package com.review.sunqi.iamss.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends Activity {

    private static final String TAG = "sunqi_log";

    private static final int TIME = 60 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.e(TAG, "onCreate");
        ((TextView)findViewById(R.id.first_activity_info)).setText("activity =" + this + "\n" + "task = " + this.getTaskId());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.touxiang);
            }
        }, 800000000L);
        findViewById(R.id.first_jump_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
