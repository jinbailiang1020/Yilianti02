package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.BaseBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class ApplyDiagnosisViewModel02 extends ViewModel{

    private final Api api;
    private  ApplyDiagnosisViewModelCallBack callBack;
    @Inject
    public ApplyDiagnosisViewModel02(ApplyDiagnosisViewModelCallBack callBack) {
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

    public void submit(ApplyDiagnosisRequestBean bean) {
        api.submitApplyDiagnosis(bean).subscribe(new BaseActivity.MyObserver<BaseBean>() {
            @Override
            public void onNext(@NonNull BaseBean response) {
                super.onNext(response);
                callBack.submitApplyDiagnosisOK(response);
            }

        });
    }
}
