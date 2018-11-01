package com.review.sunqi.iamss.androidreview.eventbus_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.review.sunqi.iamss.androidreview.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sunqi on 2018/6/4.
 */

public class EventBusTestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        findViewById(R.id.first_jump_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EventBusTestActivity.this, TestTwoActivity.class));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(111);
                    }
                },3000);
            }
        });
    }


}
