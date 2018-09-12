package com.review.sunqi.iamss.androidreview.utils;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;


/**
 * 机型工具类
 *
 * @author liuwei
 */
public class DeviceUtils {
    private static final String MODEL = Build.MODEL.toLowerCase();
    private static final String MANUFACTURER = Build.MANUFACTURER.toLowerCase();
    private static final String BRAND = Build.BRAND.toLowerCase();

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }



    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context
     * @return
     */
    public static float dip2px(Context context, float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static float sp2px(Context context, float pxValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue, context.getResources().getDisplayMetrics());
    }


}
