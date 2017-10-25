package com.embracesource.yilianti.ui.homepage.diagnosis;

import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisDetailCallBack;
import com.embracesource.yilianti.viewmodel.BaseViewModel;

import org.json.JSONObject;

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
            public void onNextUI(@NonNull ApplyDiagnosisDetailBean applyDiagnosisDetailBean) {
                callBack.getApplyDiagnosisDetailOK(applyDiagnosisDetailBean);
            }
        });
    }

    public void sendPass_2(int id) {
        api.diagnosisDetailsendPass_2(id, 2).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.sendPassOK(bean);
            }
        });
    }

    public void sendPass_3(int id) {
        api.diagnosisDetailsendPass_2(id, 3).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.sendPassOK(bean);
            }
        });
    }


    //专家团队转诊审核通过 referralAndConsultation/teamReply/referral/pass/{id}
    public void sendUnPass_expert(JSONObject json,int id) {
        api.diagnosisDetailSendUnPass_expert(json,id).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                super.onNext(bean);
                callBack.sendPassOK(bean);
            }
        });
    }


    public void sendUnPass_2(int id, String content) {
        //http://192.168.1.165:8002/referralAndConsultation/audit/{id}?available=2
        api.diagnosisDetailsendUnPass_2(id, 12, content).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.sendUnPassOK(bean);
            }
        });
    }

    public void sendUnPass_3(int id, String content) {
        //http://192.168.1.165:8002/referralAndConsultation/audit/{id}?available=2
        api.diagnosisDetailsendUnPass_2(id, 13, content).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.sendUnPassOK(bean);
            }
        });
    }

    public void huizhenSubmit(String diagnosisAdvice, int id) {
        api.huizhenSubmit(diagnosisAdvice, id).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.huizhenSubmitOK(bean);
            }
        });
    }

    public void changeToDiagnosis(int id, JSONObject jsonObject) {
        api.changeToDiagnosis(jsonObject, id).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNextUI(@NonNull SimpleBean bean) {
                callBack.huizhenSubmitOK(bean);
            }
        });
    }
}
