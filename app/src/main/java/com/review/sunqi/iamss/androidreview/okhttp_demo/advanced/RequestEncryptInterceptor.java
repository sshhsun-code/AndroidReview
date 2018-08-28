package com.review.sunqi.iamss.androidreview.okhttp_demo.advanced;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sunqi on 2018/8/28.
 */

public class RequestEncryptInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();

        Log.e("sunqi_log", "RequestEncryptInterceptor RequestBody = " + body);
        return chain.proceed(request);
    }
}
