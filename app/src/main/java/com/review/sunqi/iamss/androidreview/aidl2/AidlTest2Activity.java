package com.review.sunqi.iamss.androidreview.aidl2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/5/22.
 */

public class AidlTest2Activity extends Activity {

    private IBase mBaseAidl;
    private TextView showPersonText;
    private String[] mStrs = {"1", "2", "3"};

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mBaseAidl = IBase.Stub.asInterface(service);
            Log.e("sunqi_log", "AIDLService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBaseAidl = null;
            Log.e("sunqi_log", "AIDLService onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_test2);
        bindMyService();
        showPersonText = findViewById(R.id.text_show_person);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    showPersonText.append("\n" + mBaseAidl.add());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_get_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo info = new UserInfo("张三", "北京", 18);
                try {
                    showPersonText.append("\n" + mBaseAidl.getUserInfo(info));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_get_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mBaseAidl.getList(mStrs);
                    showPersonText.append("\n 本地String 数组0项：" + mStrs[0]);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_set_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mBaseAidl.setList(mStrs);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_get_S_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mBaseAidl.getSList(mStrs);
                    showPersonText.append("\n 本地String 数组0项：" + mStrs[0]);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindMyService();
        mBaseAidl = null;
    }

    private void bindMyService() {
        Intent intent1 = new Intent(getApplicationContext(), AIDLService.class);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);
    }

    private void unBindMyService() {
        unbindService(mConnection);
    }
}
