package com.review.sunqi.iamss.androidreview.leakcanary_test;

import android.content.Context;

public class TestHelper {

    private static TestHelper mInstance;
    private static Context mContext;

    public TestHelper(Context context) {
        mContext = context;
    }

    public static TestHelper getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new TestHelper(context);
        }
        return mInstance;
    };

    public void testMethod() {
        
    }
}
