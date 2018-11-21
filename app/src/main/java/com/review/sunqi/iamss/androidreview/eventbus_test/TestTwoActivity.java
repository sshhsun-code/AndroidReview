package com.review.sunqi.iamss.androidreview.eventbus_test;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Choreographer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TestTwoActivity extends Activity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long l) {

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void helloEventBus(Integer message) { //方法名任意，这里的参数和  EventBus.getDefault().post(111);对应即可
        Log.e("sunqi_log", "message : " + message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMsgGet(MessageEvent event) {//私有方法不支持被注册
        Log.e("sunqi_log", "event : " + event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
