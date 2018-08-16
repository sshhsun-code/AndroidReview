package com.review.sunqi.iamss.androidreview.testviewGroup;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunqi on 2018/8/16.
 */

public class TestViewGroup extends ViewGroup {

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int resultW = widthSize, resultH = heightSize;

        int contentW = getPaddingLeft() + getPaddingRight();
        int contentH = getPaddingTop() + getPaddingBottom();

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        MarginLayoutParams layoutParams = null;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            layoutParams = (MarginLayoutParams) child.getLayoutParams();

            if (child.getVisibility() == GONE) {
                continue;
            }

            contentW += child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;

            contentH += child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

        }

        if (widthMode == MeasureSpec.AT_MOST) {
            resultW = Math.min(contentW, widthSize);
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            resultH = Math.min(contentH, heightSize);
        }

        setMeasuredDimension(resultW, resultH);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int topStart = getPaddingTop();
        int leftStart = getPaddingLeft();

        int childW = 0;
        int childH = 0;

        MarginLayoutParams layoutParams = null;

        for (int j = 0; j < getChildCount(); j++) {
            View childView = getChildAt(j);

            if (childView.getVisibility() == GONE) {
                continue;
            }
           layoutParams = (MarginLayoutParams) childView.getLayoutParams();

           childH = childView.getMeasuredHeight();
           childW = childView.getMeasuredWidth();

           leftStart += layoutParams.leftMargin;
           topStart += layoutParams.topMargin;

           childView.layout(leftStart, topStart, leftStart + childW, topStart + childH);

            leftStart += childW + layoutParams.rightMargin;
            topStart += childH + layoutParams.bottomMargin;

        }
    }
}
