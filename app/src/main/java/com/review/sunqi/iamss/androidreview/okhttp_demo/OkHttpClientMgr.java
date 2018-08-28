package com.review.sunqi.iamss.androidreview.okhttp_demo;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.review.sunqi.iamss.androidreview.okhttp_demo.advanced.RequestEncryptInterceptor;
import com.review.sunqi.iamss.androidreview.okhttp_demo.advanced.TokenHeaderInterceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sunqi on 2018/8/22.
 */

public class OkHttpClientMgr {
    private static final long FILE_READ_TIME_MIN = 5L;
    private static final long FILE_WRITE_TIME_MIN = 5L;
    private static final long DEFAULT_CONNECT_TIME_SEC = 10L;
    private static final long FILE_CONNECT_TIME_SEC = 100L;

    private static class Inner {
        private static final OkHttpClientMgr mIns = new OkHttpClientMgr();
    }

    private SparseArray<OkHttpClient> mClients = new SparseArray<>(CLIENT_COUNT);

    private OkHttpClientMgr() {
    }

    public static OkHttpClientMgr getIns() {
        return Inner.mIns;
    }

    public
    @NonNull
    OkHttpClient getClient() {
        OkHttpClient client = mClients.get(CLIENT_DEFAULT);
        if (client == null) {
            initClient(CLIENT_DEFAULT, null);
        }
        return mClients.get(CLIENT_DEFAULT);
    }

    private void initClient(@Client int client, ClientBuilder builder) {
        OkHttpClient.Builder defaultBuilder;
        if (client == CLIENT_DEFAULT) {
            defaultBuilder = new OkHttpClient.Builder().connectTimeout(DEFAULT_CONNECT_TIME_SEC, TimeUnit.SECONDS);
//            .addInterceptor(getLoggerInterceptor())
        } else if (client == CLIENT_FILE) {
            defaultBuilder = getClient().newBuilder()
                    .connectTimeout(FILE_CONNECT_TIME_SEC, TimeUnit.SECONDS).writeTimeout(FILE_WRITE_TIME_MIN, TimeUnit.MINUTES).readTimeout(FILE_READ_TIME_MIN, TimeUnit.MINUTES);
        } else {
            defaultBuilder = getClient().newBuilder();
        }
        OkHttpClient httpClient = createClient(defaultBuilder, builder);
        mClients.put(client, httpClient);
    }

    private OkHttpClient createClient(OkHttpClient.Builder builder, ClientBuilder clientBuilder) {
        OkHttpClient client;
        if (clientBuilder != null) {
            clientBuilder.buildClient(builder);
        }
        client = builder
                .addInterceptor(new TokenHeaderInterceptor())
                .addInterceptor(new RequestEncryptInterceptor())
                .build();
        return client;
    }

    public
    @NonNull
    OkHttpClient getClient(@Client int client) {
        OkHttpClient httpClient = mClients.get(client);
        if (httpClient == null) {
            initClient(client, null);
            httpClient = mClients.get(client);
            if (httpClient == null) {
                return getClient();
            }
        }
        return httpClient;
    }

    private static final int CLIENT_COUNT = 4;
    public static final int CLIENT_DEFAULT = 0;
    public static final int CLIENT_API = 1;
    public static final int CLIENT_IMAGE_REQUEST = 2;
    public static final int CLIENT_FILE = 3;

    @IntDef({CLIENT_DEFAULT, CLIENT_API, CLIENT_IMAGE_REQUEST, CLIENT_FILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Client {
    }

    public interface ClientBuilder {
        void buildClient(OkHttpClient.Builder builder);
    }
}
