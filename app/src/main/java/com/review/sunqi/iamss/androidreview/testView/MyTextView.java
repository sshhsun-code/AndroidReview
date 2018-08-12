package com.review.sunqi.iamss.androidreview.testView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.review.sunqi.iamss.androidreview.R;

public class MyTextView extends View {

    private  int mTextSize;
    TextPaint mPaint;
    private String mText;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyTextView);
        mText = ta.getString(R.styleable.MyTextView_android_text);
        mTextSize = ta.getDimensionPixelSize(R.styleable.MyTextView_android_textSize,24);

        ta.recycle();

        mPaint = new TextPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.e("sunqi_log", "From: ", new Exception());
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        printMeasureSpec(true, widthMode, widthSize);
        printMeasureSpec(false, heightMode, heightSize);


        ViewGroup.LayoutParams  params = getLayoutParams();
        int height = params.height;
        int width = params.width;

        /**resultW 代表最终设置的宽，resultH 代表最终设置的高*/
        int resultW = widthSize;
        int resultH = heightSize;

        int contentW = 0;
        int contentH = 0;

        /**重点处理 AT_MOST 模式，TestView 自主决定数值大小，但不能超过 ViewGroup 给出的
         * 建议数值
         * */
        if ( widthMode == MeasureSpec.AT_MOST ) {

            if (!TextUtils.isEmpty(mText)){
                contentW = (int) mPaint.measureText(mText);
                contentW += getPaddingLeft() + getPaddingRight();
                resultW = Math.min(widthSize, contentW);
            }

        }

        if ( widthMode == MeasureSpec.UNSPECIFIED ) {
            if (!TextUtils.isEmpty(mText)) {
                contentW = (int) mPaint.measureText(mText);
                contentW += getPaddingLeft() + getPaddingRight();
                resultW = contentW;
            }
        }

        if ( heightMode == MeasureSpec.AT_MOST ) {
            if (!TextUtils.isEmpty(mText)){
                contentH = mTextSize;
                contentH += getPaddingTop() + getPaddingBottom();
                resultH = Math.min(heightSize, contentH);
            }
        }

        if ( heightMode == MeasureSpec.UNSPECIFIED ) {
            if (!TextUtils.isEmpty(mText)){
                contentH = mTextSize;
                contentH += getPaddingTop() + getPaddingBottom();
                resultH = contentH;
            }
        }

        //一定要设置这个函数，不然会报错
        setMeasuredDimension(resultW,resultH);
    }

    private void printMeasureSpec(boolean isWidth, int mode, int size) {
        String modeStr = "";
        switch (mode) {
            case MeasureSpec.EXACTLY:
                modeStr = "EXACTLY";
                break;
            case MeasureSpec.AT_MOST:
                modeStr = "AT_MOST";
                break;
            case MeasureSpec.UNSPECIFIED:
                modeStr = "UNSPECIFIED";
                break;
        }
        Log.e("sunqi_log", "\n" + (isWidth ? "Width :" : "Height : "));
        Log.e("sunqi_log", "mode = " + modeStr + " & size = " + size);
//        Log.e("sunqi_log", "From: ", new Exception());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cx = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
        int cy = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2;

        canvas.drawColor(Color.RED);
        if (TextUtils.isEmpty(mText)) {
            return;
        }
        canvas.drawText(mText,cx,cy,mPaint);

    }
}