package com.review.sunqi.iamss.androidreview.fragment_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.review.sunqi.iamss.androidreview.R;

import java.util.ArrayList;

/**
 * Created by sunqi on 2018/5/19.
 */

public class HolderActivity extends FragmentActivity implements Fragment1.ProcessListener{

    private ViewPager mHolderViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Bundle mBundle;
    private ArrayList<BaseFragment> fragmentList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);
        initView();
        initData();
    }

    private void initData() {
        initBundle();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragmentList = new ArrayList<>();
        fragment1.setArguments(mBundle);//通信方式1：Activity向Fragment传值
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        mHolderViewPager.setAdapter(mFragmentPagerAdapter);
    }

    public Fragment1 getFragment1() {//通信方式4：Fragment与Fragment之间传值
        return fragment1;
    }

    public Fragment2 getFragment2() {//通信方式4：Fragment与Fragment之间传值
        return fragment2;
    }

    public String getDataTag(BaseFragment fragment) {//通信方式2：Activity向Fragment传值
        if (fragment instanceof Fragment1) {
            return "主Fragment";
        }
        return "Fragment";
    }

    /**
     * 初始化传递的参数
     */
    private void initBundle() {
        mBundle = new Bundle();
        mBundle.putString("key", "HomeFragment");
    }

    private void initView() {
        mHolderViewPager = findViewById(R.id.holder_view_pager);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDone(String done) {//通信方式3：Fragment向Activity传值
        Log.e("sunqi_log", "HoldActivity :" + done);
    }
}
