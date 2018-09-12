package com.review.sunqi.iamss.androidreview.glide_test.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.gm.hostpluginscommonlib.DeviceUtils;
import com.gm.pluginscommonlib.R;
import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.utils.DeviceUtils;

import java.io.File;

/**
 * Created by Jzy on 2016/11/21.
 */
public class ImageLoader {
    public static final int CORNER_TOP_LEFT = 1;
    public static final int CORNER_TOP_RIGHT = 1 << 1;
    public static final int CORNER_BOTTOM_LEFT = 1 << 2;
    public static final int CORNER_BOTTOM_RIGHT = 1 << 3;
    public static final int CORNER_ALL = CORNER_TOP_LEFT | CORNER_TOP_RIGHT | CORNER_BOTTOM_LEFT | CORNER_BOTTOM_RIGHT;
    public static final int CORNER_TOP = CORNER_TOP_LEFT | CORNER_TOP_RIGHT;
    public static final int CORNER_RIGHT = CORNER_TOP_RIGHT | CORNER_BOTTOM_RIGHT;
    private static final String IMAGE_CROP_400 = "@base@tag=imgScale&w=400&h=400&c=1&f=0&m=2";
    private static final String IMAGE_CROP_530_400 = "@base@tag=imgScale&w=530&h=400&c=1&f=0&m=2";

    private static GlideTopCropTransform sTopCropTransform = null;
    private static final Object mLock = new Object();

    public interface DialogCallback {
        void onDialogCallback();
    }

