package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by sunqi on 2018/6/2.
 */

public class BinderPool {

    private static BinderPool instance;

    private Context mContext;

    private IBinderPool mIBinderPool;

    private ServiceConnection binderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBinderPool = IBinderPool.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public static BinderPool getInstance(Context context) {
        if (instance == null) {
            instance = new BinderPool(context);
        }
        return instance;
    }

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPool();
    }

    private void connectBinderPool() {
        Intent binderPoolIntent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(binderPoolIntent, binderPoolConnection, Context.BIND_AUTO_CREATE);
    }


    public IBinder getIBinder(int requestCode) {
        IBinder binder = null;

        try {
            if (mIBinderPool != null) {
                binder = mIBinderPool.queryBinder(requestCode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }
}
