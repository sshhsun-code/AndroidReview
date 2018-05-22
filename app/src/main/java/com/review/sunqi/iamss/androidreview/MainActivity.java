package com.review.sunqi.iamss.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.review.sunqi.iamss.androidreview.fragment_test.HolderActivity;
import com.review.sunqi.iamss.androidreview.serializablepkg.SerializableTestDemo;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private View.OnClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
        findViewById(R.id.first_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.second_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.third_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.fouth_activity_jump).setOnClickListener(mClickListener);
        Log.e(TAG, "onCreate");
    }

    private void initListener() {
        mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.first_activity_jump:
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.second_activity_jump:
                        Intent intent1 = new Intent(MainActivity.this, SerializableTestDemo.class);
                        startActivity(intent1);
                        break;
                    case R.id.third_activity_jump:
                        Intent intent2 = new Intent(MainActivity.this, HolderActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.fouth_activity_jump:
                        Intent intent3 = new Intent(MainActivity.this, HolderActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }

    private void showToast() {

    }

    private void showDialg(Activity activity) {

    }
}
