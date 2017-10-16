package com.embracesource.yilianti.common.http;

import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.ui.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

public interface ApiInterface {

    Observable<LoginBean> login(String userName, String pwd);
//    Observable<LoginBean> login1(String userName, String pwd);
}