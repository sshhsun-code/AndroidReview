package com.review.sunqi.iamss.androidreview.animation_test.view_animation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/10/15.
 */

public class ViewAnimationTestAct extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_test);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.start).startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.start).startAnimation(animation);
            }
        });

    }

    private void layoutAnimationTest(Context context, Animation animation) {
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        new ListView(ViewAnimationTestAct.this).setLayoutAnimation(controller);

    }
}
