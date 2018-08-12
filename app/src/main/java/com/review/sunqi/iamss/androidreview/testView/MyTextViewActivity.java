package com.review.sunqi.iamss.androidreview.testView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.R;

public class MyTextViewActivity extends Activity{


    private static final String TAG = "sunqi_log";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytextview_layout);

        MyTextView textView = findViewById(R.id.mytextview);
        textView.measure(0, 0);
        Log.i(TAG, "imageView MeasuredWidth = " + textView.getMeasuredWidth());
        Log.i(TAG, "imageView MeasuredHeight = " + textView.getMeasuredHeight());

    }
}
