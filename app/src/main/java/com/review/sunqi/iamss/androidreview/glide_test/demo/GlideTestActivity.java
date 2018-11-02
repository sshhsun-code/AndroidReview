package com.review.sunqi.iamss.androidreview.glide_test.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.review.sunqi.iamss.androidreview.R;
import com.review.sunqi.iamss.androidreview.glide_test.glide.ImageLoader;

public class GlideTestActivity extends Activity {

    ImageView img = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_layout);

        img = findViewById(R.id.img_show);
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Target<GlideDrawable> target =  ImageLoader.loadImage(GlideTestActivity.this, "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg",
                        img);
            }
        });
    }
}
