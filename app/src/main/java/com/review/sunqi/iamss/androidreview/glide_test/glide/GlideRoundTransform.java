package com.review.sunqi.iamss.androidreview.glide_test.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.review.sunqi.iamss.androidreview.utils.DeviceUtils;

/**
 * Created by Jzy on 2016/11/21.
 */

public class GlideRoundTransform extends BitmapTransformation {

    private  float radius = 0f;
    private  int corners = ImageLoader.CORNER_ALL;

    public GlideRoundTransform(Context context) {
        this(context, 5, ImageLoader.CORNER_ALL);
    }

    public GlideRoundTransform(Context context, int dp, int corners) {
        super(context);
        this.radius = DeviceUtils.dip2px(context, dp);
        this.corners = corners;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform,outWidth,outHeight);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        if (source == null) return null;

        Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }
        // 初始化缩放比
        float widthScale = outWidth * 1.0f / source.getWidth();
        float heightScale = outHeight * 1.0f / source.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);
        int width = result.getWidth();
        int height = result.getHeight();

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        BitmapShader bitmapShader = new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, outWidth, outHeight);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        //把需要的圆角补上
        int notRoundedCorners = corners ^ ImageLoader.CORNER_ALL;
        if ((notRoundedCorners & ImageLoader.CORNER_TOP_LEFT) != 0) {
            clipTopLeft(canvas, paint, (int)radius, width, height);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_TOP_RIGHT) != 0) {
            clipTopRight(canvas, paint, (int)radius, width, height);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_BOTTOM_LEFT) != 0) {
            clipBottomLeft(canvas, paint, (int)radius, width, height);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_BOTTOM_RIGHT) != 0) {
            clipBottomRight(canvas, paint, (int)radius, width, height);
        }
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    private static void clipTopLeft(Canvas canvas, Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, 0, offset, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipTopRight(Canvas canvas, Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, 0, width, offset);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomLeft(Canvas canvas, Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(0, height - offset, offset, height);
        canvas.drawRect(block, paint);
    }

    private static void clipBottomRight(Canvas canvas, Paint paint, int offset, int width, int height) {
        final Rect block = new Rect(width - offset, height - offset, width, height);
        canvas.drawRect(block, paint);
    }
}

