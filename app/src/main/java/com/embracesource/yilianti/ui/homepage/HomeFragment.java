package com.embracesource.yilianti.ui.homepage;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.biz.pojo.bean.ThemeItem;
import com.embracesource.yilianti.databinding.FragmentHomeBinding;
import com.embracesource.yilianti.ui.base.AacFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * OtherThemeFragment <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class HomeFragment extends AacFragment<FragmentHomeBinding> {

    private static final String EXTRA_ITEM = "themeItem";

    public static HomeFragment newInstance(ThemeItem themeItem) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ITEM, themeItem);
        fragment.setArguments(args);
        return fragment;
    }

    private ThemeItem themeItem;


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        themeItem = (ThemeItem) getArguments().getSerializable(EXTRA_ITEM);

        List localImages = new ArrayList();
        //获取本地的图片
        for (int position = 0; position < 7; position++) {
//            localImages.add(getResId("ic_test_" + position, R.drawable.class));
            localImages.add(getResId("haibao", R.drawable.class));
        }

   /*     ViewModelProviders.of(getActivity(), viewModelFactory())
                .get(HomeViewModel.class)
                .initConvenientBanner(binding.convenientBanner,localImages);*/
//        java.lang.IllegalArgumentException: unknown model class class com.embracesource.yilianti.ui.homepage.HomeViewModel
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}