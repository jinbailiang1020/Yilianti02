package com.embracesource.yilianti.ui.loginregister;

import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.ActivityUpdatePwdBinding;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.util.FormatUtils;

import io.reactivex.annotations.NonNull;

public class UpdatePwdActivity extends AacBaseActivity<ActivityUpdatePwdBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void updatePwd(View view) {
        String originalPwd = binding.etOriginalPwd.getText().toString();
        String newPwd = binding.etNewPwd.getText().toString();
        String surePwd = binding.etSurePwd.getText().toString();
        if (FormatUtils.isInValidPwd(originalPwd)) {
            showToast(getString(R.string.msg_pwd));
            return;
        }
        if (FormatUtils.isInValidPwd(newPwd)) {
            showToast(getString(R.string.msg_pwd));
            return;
        }

        if (!newPwd.equals(surePwd)) {
            showToast(getString(R.string.msg_sure_pwd));
            return;
        }

        Api api = new Api();
        int userId = myPrefrences.getInt(MyPrefrences.Key.userId);
        showDialog();
        api.updatePwd(userId+"", originalPwd, newPwd).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean response) {
                if(response.isSuccess()){
                    showToast(getString(R.string.msg_update_pwd_ok));
                    UpdatePwdActivity.this.finish();
                }
            }
        });
    }
}
