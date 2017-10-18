package com.embracesource.yilianti.ui.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.common.http.RetrofitConfig;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.ActivityLoginBinding;
import com.embracesource.yilianti.ui.MainActivity;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.viewmodel.LoginViewModelCallBack;

public class LoginActivity extends AacBaseActivity<ActivityLoginBinding> implements LoginViewModelCallBack {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
        if (!myPrefrences.getString(MyPrefrences.Key.pwd).isEmpty()) {
            binding.cbRememberPwd.setChecked(true);
        }
        binding.etPhone.setText(myPrefrences.getString(MyPrefrences.Key.userName));
        binding.etPwd.setText(myPrefrences.getString(MyPrefrences.Key.pwd));
    }

    public void login(View view) {
        String msg = null;
        String pwd = binding.etPwd.getText().toString();
        String userName = binding.etPhone.getText().toString();
        if (userName.length() == 0) {
            msg = "请输入手机号";
            showToast(msg);
            return;
        }
        if (pwd.length() == 0) {
            msg = "请输入密码";
            showToast(msg);
            return;
        }
        showDialog();
        new LoginViewModel(LoginActivity.this).login(userName, pwd);

    }

    @Override
    public void loginOK(LoginBean response, String name, String pwd) {
        if (response.isSuccess()) {
            loginSuccess(response, name, pwd);
        } else {
            //Todo Toast
        }

    }

    private void loginSuccess(LoginBean response, String name, String pwd) {
//        UserDao dao = AppContext.getInstance().getDaoSession().getUserDao();
//        dao.insert()
        myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.userName, name));
        myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.sessionid, response.getData().getSessionid()));
        RetrofitConfig.setJsessionid(response.getData().getSessionid(),mContext);//设置Sessionid
        if (binding.cbRememberPwd.isChecked()) {
            myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.pwd, pwd));
        } else {
            myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.pwd, ""));
        }
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
