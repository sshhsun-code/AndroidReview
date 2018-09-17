package com.review.sunqi.iamss.androidreview.dispatch_test;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class TestDispatchViewGroup extends ViewGroup {

    public TestDispatchViewGroup(Context context) {
        super(context);
    }

    public TestDispatchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestDispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestDispatchViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.logEvent("TestViewGroup", "onTouchEvent", event);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else {
            return false;
        }
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
