package com.review.sunqi.iamss.androidreview.aidl3;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sunqi on 2018/6/1.
 */

public class MessengerService extends Service {

    private static final int MSG_FROM_CLIENT = 1;
    private static final int MSG_FROM_SERVER = 2;

    private static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.e("sunqi_log", "MessengerService receive Client Message");
                    Bundle data = msg.getData();
                    if (data != null) {
                        String value = data.getString("key");
                        Log.e("sunqi_log", "Message value = " + value);
                    }
                    Messenger replyMessenger = msg.replyTo;
                    if (replyMessenger != null) {
                        try {
                            Message replyMsg = Message.obtain(null, MSG_FROM_SERVER);
                            Bundle replyData = new Bundle();
                            replyData.putString("reply", "你的消息我已经收到了~稍后回复你~");
                            replyMsg.setData(replyData);
                            replyMessenger.send(replyMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger messenger = new Messenger(new MessageHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
