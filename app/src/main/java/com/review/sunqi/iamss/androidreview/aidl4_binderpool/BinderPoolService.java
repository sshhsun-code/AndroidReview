package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by sunqi on 2018/6/1.
 */

public class BinderPoolService extends Service{

    private IBinder mBinderPool = new IBinderPool.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public IBinder queryBinder(int binderRequestCode) throws RemoteException {
            Log.e("sunqi_log", "binderRequestCode = " + binderRequestCode);
            switch (binderRequestCode) {
                case Constant.BINDER_CODE_BOOK:
                    return new IBookImpl();
                case Constant.BINDER_CODE_STUDENT:
                    return new IStudentImpl();

            }
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }
}
