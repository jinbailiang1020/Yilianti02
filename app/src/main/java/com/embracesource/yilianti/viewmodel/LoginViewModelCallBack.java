package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.UserTypeBean;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface LoginViewModelCallBack extends BaseViewModelCallBack {
     void loginOK(LoginBean response, String name, String pwd) ;

    void selectUserRoleOK(UserTypeBean response);
}
