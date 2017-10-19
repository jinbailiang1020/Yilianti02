package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.common.http.ApiInterface;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.ApplyDiagnosisViewModelCallBack;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class ApplyDiagnosisViewModel02 extends ViewModel{

    private final ApiInterface api;
    private  ApplyDiagnosisViewModelCallBack callBack;
//    @Inject
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
        api.submitApplyDiagnosis(bean).subscribe(new BaseActivity.MyObserver<SimpleBean>() {
            @Override
            public void onNext(@NonNull SimpleBean response) {
                super.onNext(response);
                callBack.submitApplyDiagnosisOK(response);//
            }

        });
    }

    public void getDiagnosisTeam() {
        api.getDiagnosisTeam().subscribe(new BaseActivity.MyObserver<DiagnosisTeamBean>() {
            @Override
            public void onNext(@NonNull DiagnosisTeamBean response) {
                super.onNext(response);
                callBack.getDiagnosisTeamOK(response);
            }

        });
    }

    public void changeHospitalList() { //获得医院
        api.changeHospitalList().subscribe(new BaseActivity.MyObserver<HospitalBean>() {
            @Override
            public void onNext(@NonNull HospitalBean response) {
                super.onNext(response);
                callBack.changeHospitalListOK(response);
            }
        });
    }

    public void changeDoctorList(int groupId) {//医生列表接口
        api.changeDoctorList(groupId).subscribe(new BaseActivity.MyObserver<DoctorBean>() {
            @Override
            public void onNext(@NonNull DoctorBean response) {
                super.onNext(response);
                callBack.changeDoctorListOK(response);
            }
        });
    }
}
