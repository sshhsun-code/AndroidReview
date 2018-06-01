package com.review.sunqi.iamss.androidreview.aidl3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/6/1.
 */

public class MessengerActivity extends Activity {

    private static final int MSG_FROM_CLIENT = 1;
    private static final int MSG_FROM_SERVER = 2;

    private Messenger mServiceMessenger;
    private Messenger mReceiverMessenger;
    private static Handler mReceiverHandler;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mServiceMessenger = new Messenger(iBinder);
            Message msg = Message.obtain(null, MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("key", "Msg From Client");
            msg.setData(data);
            msg.replyTo = mReceiverMessenger;
            try {
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_test);
        initData();
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void initData() {
        mReceiverHandler = new ReceiverHandler();
        mReceiverMessenger = new Messenger(mReceiverHandler);
    }

    private static class ReceiverHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_SERVER:
                    Log.e("sunqi_log", "Client receive Server Message");
                    Bundle data = msg.getData();
                    if (data != null) {
                        String value = data.getString("reply");
                        Log.e("sunqi_log", "Reply Message = " + value);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
