package com.review.sunqi.iamss.androidreview.diy_view.gesturedector;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by sunqi on 2018/9/14.
 */

public class GestureView extends ScrollView implements GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector = null;

    public GestureView(Context context) {
        super(context);

        mGestureDetector = new GestureDetector(context, this);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mGestureDetector = new GestureDetector(context, this);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mGestureDetector = new GestureDetector(context, this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mGestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e("sunqi_log", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.e("sunqi_log", "onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.e("sunqi_log", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("sunqi_log", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.e("sunqi_log", "onLongPress");

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.e("sunqi_log", "onFling");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("sunqi_log", "onTouchEvent");
        return mGestureDetector.onTouchEvent(event);
    }

    private void pritfCallBackInfo(String info) {
        Log.e("sunqi_log", info);
    }
}
