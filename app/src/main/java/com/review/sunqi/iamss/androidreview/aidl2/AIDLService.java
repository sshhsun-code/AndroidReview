package com.review.sunqi.iamss.androidreview.aidl2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sunqi on 2018/5/23.
 */

public class AIDLService extends Service {

    String info = "";

    private IBinder mBinder = new IBase.Stub() {
        @Override
        public int add() throws RemoteException {
            return 7;
        }

        @Override
        public String getUserInfo(UserInfo userInfo) throws RemoteException {
            return userInfo.toString();
        }

        @Override
        public void getList(String[] list) throws RemoteException {
            list[0] = "Server端赋值：" + info;
        }

        @Override
        public void setList(String[] list) throws RemoteException {
            if (list.length > 0) {
                info = list[0];
                Log.e("sunqi_log", "AIDLService setList " + info);
            }
        }

        @Override
        public void getSList(String[] list) throws RemoteException {
            String totalString = "";
            /**
             * 从客户端取得的信息
             */
            String receivedFromClientString = "";
            for (int i = 0; i < list.length; i++) {
                receivedFromClientString += list[i];
            }
            /**
             * 从服务端返回的信息
             */
            totalString += "从客户端收到的信息为：" + receivedFromClientString + " \n在此我们新增一段返回信息:good";
            list[0] = totalString;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
