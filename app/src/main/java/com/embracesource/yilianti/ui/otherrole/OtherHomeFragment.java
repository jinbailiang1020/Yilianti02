package com.embracesource.yilianti.ui.otherrole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.databinding.FragmentOtherHomeBinding;
import com.embracesource.yilianti.ui.base.AacFragment;
import com.embracesource.yilianti.ui.homepage.HomeViewModel;
import com.embracesource.yilianti.ui.homepage.diagnosis.DiagnosisPictureActivity;
import com.embracesource.yilianti.util.BaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * OtherThemeFragment <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class OtherHomeFragment extends AacFragment<FragmentOtherHomeBinding> {

    private static final String EXTRA_ITEM = "themeItem";

    public static OtherHomeFragment newInstance() {
        OtherHomeFragment fragment = new OtherHomeFragment();
        Bundle args = new Bundle();
//        args.putSerializable(EXTRA_ITEM, themeItem);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_other_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        themeItem = (ThemeItem) getArguments().getSerializable(EXTRA_ITEM);
        List localImages = new ArrayList();
        //获取本地的图片
        for (int position = 0; position < 7; position++) {
            localImages.add(BaseUtils.getResId("haibao", R.drawable.class));
        }

        new HomeViewModel().initConvenientBanner(binding.convenientBanner,localImages);


//会诊--图文
        binding.tvWaitHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiagnosisPictureActivity.class);
                intent.putExtra("", "");
                startActivity(intent);
            }
        });
}


}