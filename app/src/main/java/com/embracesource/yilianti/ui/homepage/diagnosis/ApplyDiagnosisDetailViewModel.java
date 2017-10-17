package com.embracesource.yilianti.ui.homepage.diagnosis;

import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;
import com.embracesource.yilianti.viewmodel.BaseViewModel;

import org.json.JSONObject;

import io.reactivex.annotations.NonNull;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class ApplyDiagnosisDetailViewModel extends BaseViewModel {
    private final ApplyDiagnosisDetailCallBack callBack;

    public ApplyDiagnosisDetailViewModel(ApplyDiagnosisDetailCallBack callBack) {
        this.callBack = callBack;
    }

    public void getDetail(String id,String flag) {
        api.getApplyDiagnosisDetail(id,flag).subscribe(new BaseActivity.MyObserver<Response<JSONObject>>() {//ApplyDiagnosisDetailBean
            @Override
            public void onNext(@NonNull Response<JSONObject> applyDiagnosisDetailBean) {
                super.onNext(applyDiagnosisDetailBean);
                applyDiagnosisDetailBean.toString();
            }
        });
    }
}
