package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.common.imagepicker.PicassoImageLoader;
import com.embracesource.yilianti.common.pickerview.GetJsonDataUtil;
import com.embracesource.yilianti.common.pickerview.JsonBean;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;
import com.embracesource.yilianti.viewmodel.LoginViewModelCallBack;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.loader.ImageLoader;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

import static android.R.attr.name;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class ApplyDiagnosisViewModel02 extends ViewModel{

    private final ApplyDiagnosisViewModelCallBack callBack;
    private final Api api;

    ApplyDiagnosisViewModel02(ApplyDiagnosisViewModelCallBack callBack) {
        this.callBack = callBack;
        api = new Api();
    }

    public void getBaseData(String code) {
        api.getBaseData(code).subscribe(new BaseActivity.MyObserver<ApplyDiagnosisGoalBean>() {
            @Override
            public void onNext(@NonNull ApplyDiagnosisGoalBean response) {
                super.onNext(response);
                callBack.getBaseDataOK(response);
            }

        });
    }

}
