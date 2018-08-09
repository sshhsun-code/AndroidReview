package com.review.sunqi.iamss.androidreview.flow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/8/8.
 */

public class FlowTestActivity extends Activity {

    private Context mContext;
    private TextFlowLayout text_flow_layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout_show);
        mContext = this;
        text_flow_layout = findViewById(R.id.text_flow_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] localHotWorlds = new String[]{"王者荣耀", "大话西游", "决战！平安京", "率土之滨", "迷雾求生", "绝地求生：刺激战场",
                "纪念碑谷2", "终结者2：审判日", "穿越火线-枪战王者","鲲仙人","天神降临","少女使命","太极崛起",
                "梦幻西游手游", "三少爷的剑", "镇魔曲网页版", "血盟荣耀", "大天神", "传奇霸业", "绝世仙王", "鹤鸣九霄", "驯鹤记"};
        initHotWorldsView(localHotWorlds);

    }

    int viewHeight;
    int paddingLeft;
    private void initHotWorldsView(String[] hotWorlds) {
        if (text_flow_layout == null) {
            return;
        }
        text_flow_layout.removeAllViews();
        viewHeight = DensityUtil.dip2px(mContext, 27);
        paddingLeft = DensityUtil.dip2px(mContext, 13);
        int posHW = 0;
        for (String data : hotWorlds) {
            TextView textView = getSuggestTextView(data, posHW);
            text_flow_layout.addView(textView);
            ++posHW;
        }
    }

    private TextView getSuggestTextView(String name, final int posHW) {
        final TextView textView = new TextView(mContext);
        textView.setHeight(viewHeight);
        textView.setText(name);
        textView.setTextColor(0xFF3A3A3A);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); //22SP
        textView.setPadding(paddingLeft, 0, paddingLeft, 0);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.selector_rectangle_gray_no_stroke));

        return textView;
    }

}
