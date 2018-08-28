package com.review.sunqi.iamss.androidreview.okhttp_demo.advanced;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunqi on 2018/8/24.
 */

public class TokenHeaderInterceptor implements Interceptor {
    
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        Log.e("sunqi_log", "TokenHeaderInterceptor HttpUrl = " + url.host());

//        Response originalResponse = chain.proceed(request);
        return chain.proceed(request);
    }
}
