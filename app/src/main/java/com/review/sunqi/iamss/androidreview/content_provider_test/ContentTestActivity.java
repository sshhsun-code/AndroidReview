package com.review.sunqi.iamss.androidreview.content_provider_test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by sunqi on 2018/6/11.
 */

public class ContentTestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContentResolver().call(null, "", "", null);
    }
}
