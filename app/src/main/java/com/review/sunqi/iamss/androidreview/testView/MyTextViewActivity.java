package com.review.sunqi.iamss.androidreview.testView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.review.sunqi.iamss.androidreview.R;

public class MyTextViewActivity extends Activity{
    MyTextView mTextView;


    private static final String TAG = "sunqi_log";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytextview_layout);

        mTextView = findViewById(R.id.mytextview);
        findViewById(R.id.btn_request_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.requestLayout();
            }
        });
        findViewById(R.id.btn_invalidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.invalidate();
            }
        });

    }
}
