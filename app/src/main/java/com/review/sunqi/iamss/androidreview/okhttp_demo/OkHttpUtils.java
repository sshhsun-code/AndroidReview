package com.review.sunqi.iamss.androidreview.okhttp_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sunqi on 2018/8/22.
 */

public class OkHttpUtils<T extends Object> {

    public static final int METHOD_GET = 1;
    public static final int METHOD_POST_JSON = 1;

    private static final class InnerInstance {
        public static OkHttpUtils mInstance = new OkHttpUtils();
    }

    public static OkHttpUtils getInstance() {
        return InnerInstance.mInstance;
    }

    public OkHttpUtils() {

    }

    public void request(String url, Map<String, Object> paramsMap, Class<T> tClass, final HttpListener listener, int method) {

        if (method == METHOD_GET) {
            get(url, paramsMap, tClass,listener);
        } else if (method == METHOD_POST_JSON) {
            postJson(url, paramsMap, tClass, listener);
        }
    }

    private void postJson(String url, Map<String, Object> paramsMap, Class<T> tClass, final HttpListener listener) {


    }

    private void get(String url, Map<String, Object> paramsMap, final Class<T> tClass, final HttpListener listener) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        String getUrl = toUrl(paramsMap, url);
        OkHttpClient client = OkHttpClientMgr.getIns().getClient();
        Request request = new Request.Builder()
                .url(getUrl)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    listener.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if (listener != null) {
                        listener.onFailure(new IOException(response.message()));
                    }
                    return;
                }
                T t = null;
                try {
                    // 注意：response.body().string()只能调用一次，第二次就会失败
                    byte[] data = response.body().bytes();
                    t = createData(tClass, data);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    response.close();
                }

                if (listener != null) {
                    listener.onSuccess(t);
                }
            }
        });
    }

    private T createData(Class<T> tClass, byte[] data) throws UnsupportedEncodingException, IllegalAccessException, InstantiationException {
        if (tClass.equals(String.class)) {
            return (T) new String(data, "UTF-8");
        }
        if (tClass.equals(Bitmap.class)) {
            return (T) BitmapFactory.decodeByteArray(data, 0, data.length);
        }
        return (T)data;
    }

    private String toUrl(Map<String, Object> params, String url) {
        if (params == null || params.isEmpty() || TextUtils.isEmpty(url)) {
            return url;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object val = entry.getValue();
            sb.append(entry.getKey()).append("=").append(val);
            if (iter.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }


    public interface HttpListener<T> {
        void onSuccess(T response);

        void onFailure(Throwable throwable);
    }
}
