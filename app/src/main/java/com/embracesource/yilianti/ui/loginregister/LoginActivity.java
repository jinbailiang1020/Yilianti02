package com.embracesource.yilianti.ui.loginregister;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.databinding.ActivityLoginBinding;
import com.embracesource.yilianti.ui.MainActivity;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.BaseViewModelCallBack;
import com.embracesource.yilianti.viewmodel.LoginViewModelCallBack;

import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AacBaseActivity<ActivityLoginBinding> implements LoginViewModelCallBack{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void login(View view) {
        String msg = null;
        String pwd = binding.etPwd.getText().toString();
        String userName = binding.etPhone.getText().toString();
   /*     if (userName.length() == 0) {
            msg = "请输入手机号";
            showToast(msg);
            return;
        }
        if (pwd.length() == 0) {
            msg = "请输入密码";
            showToast(msg);
            return;
        }*/
        new LoginViewModel(LoginActivity.this).login(userName,pwd);

    }

    @Override
    public void loginOK(LoginBean response) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
