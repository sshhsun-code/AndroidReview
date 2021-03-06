package com.review.sunqi.iamss.androidreview.testView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.leakcanary_test.TestHelper;

public class MyTextViewActivity extends Activity{
    MyTextView mTextView;

    TestHandler handler;

    private static final String TAG = "sunqi_log";

    private String[] strs = {
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
            "asasasasasa",
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytextview_layout);
        TestHelper.getmInstance(this).testMethod();
        handler = new TestHandler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TestHelper.getmInstance(MyTextViewActivity.this).testMethod();
            }
        }, 60 * 1000);

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

    class TestHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }
    }
}
