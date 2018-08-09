package com.review.sunqi.iamss.androidreview.flow;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zhangtongyu on 2016/10/19.
 */
public class DensityUtil {

    public static final float getHeightInPx(Context context) {
        final float height = (float) context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx(Context context) {
        final float width = (float) context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp(Context context) {
        final float height = (float) context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static final int getWidthInDp(Context context) {
        final float height = (float) context.getResources().getDisplayMetrics().heightPixels;
        int widthInDp = px2dip(context, height);
        return widthInDp;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 针对部分view高度获取为0的情况，重新绘制，单位为px
     *
     * @param view
     * @return
     */
    public static int getViewRealHeight(View view) {
        if (view == null) {
            return 0;
        }
        int intw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int inth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(intw, inth);
        return view.getMeasuredHeight();
    }

    public static int getViewRealWidth(View view) {
        if (view == null) {
            return 0;
        }
        int intw = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int inth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(intw, inth);
        return view.getMeasuredWidth();
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    public static boolean checkIsVisible(Context context, View view) {
        if (view != null && view.getVisibility() == View.VISIBLE) {
            int screenWidth = getScreenMetrics(context).x;
            int screenHeight = getScreenMetrics(context).y;
            Rect rect = new Rect(0, 0, screenWidth, screenHeight);
            int[] location = new int[2];
            view.getLocationInWindow(location);
            return view.getLocalVisibleRect(rect);
        }
        return false;
    }

}
