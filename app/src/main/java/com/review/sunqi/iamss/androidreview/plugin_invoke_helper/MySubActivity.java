package com.review.sunqi.iamss.androidreview.plugin_invoke_helper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.review.sunqi.iamss.androidreview.R;

import dalvik.system.DexClassLoader;

/**
 * Created by sunqi on 2018/10/29.
 */

public class MySubActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.first_jump_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.sshhsun.pluginmoudle",
                        "com.sshhsun.pluginmoudle.TestActivity"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        hookClassLoader();
    }

    private static final String PLUGIN_APK_PATH = "/plugin_demo.apk";

    private void hookClassLoader() {
        try {
            new Thread(){
                @Override
                public void run() {
                    //创建一个属于我们自己插件的ClassLoader，我们分析过只能使用DexClassLoader
                    String cachePath = MySubActivity.this.getCacheDir().getAbsolutePath();
                    String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + PLUGIN_APK_PATH;
                    DexClassLoader mClassLoader = new DexClassLoader(apkPath, cachePath,cachePath, getClassLoader());
                    MyHookHelper.inject(mClassLoader);
                    try {
                        AMSHookHelper.hookActivityManagerNative();
                        AMSHookHelper.hookActivityThreadHandler();
                    } catch (Exception e) {
                        Log.e("Main","加载异常了 = " + e.getMessage());
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MySubActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
