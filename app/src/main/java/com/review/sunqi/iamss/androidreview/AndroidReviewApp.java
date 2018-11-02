package com.review.sunqi.iamss.androidreview;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Method;


public class AndroidReviewApp extends Application {

    private static Context sContext;
    private RefWatcher refWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = base;
        hookClassLoader();
    }

    private AssetManager assetManager;
    private Resources newResource;
    private Resources.Theme mTheme;

    private void hookClassLoader() {
        try {

            //创建我们自己的Resource
            String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chajian_demo.apk";
            String mPath = getPackageResourcePath();

            assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);

//            addAssetPathMethod.invoke(assetManager, mPath);
            addAssetPathMethod.invoke(assetManager, apkPath);


            Method ensureStringBlocks = AssetManager.class.getDeclaredMethod("ensureStringBlocks");
            ensureStringBlocks.setAccessible(true);
            ensureStringBlocks.invoke(assetManager);

            Resources supResource = getResources();
            Log.e("Main", "supResource = " + supResource);
            newResource = new Resources(assetManager, supResource.getDisplayMetrics(), supResource.getConfiguration());
            Log.e("Main", "设置 getResource = " + getResources());

            mTheme = newResource.newTheme();
            mTheme.setTo(super.getTheme());
        } catch (Exception e) {
            Log.e("Main", "走了我的callActivityOnCreate 错了 = " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        refWatcher= setupLeakCanary();
        Log.e("sunqi_log", " App Resource = "  + getResources());
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        AndroidReviewApp app = (AndroidReviewApp) context.getApplicationContext();
        return app.refWatcher;
    }
}
