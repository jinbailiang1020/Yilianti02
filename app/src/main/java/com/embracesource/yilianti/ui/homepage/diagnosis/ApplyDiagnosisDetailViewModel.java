package com.embracesource.yilianti.ui.homepage.diagnosis;

import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;
import com.embracesource.yilianti.viewmodel.BaseViewModel;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ApplyDiagnosisDetailViewModel extends BaseViewModel {
    private final ApplyDiagnosisDetailCallBack callBack;

    public ApplyDiagnosisDetailViewModel(ApplyDiagnosisDetailCallBack callBack) {
        this.callBack = callBack;
    }

    public void getDetail(int id,String flag) {
        api.getApplyDiagnosisDetail(id,flag).subscribe(new BaseActivity.MyObserver<ApplyDiagnosisDetailBean>() {//ApplyDiagnosisDetailBean
            @Override
            public void onNext(@NonNull ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
                super.onNext(applyDiagnosisDetailBean);
                callBack.getApplyDiagnosisDetailOK(applyDiagnosisDetailBean);
            }
        });
    }
}
