package com.review.sunqi.iamss.androidreview.glide_test.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

/**
 * Created by guofang on 2018/7/3.
 */

public class GlideTopCropTransform extends BitmapTransformation {
    public GlideTopCropTransform(Context context) {
        super(context);
    }

    public GlideTopCropTransform(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap toReuse = pool.get(outWidth, outHeight, toTransform.getConfig() != null ? toTransform.getConfig() : Bitmap.Config.ARGB_8888);
        float dx = 0.0F;
        float dy = 0.0F;
        Matrix matrix = new Matrix();
        float scale;
        if (toTransform.getWidth() * outHeight > outWidth * toTransform.getHeight()) {
            scale = (float) outHeight / (float) toTransform.getHeight();
            dx = ((float) outWidth - (float) toTransform.getWidth() * scale) * 0.5F;
        } else {
            scale = (float) outWidth / (float) toTransform.getWidth();
        }

        matrix.setScale(scale, scale);
        matrix.postTranslate((float) ((int) (dx + 0.5F)), (float) ((int) (dy + 0.5F)));

        Bitmap transformed;
        if (toReuse != null) {
            transformed = toReuse;
        } else {
            transformed = Bitmap.createBitmap(outWidth, outHeight, toTransform.getConfig());
        }

        TransformationUtils.setAlpha(toTransform, transformed);
        Canvas canvas = new Canvas(transformed);
        Paint paint = new Paint(6);
        canvas.drawBitmap(toTransform, matrix, paint);

        if (toReuse != null && toReuse != transformed && !pool.put(toReuse)) {
            toReuse.recycle();
        }
        return transformed;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
