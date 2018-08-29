package com.review.sunqi.iamss.androidreview.retrofit;

import retrofit2.Retrofit;

/**
 *
 * @author sunqi
 * @date 2018/8/29
 */

public class RetrofitUtils {

    private static Retrofit mRetrofit;

    public static Retrofit getInstance() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder().build();
        }
        return mRetrofit;
    }
}
