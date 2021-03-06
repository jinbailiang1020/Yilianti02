package com.embracesource.yilianti.ui.otherrole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.UserType;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.FragmentOtherHomeBinding;
import com.embracesource.yilianti.ui.base.AacFragment;
import com.embracesource.yilianti.ui.homepage.HomeViewModel;
import com.embracesource.yilianti.ui.homepage.diagnosis.DiagnosisPictureActivity;
import com.embracesource.yilianti.ui.homepage.needhandle.NeedHandleActivity;
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
        final int role = myPrefrences.getInt(MyPrefrences.Key.role);
        switch (role) {
            case UserType.Medical_Service:
                binding.tvWorkbench.setText(getString(R.string.Medical_Service_Workbench));
                break;
            case UserType.Customer_Service:
                binding.tvWorkbench.setText(getString(R.string.Customer_Service_Workbench));
                break;
        }
        new HomeViewModel().initConvenientBanner(binding.convenientBanner, localImages);


        //待办事项
        binding.tvWaitHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (role) {
                    case UserType.Medical_Service:
                        Intent intent = new Intent(getActivity(), NeedHandleActivity.class);
                        intent.putExtra("", "");
                        startActivity(intent);
                        break;
                    case UserType.Customer_Service:
                        Intent intent1 = new Intent(getActivity(), DiagnosisPictureActivity.class);
                        intent1.putExtra("", "");
                        startActivity(intent1);
                        break;
                }
            }
        });
    }


}