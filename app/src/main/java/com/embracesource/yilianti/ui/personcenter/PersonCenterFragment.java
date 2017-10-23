package com.embracesource.yilianti.ui.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.UserInfoBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.FragmentPersonCenterBinding;
import com.embracesource.yilianti.ui.base.AacFragment;
import com.embracesource.yilianti.ui.base.BaseFragment;
import com.embracesource.yilianti.ui.loginregister.UpdatePwdActivity;
import com.embracesource.yilianti.util.StringUtils;

import io.reactivex.annotations.NonNull;

/**
 * OtherThemeFragment <br/>
 * Created by xiaqiulei on 2015-12-30.
 */
public class PersonCenterFragment extends AacFragment<FragmentPersonCenterBinding> implements View.OnClickListener {
    private Api api;

//    private static final String EXTRA_ITEM = "themeItem";

    public static PersonCenterFragment newInstance() {
        PersonCenterFragment fragment = new PersonCenterFragment();
        Bundle args = new Bundle();
//        args.putSerializable(EXTRA_ITEM, themeItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_person_center;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        api = new Api();
        getUserInfo();
        binding.llUpdatePwd.setOnClickListener(this);
    }

    private void getUserInfo() {
        getBaseActivityInstance().showDialog();
        api.getUserInfo().subscribe(new BaseFragment.MyObserver<UserInfoBean>() {
            @Override
            public void onNextUI(@NonNull UserInfoBean userInfoBean) {
                if (userInfoBean.isSuccess()) {
                    if (userInfoBean.getData() == null || StringUtils.isNullorEmpty(userInfoBean.getData().getId() + "")) {
                        getBaseActivityInstance().showToast(getString(R.string.msg_data_error));
                        return;
                    }
                    myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.userId, userInfoBean.getData().getId()));
                } else {
                    getBaseActivityInstance().showToast(getString(R.string.msg_data_error));
                }

            }
        });
    }


    public void updatePwd(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_update_pwd:
                Intent intent =new Intent(getActivity(), UpdatePwdActivity.class);
                startActivity(intent);
                break;
        }
    }
}