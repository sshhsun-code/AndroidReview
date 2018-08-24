package com.review.sunqi.iamss.androidreview.okhttp_demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/8/23.
 */

public class OkHttpDemoActivity extends Activity {

    private static final String HTTPURL = "https://blog.csdn.net/u014165633/article/details/52880841";

    private static Handler mHandler;

    TextView textView;
    ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_demo);
        textView = findViewById(R.id.http_result_show);
        mImageView = findViewById(R.id.iv_http_result_show);
        findViewById(R.id.http_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestBitmap();
                requestString();
            }
        });

        mHandler = new MyHandler();

    }

    private void requestString() {
        OkHttpUtils.getInstance().request(HTTPURL, null, String.class, new OkHttpUtils.HttpListener<String>() {
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

    private void requestBitmap() {
        String url = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1396620736,2302536349&fm=11&gp=0.jpg";
        OkHttpUtils.getInstance().request(url, null, Bitmap.class, new OkHttpUtils.HttpListener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap response) {
                Message msg = mHandler.obtainMessage(2);
                msg.obj = response;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, OkHttpUtils.METHOD_GET);
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String result = msg.getData().getString("response");
                    textView.setText(result);
                    break;
                case 2:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mImageView.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
