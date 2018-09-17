package com.review.sunqi.iamss.androidreview.dispatch_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.review.sunqi.iamss.androidreview.R;

public class TestDispatchActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diapatch_layout);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.logEvent("TestDispatchActivity", "dispatchTouchEvent", ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.logEvent("TestDispatchActivity", "onTouchEvent", event);
        return super.onTouchEvent(event);
    }
}
