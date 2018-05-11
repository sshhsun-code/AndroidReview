package com.review.sunqi.iamss.androidreview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends Activity {

    private static final String TAG = "sunqi_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.e(TAG, "onCreate");
        ((TextView)findViewById(R.id.third_activity_info)).setText("activity =" + this + "\n" + "task = " + this.getTaskId());
        findViewById(R.id.third_jump_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