    public static String getImageCropUrl(String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return imageUrl;
        }
        return imageUrl + IMAGE_CROP_400;
    }

    public static String getNewsImageCropUrl(String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return imageUrl;
        }
        return imageUrl + IMAGE_CROP_530_400;
    }

    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadCenterCropImage(Context context, String imageUrl, ImageView imageView) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).centerCrop().into(new GlideDrawableImageViewTarget(imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                }
            });
        }
        return null;
    }


    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadCenterCropImage(Context context, String imageUrl, ImageView imageView, int defRsId, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            if (!TextUtils.isEmpty(imageUrl) && imageUrl.endsWith(imageUrl.toLowerCase())) {
                return loadImage(context, imageUrl, imageView, defRsId, dialogCallback);
            } else {
                return Glide.with(context).load(imageUrl).placeholder(defRsId).error(defRsId).centerCrop().into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (dialogCallback != null) {
                            dialogCallback.onDialogCallback();
                        }
                    }
                });
            }
        }
        return null;
    }

    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadImage(Context context, String imageUrl, ImageView imageView) {
        return loadImage(context, imageUrl, imageView, null);
    }

    public static Target<GlideDrawable> loadImageNoAnimation(Context context, String imageUrl, ImageView imageView) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).dontAnimate().into(imageView);
        }
        return null;
    }

    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadImage(Context context, String imageUrl, ImageView imageView, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).into(new GlideDrawableImageViewTarget(imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (dialogCallback != null) {
                        dialogCallback.onDialogCallback();
                    }
                }
            });
        }
        return null;
    }

    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadImage(Context context, String imageUrl, ImageView imageView, int defRsid, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .placeholder(defRsid)
                    .error(defRsid)
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (dialogCallback != null) {
                                dialogCallback.onDialogCallback();
                            }
                        }
                    });
        }
        return null;
    }

    /**
     * 竖直加载图片
     */
    public static Target<GlideDrawable> loadImageVertical(Context context, String imageUrl, ImageView imageView) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).transform(new RotateTransformation(context, 90f)).into(imageView);
        }
        return null;
    }

    /**
     * 加载图片
     */
    public static Target<GlideDrawable> loadImageCenterCrop(Context context, String imageUrl, ImageView imageView, int defRsid, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(defRsid)
                    .error(defRsid)
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (dialogCallback != null) {
                                dialogCallback.onDialogCallback();
                            }
                        }
                    });
        }
        return null;
    }

    /**
     * 加载本地图片图片
     */
    public static void loadSDCardImage(Context context, String imageUrl, final ImageView imageView, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            File file = new File(imageUrl);
            Glide.with(context).load(file).into(new GlideDrawableImageViewTarget(imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (dialogCallback != null) {
                        dialogCallback.onDialogCallback();
                    }
                }
            });
        }
    }

    /**
     * 加载本地图片图片
     */
    public static Target<GlideDrawable> loadSDCardRoundImage(Context context, String imageUrl, final ImageView imageView) {
        return loadSDCardRoundImage(context, imageUrl, imageView, context.getResources().getDimensionPixelSize(R.dimen.glide_round_angle));
    }

    /**
     * 加载本地图片图片
     */
    public static Target<GlideDrawable> loadSDCardRoundImage(Context context, String imageUrl, final ImageView imageView, int angle) {
        if (isHaveLoad(context)) {
            File file = new File(imageUrl);
            return Glide.with(context)
                    .load(file)
                    .crossFade()
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .into(imageView);
        }
        return null;
    }


    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final String imageUrl, final ImageView imageView) {
        return loadRoundImage(context, imageUrl, imageView, context.getResources().getDimensionPixelSize(R.dimen.glide_round_angle));
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final String imageUrl, int defRsid, final ImageView imageView) {
        return loadRoundImage(context, imageUrl, imageView, defRsid, context.getResources().getDimensionPixelSize(R.dimen.glide_round_angle));
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final String imageUrl, final ImageView imageView, int angle) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .crossFade()
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView 要设置的imageView
     * @param imageUrl  图片url
     * @param angle     圆角
     * @param corners   指定那个方向圆角，取GlideRoundTransform这里面的，默认四个角都是圆角
     */
    public static Target<GlideDrawable> loadRoundImage(Context context, ImageView imageView, String imageUrl, int angle, int corners) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, corners))
                    .crossFade()
                    .into(imageView);
        }
        return null;
    }

    /**
     * 使用bitmapShader进行圆角切割
     */
    public static Target<GlideDrawable> loadRoundImageFitCenter(final Context context, final ImageView imageView, String imageUrl, final int angle, final int corners) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .transform(new GlideRoundTransform(context, angle, corners))
                    .crossFade()
                    .into(imageView);
        }
        return null;
    }

    public static Target<Bitmap> loadRoundImageByShader(final Context context, final ImageView imageView, String imageUrl, final int angle, final int corners) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    float radius = DeviceUtils.dip2px(context, angle);
                    Bitmap bitmap = roundBitmapByShader(resource, imageView.getWidth(), imageView.getHeight(), radius, corners);
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
        return null;
    }

    //可以当做尝试使用方法，但是这里bitmap的创建没有进行回收利用处理，使用Glide的Transform会更好
    private static Bitmap roundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, float radius, int corners) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap can't be null");
        }
        // 初始化缩放比
        float widthScale = outWidth * 1.0f / bitmap.getWidth();
        float heightScale = outHeight * 1.0f / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);
        // 初始化绘制纹理图
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 根据控件大小对纹理图进行拉伸缩放处理
        bitmapShader.setLocalMatrix(matrix);
        // 初始化目标bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        // 初始化目标画布
        Canvas targetCanvas = new Canvas(targetBitmap);
        // 初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        // 利用画笔将纹理图绘制到画布上面
        targetCanvas.drawRoundRect(new RectF(0, 0, outWidth, outHeight), radius, radius, paint);
        //把需要的圆角补上
        int notRoundedCorners = corners ^ ImageLoader.CORNER_ALL;
        if ((notRoundedCorners & ImageLoader.CORNER_TOP_LEFT) != 0) {
            final Rect block = new Rect(0, 0, (int) radius, (int) radius);
            targetCanvas.drawRect(block, paint);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_TOP_RIGHT) != 0) {
            final Rect block = new Rect((int) (outWidth - radius), 0, outWidth, (int) radius);
            targetCanvas.drawRect(block, paint);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_BOTTOM_LEFT) != 0) {
            final Rect block = new Rect(0, (int) (outHeight - radius), (int) radius, outHeight);
            targetCanvas.drawRect(block, paint);
        }
        if ((notRoundedCorners & ImageLoader.CORNER_BOTTOM_RIGHT) != 0) {
            final Rect block = new Rect((int) (outWidth - radius), (int) (outHeight - radius), outWidth, outHeight);
            targetCanvas.drawRect(block, paint);
        }
        return targetBitmap;
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView 要设置的imageView
     * @param imageUrl  图片url
     * @param angle     圆角
     * @param corners   指定那个方向圆角，取GlideRoundTransform这里面的，默认四个角都是圆角
     */
    public static Target<GlideDrawable> loadRoundImage(Context context, ImageView imageView, String imageUrl, int angle, int corners, String urlMd5) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .signature(new StringSignature(urlMd5))
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, corners))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载圆角图片
     *
     * @param fgm       所在Fragment
     * @param imageView 要设置的imageView
     * @param imageUrl  图片url
     * @param angle     圆角
     * @param corners   指定那个方向圆角，取GlideRoundTransform这里面的，默认四个角都是圆角
     */
    public static Target<GlideDrawable> loadRoundImage(Fragment fgm, ImageView imageView, String imageUrl, int angle, int corners) {
        if (fgm == null) {
            return null;
        }
        return Glide.with(fgm)
                .load(imageUrl)
                .transform(new CenterCrop(fgm.getContext()), new GlideRoundTransform(fgm.getContext(), angle, corners))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final String imageUrl, final ImageView imageView, final int defRsid, int angle) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .placeholder(defRsid)
                    .error(defRsid)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final Uri imageUri, final ImageView imageView) {
        return loadRoundImage(context, imageUri, imageView, context.getResources().getDimensionPixelSize(R.dimen.glide_round_angle));
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final Uri imageUri, final ImageView imageView, int angle) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUri)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .crossFade()
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载资源圆角图片
     */
    public static Target<GlideDrawable> loadRoundResImage(final Context context, int rsId, final ImageView imageView) {
        return loadRoundResImage(context, rsId, imageView, context.getResources().getDimensionPixelSize(R.dimen.glide_round_angle));
    }

    /**
     * 加载资源圆角图片
     */
    public static Target<GlideDrawable> loadRoundResImage(final Context context, int rsId, final ImageView imageView, int angle) {
        if (isHaveLoad(context)) {
            Glide.with(context)
                    .load(rsId)
                    .error(rsId)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(final Context context, final String imageUrl, final ImageView imageView) {
        if (isHaveLoad(context))
            Glide.with(context).load(imageUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
    }

    /**
     * 加载圆形图片
     */
    public static Target<Bitmap> loadCircleImage(final Context context, final String imageUrl, int defRsId, final ImageView imageView) {
        if (isHaveLoad(context)) {
            return Glide.with(context).load(imageUrl).asBitmap().placeholder(defRsId).error(defRsId).centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        return null;
    }

    /**
     * 加载资源圆形图片
     */
    public static void loadCircleResImage(final Context context, final int rsId, final ImageView imageView) {

        if (isHaveLoad(context)) {
            Glide.with(context).load(rsId).asBitmap().placeholder(rsId).error(rsId).centerCrop().into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }

    /**
     * 使用本地方法加载圆形图片
     * @param rsId 资源id
     */
    public static void loadCircleResImageNative(final Context context, final int rsId, final ImageView imageView) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), rsId);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
    }

    /**
     * 加载图片
     */
    public static void loadGifImage(Context context, String imageUrl, ImageView imageView, final DialogCallback dialogCallback) {
        if (isHaveLoad(context))
            Glide.with(context).load(imageUrl).into(new GlideDrawableImageViewTarget(imageView) {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    super.onResourceReady(resource, animation);
                    if (dialogCallback != null) {
                        dialogCallback.onDialogCallback();
                    }
                }
            });
    }

    public static boolean isHaveLoad(Context context) {
        if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT) {
            return !(context instanceof Activity) || (context instanceof Activity && !((Activity) context).isDestroyed());
        } else {
            return !(context instanceof Activity) || (context instanceof Activity && !((Activity) context).isFinishing());
        }
    }

    public interface GetImageCachePathCallback {
        void onSuccess(String path, Bitmap bitmap);
        void onFail();
    }


    public static class GetImageCacheTask extends AsyncTask<String, Void, Object> {
        private final Context context;
        private final GetImageCachePathCallback callback;
        private Integer mCacheType = TYPE_FILE;

        public static final int TYPE_FILE = 1;
        public static final int TYPE_BITMAP = 2;

        public GetImageCacheTask(Context context, GetImageCachePathCallback callback) {
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected Object doInBackground(String... params) {
            if (context == null) {
                return null;
            }
            if (params.length < 2) {
                return null;
            }
            String imgUrl = params[0];
            Integer type = Integer.valueOf(params[1]);
            if (type == null) {
                type = TYPE_FILE;
            }
            mCacheType = type;

            switch (mCacheType) {
                case TYPE_FILE:
                    return downLoadFile(imgUrl);
                case TYPE_BITMAP:
                    return downLoadBitmap(imgUrl);
                default:
                    return null;
            }
        }

        private File downLoadFile(String imgUrl) {
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception ex) {
                return null;
            }
        }

        private Bitmap downLoadBitmap(String imgUrl) {
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .asBitmap()
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception ex) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            if (result == null) {
                if (callback != null) {
                    callback.onFail();
                }
                return;
            }
            String path = null;
            Bitmap bitmap = null;
            if (mCacheType == TYPE_FILE) {
                //此path就是对应文件的缓存路径
                path = ((File) result).getPath();
                //将缓存文件copy, 命名为图片格式文件
                // copyFile(path, newPath);
            } else if (mCacheType == TYPE_BITMAP) {
                bitmap = (Bitmap) result;
            }
            if (callback != null) {
                callback.onSuccess(path, bitmap);
            }
        }
    }

    public static Target<GlideDrawable> loadImageTopCrop(final Context context,
                                                         final String imageUrl, final ImageView imageView, final int defRsid, final DialogCallback dialogCallback) {
        synchronized (mLock) {
            if (sTopCropTransform == null) {
                sTopCropTransform = new GlideTopCropTransform(context);
            }
        }
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .transform(sTopCropTransform)
                    .crossFade()
                    .placeholder(defRsid)
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (dialogCallback != null) {
                                dialogCallback.onDialogCallback();
                            }
                        }
                    });
        }
        return null;
    }

    public static Target<GlideDrawable> loadRoundImageTopCrop(final Context context,
                                                              final String imageUrl, final ImageView imageView, final int defRsid, int angle, final DialogCallback dialogCallback) {
        synchronized (mLock) {
            if (sTopCropTransform == null) {
                sTopCropTransform = new GlideTopCropTransform(context);
            }
        }
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .transform(sTopCropTransform, new GlideRoundTransform(context, angle, CORNER_ALL))
                    .crossFade()
                    .placeholder(defRsid)
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (dialogCallback != null) {
                                dialogCallback.onDialogCallback();
                            }
                        }
                    });
        }
        return null;
    }

    /**
     * 加载圆角图片,如果是gif图，以静态bitmap形式加载
     */
    public static Target<Bitmap> loadRoundImageAsBitmapCenterCrop(final Context context, final String imageUrl, final ImageView imageView, final int defRsid, int angle) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .placeholder(defRsid)
                    .error(defRsid)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, CORNER_ALL))
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载图片
     */
    public static Target<Bitmap> loadImageAsBitmapCenterCrop(Context context, String imageUrl, ImageView imageView, final int defRsid) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .asBitmap()
                    .placeholder(defRsid)
                    .error(defRsid)
                    .transform(new CenterCrop(context))
                    .into(imageView);
        }
        return null;
    }

    public static Target<GlideDrawable> clearImage(Context context, ImageView imageView, final int rsid, int angle) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(rsid)
                    .transform(new GlideRoundTransform(context, angle, CORNER_ALL))
                    .placeholder(rsid)
                    .into(imageView);
        }
        return null;
    }

    /**
     * 加载圆角图片
     */
    public static Target<GlideDrawable> loadRoundImage(final Context context, final String imageUrl, final ImageView imageView, final int defRsid, int angle, int corners, final DialogCallback dialogCallback) {
        if (isHaveLoad(context)) {
            return Glide.with(context)
                    .load(imageUrl)
                    .placeholder(defRsid)
                    .error(defRsid)
                    .transform(new CenterCrop(context), new GlideRoundTransform(context, angle, corners))
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (dialogCallback != null) {
                                dialogCallback.onDialogCallback();
                            }
                        }
                    });
        }
        return null;
    }

    public static void clear(Object target) {
        if (target != null) {
            if (target instanceof View) {
                Glide.clear((View) target);
            } else {
                Glide.clear((Target<?>) target);
            }
        }
    }

    public static int getCornerTopConstant() {
        return ImageLoader.CORNER_TOP;
    }

    public static int getBitmapTypeConstant() {
        return ImageLoader.GetImageCacheTask.TYPE_BITMAP;
    }

    public static void execImageCacheTask(Context context, String imgUrl, String fileType, final GetImageCachePathCallback callback) {
        ImageLoader.GetImageCacheTask imageLoder = new ImageLoader.GetImageCacheTask(context, callback);
        imageLoder.execute(imgUrl, fileType);
    }

    public static int getCornerAllConstant() {
        return ImageLoader.CORNER_ALL;
    }

    public static int getFileTypeConstant() {
        return ImageLoader.GetImageCacheTask.TYPE_FILE;
    }

    public static int getCornerRightConstant() {
        return ImageLoader.CORNER_RIGHT;
    }

    public static Target<Bitmap> loadImageAsBitmapCenterCrop(Context context, String imageUrl, ImageView imageView, int defRsid, int angle) {
        if (angle == 0) {
            return ImageLoader.loadImageAsBitmapCenterCrop(context, imageUrl, imageView, defRsid);
        } else {
            return ImageLoader.loadRoundImageAsBitmapCenterCrop(context, imageUrl, imageView, defRsid, angle);
        }
    }

    public static Target<GlideDrawable> loadImageTopCrop(Context context, String imageUrl, ImageView imageView, int defRsid, int angle, final DialogCallback glideModuleCallBack) {
        if (angle == 0) {
            return ImageLoader.loadImageTopCrop(context, imageUrl, imageView, defRsid, glideModuleCallBack);
        } else {
            return ImageLoader.loadRoundImageTopCrop(context, imageUrl, imageView, defRsid, angle, glideModuleCallBack);
        }
    }

    /**
     * 加载圆角高斯模糊图片
     * */
    public static void loadImageBlurRound(Context context, String imageUrl, int defRsid, ImageView imageView, int cornerRadius) {
        if (isHaveLoad(context)) {
            Glide.with(context).load(imageUrl).asBitmap().error(defRsid).placeholder(defRsid)
                    .transform(
                            new BlurTransformation(context, 60), new CenterCrop(context),
                            new GlideRoundTransform(context, cornerRadius, CORNER_ALL))
                    .into(imageView);
        }
    }

    /**
     * 加载高斯模糊图片
     * */
    public static void loadImageBlur(Context context, String imageUrl, int defRsid, ImageView imageView) {
        if (isHaveLoad(context))
            Glide.with(context).load(imageUrl).asBitmap().error(defRsid).placeholder(defRsid)
                    .transform(new BlurTransformation(context, 60))
                    .into(imageView);
    }
}
