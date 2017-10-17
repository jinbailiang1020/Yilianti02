package com.embracesource.yilianti.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.common.http.Api;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class BaseViewModel extends ViewModel {
   public  Api api = new Api();
}
