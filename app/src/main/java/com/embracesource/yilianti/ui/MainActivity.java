package com.embracesource.yilianti.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import javax.inject.Inject;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.app.InjectHelp;
import com.embracesource.yilianti.biz.api.impl.retrofit.IZhihuRetrofitApi;
import com.embracesource.yilianti.biz.pojo.bean.ThemeItem;
import com.embracesource.yilianti.databinding.ActivityMainBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.homepage.HomeFragment;

public class MainActivity extends AacBaseActivity<ActivityMainBinding> {

    LinearLayout llMainMenuContainer;

    @Inject
    IZhihuRetrofitApi zhihuRetrofitApi;

    ThemeItem currentThemeItem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectHelp.appComponent().inject(this);
        setTitleLeftViewGone();
        updateFragment();
/*        init();
        initSlideMenu();
        loadData();*/
    }

    void updateFragment() {
        String tag = "content_fragment";
        Fragment fragment;
  /*      if (currentThemeItem != null) {
            fragment = HomeFragment.newInstance(currentThemeItem);
        } else {
            fragment = HomeFragment.newInstance();
        }*/
        fragment = HomeFragment.newInstance(currentThemeItem);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, fragment, tag)
                .commitAllowingStateLoss();
    }



}