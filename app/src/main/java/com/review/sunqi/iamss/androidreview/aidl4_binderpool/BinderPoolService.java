package com.review.sunqi.iamss.androidreview.aidl4_binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sunqi on 2018/6/1.
 */

public class BinderPoolService extends Service{

    private IBinder mBinderPool;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
