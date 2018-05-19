package com.review.sunqi.iamss.androidreview.fragment_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.review.sunqi.iamss.androidreview.R;

/**
 * Created by sunqi on 2018/5/19.
 */

public class Fragment1 extends BaseFragment{

    private TextView tips;
    private String mActivityStr;
    private String mToastStr;
    private ProcessListener mProcessListener;

    @Override
    protected void handleBundle(Bundle bundle) {
        mActivityStr = bundle.getString("key");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToastStr = mHostActivity.getDataTag(this);
        mProcessListener = mHostActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = LayoutInflater.from(mContext);
        if (mRootView == null) {
            mRootView = mInflater.inflate(R.layout.home_fragment, null);
        }
        tips = mRootView.findViewById(R.id.fragment1_text);
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        tips.setText(mActivityStr);
        Toast.makeText(mContext, mToastStr, Toast.LENGTH_LONG).show();
        if (mProcessListener != null) {
            mProcessListener.onDone("HomeFragment 加载完成");
        }
        if (mIsVisibility) {
            mHostActivity.getFragment2().method1();
            mHostActivity.getFragment2().method2();
        }
    }

    @Override
    public void method1() {
        Log.e("sunqi_log", "Fragment1 中 method1调用");
    }

    @Override
    public void method2() {
        Log.e("sunqi_log", "Fragment1 中 method2调用");
    }

    public interface ProcessListener {
        void onDone(String done);
    }
}
