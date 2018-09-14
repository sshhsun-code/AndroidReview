package com.review.sunqi.iamss.androidreview.diy_view.velocity_test;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by sunqi on 2018/9/14.
 */

public class VelocityView extends View {

    //用于回调的接口
    GetVelocityListener listener;
    //追踪速度关键的类。没有这个这篇文章将毫无意义
    VelocityTracker velocityTracker;
    //要画文字或者任何东西都需要的paint
    Paint paint = new Paint();


    public VelocityView(Context context) {
        super(context);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VelocityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VelocityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画文字的代码。
        canvas.save();
        paint.setTextSize(30);
        paint.setColor(Color.BLACK);
        canvas.drawText("x = " + xVelocity, (getLeft() + getRight()) / 2, (getTop() + getBottom()) / 2, paint);
        canvas.drawText("y =" + yVelocity, (getLeft() + getRight()) / 2, (getTop() + getBottom()) / 2 + 40, paint);
        //画完之后回收一下
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //初始化
                velocityTracker = VelocityTracker.obtain();
                break;
            case MotionEvent.ACTION_MOVE:
                //追踪
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);
                xVelocity = (int) velocityTracker.getXVelocity();
                yVelocity = (int) velocityTracker.getYVelocity();

                if (listener != null) {
                    listener.get(xVelocity, yVelocity);
                }
                //强制刷新一下view，否则不会一直掉onDraw。
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //回收
                velocityTracker.clear();
                velocityTracker.recycle();
                break;
            default:
                break;
        }
        return true;
    }


    int xVelocity = 0;
    int yVelocity = 0;

    public interface GetVelocityListener {
        public void get(int x, int y);
    }

}
