package com.review.sunqi.iamss.androidreview.dispatch_test;

import android.util.Log;
import android.view.MotionEvent;

public class LogUtils {

    private static final String ACTION_DOWN = "ACTION_DOWN";

    public static void logEvent(String who, String callBack, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("sunqi_log",who + " ----- " + callBack + " -----ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("sunqi_log",who + " ----- " + callBack + " -----ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("sunqi_log",who + " ----- " + callBack + " -----ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("sunqi_log",who + " ----- " + callBack + " -----ACTION_CANCEL");
                break;

        }
    }
}
