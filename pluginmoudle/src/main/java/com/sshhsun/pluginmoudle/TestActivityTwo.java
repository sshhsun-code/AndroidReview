package com.sshhsun.pluginmoudle;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by DELL-PC on 2017/2/22.
 */

public class TestActivityTwo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Main","test this = " + this);
        Log.e("Main","getResource = " + getResources());
        Log.e("Main","getApplication = " + getApplication());
        Log.e("Main","getApplication class = " + getApplication().getClass().getName());

        setContentView(R.layout.test);
    }

    @Override
    public Resources getResources() {
        Log.e("Main","getApplicationContext = " + getApplicationContext());
        Log.e("Main","getApplicationContext 2 = " + getApplication());
        Log.e("Main","getApplicationResources 2 = " + super.getResources());
        if(getApplication() != null && getApplication().getResources() != null){
            return getApplication().getResources();
        }
        return super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        if(getApplication() != null && getApplication().getAssets() != null){
            return getApplication().getAssets();
        }
        return super.getAssets();
    }

    @Override
    public Resources.Theme getTheme() {
        if(getApplication() != null && getApplication().getTheme() != null){
            return getApplication().getTheme();
        }
        return super.getTheme();
    }
}
