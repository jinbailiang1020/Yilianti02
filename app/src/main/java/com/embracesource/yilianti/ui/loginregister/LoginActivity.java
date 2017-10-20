package com.embracesource.yilianti.ui.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.embracesource.yilianti.R;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.UserTypeBean;
import com.embracesource.yilianti.common.http.RetrofitConfig;
import com.embracesource.yilianti.common.memory.MyPrefrences;
import com.embracesource.yilianti.databinding.ActivityLoginBinding;
import com.embracesource.yilianti.ui.MainActivity;
import com.embracesource.yilianti.ui.base.AacBaseActivity;
import com.embracesource.yilianti.ui.otherrole.OtherMainActivity;
import com.embracesource.yilianti.viewmodel.LoginViewModelCallBack;
import com.orhanobut.logger.Logger;

public class LoginActivity extends AacBaseActivity<ActivityLoginBinding> implements LoginViewModelCallBack {

    private LoginViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new LoginViewModel(LoginActivity.this);
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
        String msg;
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
        viewModel.login(userName, pwd);

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
        RetrofitConfig.setJsessionid(response.getData().getSessionid(), mContext);//设置Sessionid
        if (binding.cbRememberPwd.isChecked()) {
            myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.pwd, pwd));
        } else {
            myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.pwd, ""));
        }
        //判断登录角色
        viewModel.selectUserRole();
//        http://localhost:8002/account/selectUserRole
    }

    @Override
    public void selectUserRoleOK(UserTypeBean response) {
        try {
            int role = response.getData().get(0);
            Logger.d("UserTypeBean --->"+role);
            if (response.isSuccess() && role > 0) {
                myPrefrences.putValues(new MyPrefrences.ContentValue(MyPrefrences.Key.role, role));
                /**
                 * 用户类型
                 * 1 用户
                 * 2 医生 、、、
                 * 3 患者
                 * 4 游客
                 * 5 医务处、、、
                 * 6 客服 、、、
                 * 7 系统管理员
                 */
                switch (role){
                    case 2:
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                    case 6:
                        Intent intent1 = new Intent(LoginActivity.this, OtherMainActivity.class);
                        startActivity(intent1);
                        break;
                }
            } else {
            showToast(getString(R.string.get_user_type_error));
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast(getString(R.string.get_user_type_error));
        }
    }
}
