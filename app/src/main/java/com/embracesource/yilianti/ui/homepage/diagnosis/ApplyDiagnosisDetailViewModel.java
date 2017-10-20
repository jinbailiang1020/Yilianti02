package com.embracesource.yilianti.ui.homepage.diagnosis;

import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.SimpleBean;
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

    public void getDetail(int id, String flag) {
        api.getApplyDiagnosisDetail(id, flag).subscribe(new BaseActivity.MyObserver<ApplyDiagnosisDetailBean>() {//ApplyDiagnosisDetailBean
            @Override
            public void onNext(@NonNull ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
                super.onNext(applyDiagnosisDetailBean);
                callBack.getApplyDiagnosisDetailOK(applyDiagnosisDetailBean);
            }
        });
    }

    public void sendPass_2(int id) {
        api.diagnosisDetailsendPass_2(id, 2).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendPassOK(bean);
            }
        });
    }

    public void sendPass_3(int id) {
        api.diagnosisDetailsendPass_2(id, 3).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendPassOK(bean);
            }
        });
    }

    public void sendPass_4(int id) {
        api.diagnosisDetailsendPass_2(id, 3).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendPassOK(bean);
            }
        });
    }


    public void sendUnPass_2(int id, String content) {
        //http://192.168.1.165:8002/referralAndConsultation/audit/{id}?available=2
        api.diagnosisDetailsendUnPass_2(id, 12, content).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendUnPassOK(bean);
            }
        });
    }
    public void sendUnPass_3(int id, String content) {
        //http://192.168.1.165:8002/referralAndConsultation/audit/{id}?available=2
        api.diagnosisDetailsendUnPass_2(id, 13, content).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendUnPassOK(bean);
            }
        });
    }

}
