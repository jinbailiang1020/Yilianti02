package com.embracesource.yilianti.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.FragmentHomeBinding;
import com.embracesource.yilianti.ui.base.AacFragment;
import com.embracesource.yilianti.ui.homepage.diagnosis.DiagnosisPictureActivity;
import com.embracesource.yilianti.ui.homepage.needhandle.NeedHandleActivity;
import com.embracesource.yilianti.util.BaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * OtherThemeFragment <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class HomeFragment extends AacFragment<FragmentHomeBinding> {

    private static final String EXTRA_ITEM = "themeItem";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putSerializable(EXTRA_ITEM, themeItem);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        themeItem = (ThemeItem) getArguments().getSerializable(EXTRA_ITEM);
        List localImages = new ArrayList();
        //获取本地的图片
        for (int position = 0; position < 7; position++) {
            localImages.add(BaseUtils.getResId("haibao", R.drawable.class));
        }

        new HomeViewModel().initConvenientBanner(binding.convenientBanner, localImages);
//        binding.tvDiagnosisPicture.setTargetView( binding.tvDiagnosisPicture);
//        binding.tvDiagnosisPicture.setBadgeGravity(Gravity.TOP | Gravity.LEFT);
//        binding.tvDiagnosisPicture.setBadgeCount(8);
        //待办事项
        binding.tvNeedHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NeedHandleActivity.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });

        //会诊--图文
        binding.tvDiagnosisPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiagnosisPictureActivity.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });
    }


}