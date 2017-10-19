package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.DiagnosisTeamBean;
import com.embracesource.yilianti.bean.DoctorBean;
import com.embracesource.yilianti.bean.HospitalBean;
import com.embracesource.yilianti.bean.SimpleBean;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
//@Component(modules = AppModule.class)
public interface ApplyDiagnosisViewModelCallBack extends BaseViewModelCallBack {
     void getBaseDataOK(ApplyDiagnosisGoalBean response);

    void submitApplyDiagnosisOK(SimpleBean response);

    void getDiagnosisTeamOK(DiagnosisTeamBean response);

    void changeHospitalListOK(HospitalBean response);

    void changeDoctorListOK(DoctorBean response);
}
