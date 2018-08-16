package com.review.sunqi.iamss.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.review.sunqi.iamss.androidreview.adil_Test.AidlTestActivity;
import com.review.sunqi.iamss.androidreview.aidl2.AidlTest2Activity;
import com.review.sunqi.iamss.androidreview.aidl3.MessengerActivity;
import com.review.sunqi.iamss.androidreview.aidl4_binderpool.BinderPoolActivity;
import com.review.sunqi.iamss.androidreview.ashmem_test.AshmemActivity;
import com.review.sunqi.iamss.androidreview.flow.FlowTestActivity;
import com.review.sunqi.iamss.androidreview.fragment_test.HolderActivity;
import com.review.sunqi.iamss.androidreview.serializablepkg.SerializableTestDemo;
import com.review.sunqi.iamss.androidreview.testView.MyTextViewActivity;
import com.review.sunqi.iamss.androidreview.testviewGroup.MyTestViewGroupAct;

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
        findViewById(R.id.fifth_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.sixth_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.binder_pool_activity_jump).setOnClickListener(mClickListener);
        findViewById(R.id.ashmem_test).setOnClickListener(mClickListener);
        findViewById(R.id.flow_text_test).setOnClickListener(mClickListener);
        findViewById(R.id.mytextview_test).setOnClickListener(mClickListener);
        findViewById(R.id.mytest_view_group).setOnClickListener(mClickListener);
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
                        Intent intent3 = new Intent(MainActivity.this, AidlTestActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.fifth_activity_jump:
                        Intent intent4 = new Intent(MainActivity.this, AidlTest2Activity.class);
                        startActivity(intent4);
                        break;
                    case R.id.sixth_activity_jump:
                        Intent intent5 = new Intent(MainActivity.this, MessengerActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.binder_pool_activity_jump:
                        Intent intent6 = new Intent(MainActivity.this, BinderPoolActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.ashmem_test:
                        Intent intent7 = new Intent(MainActivity.this, AshmemActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.flow_text_test:
                        Intent intent8 = new Intent(MainActivity.this, FlowTestActivity.class);
                        startActivity(intent8);
                        break;
                    case R.id.mytextview_test:
                        Intent intent9 = new Intent(MainActivity.this, MyTextViewActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.mytest_view_group:
                        Intent intent10 = new Intent(MainActivity.this, MyTestViewGroupAct.class);
                        startActivity(intent10);
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
