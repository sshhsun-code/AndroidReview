package com.review.sunqi.iamss.androidreview.okhttp_demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/8/23.
 */

public class OkHttpDemoActivity extends Activity {

    private static final String HTTPURL = "https://blog.csdn.net/u014165633/article/details/52880841";

    private static Handler mHandler;

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);
        textView = findViewById(R.id.http_result_show);
        findViewById(R.id.http_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.getInstance().request(HTTPURL, null, new OkHttpUtils.HttpListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        Message msg = mHandler.obtainMessage(1);
                        Bundle data = new Bundle();
                        data.putString("response", response);
                        msg.setData(data);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                    }
                }, OkHttpUtils.METHOD_GET);
            }
        });

        mHandler = new MyHandler();

    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.getData().getString("response");
            textView.setText(result);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
