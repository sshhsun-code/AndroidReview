package com.review.sunqi.iamss.androidreview.diy_view.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/9/14.
 */

public class ViewActivity extends Activity {

    LinearLayout layout;
    Button btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
//        setContentView(R.layout.activity_view_scroller_test);

        layout = findViewById(R.id.layout);
        btn1 = findViewById(R.id.btn_scroll_to);
        btn2 = findViewById(R.id.btn_scroll_by);
        findViewById(R.id.btn_scroll_to).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.scrollTo(-100, -100);
            }
        });

        findViewById(R.id.btn_scroll_by).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                layout.scrollBy(-100, -100);
                btn2.scrollBy(-10, -10);
                /*
                *
                * 注意，scrollTo&scrollBy都只是对控件内部的内容进行滑动！！比如，我们对btn2进行滑动，发现，btn2的位置并没有改变，但是其text位置发生改变
                *
                * */
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
