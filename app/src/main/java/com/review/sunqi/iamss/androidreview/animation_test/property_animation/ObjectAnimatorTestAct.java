package com.review.sunqi.iamss.androidreview.animation_test.property_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.review.sunqi.iamss.androidreview.R;

/**
 * 属性动画测试
 * Created by sunqi on 2018/10/15.
 */

public class ObjectAnimatorTestAct extends Activity{

//    Button start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation2);
//        start = findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showButtonWidthAnimator(view);
////                showAnimatorSet(view);
////                showObjectAnimation(view);
////                showColorValueAnimation(view);
//            }
//        });
    }

    private void showObjectAnimation(Object view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 100);
        animator.setDuration(3000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                showColorValueAnimation(start);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        animator.start();
    }

    private void showColorValueAnimation(Object view) {
        ValueAnimator colorAnimator = ObjectAnimator.ofInt(view, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        colorAnimator.setDuration(3000);
        colorAnimator.setInterpolator(new LinearInterpolator());
        colorAnimator.setEvaluator(new IntEvaluator());
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimator.setRepeatMode(ValueAnimator.RESTART);
        colorAnimator.start();
    }

    private void showAnimatorSet(Object view) {

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 200),
                ObjectAnimator.ofFloat(view, "translationY", 100),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 1.5f),
                ObjectAnimator.ofFloat(view, "scaleY", 1, 0.5f)
        );
        set.setDuration(5000).start();
    }

    private void showButtonWidthAnimator(final View button) {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(button.getLayoutParams().width, 500);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int currentValue = (Integer) valueAnimator.getAnimatedValue();

                button.getLayoutParams().width = currentValue;

                button.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.start();
    }
}
