package com.review.sunqi.iamss.androidreview.fragment_test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sunqi on 2018/5/19.
 */

public abstract class BaseFragment extends Fragment implements GlobalDataInterface {

    protected View mRootView;
    protected HolderActivity mHostActivity;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected boolean mIsVisibility;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("sunqi_log", this + "onAttach");
        mHostActivity = (HolderActivity) getActivity();
        mContext = context;
        handleBundle(getArguments());
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//懒加载方案lazyLoad
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            mIsVisibility = true;
        } else {
            mIsVisibility = false;
        }

        mHandler = new Handler();
        handleLazyLoad(new Runnable() {
            @Override
            public void run() {
                lazyLoad();
            }
        });
    }

    protected void lazyLoad() {
        Log.e("sunqi_log", this + "lazyLoad");
        Log.e("sunqi_log", this + "mHostActivity = " + mHostActivity);
    }

    public void handleLazyLoad(Runnable runnable) {
        mHandler.postDelayed(runnable, 500);
    }

    /**
     * 处理传递的参数
     * @param bundle
     */
    protected void handleBundle(Bundle bundle) {

    }
}
