package com.embracesource.yilianti.common.http;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.LoginBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;

import io.reactivex.Observable;

public interface ApiInterface {

    Observable<LoginBean> login(String userName, String pwd);

    Observable<MyLaunchListBean> getMyParticipateList(int pageNum, int pageSize);

    Observable getMyLaunchList(int pageNum, int pageSize);

    Observable getApplyDiagnosisDetail(int id, String flag);

    Observable submitApplyDiagnosis(ApplyDiagnosisRequestBean bean);

    Observable getDiagnosisTeam();

    Observable changeHospitalList();

    Observable<ApplyDiagnosisGoalBean> getBaseData(String code);

    Observable changeDoctorList(int groupId);

    Observable<SimpleBean> diagnosisDetailsendPass_2(int id, int available);

    Observable diagnosisDetailsendUnPass_2(int id, int available, String content);

    Observable selectUserRole();

    Observable getNeedHandleList(int pageNum, int pageSize) ;


//    Observable<LoginBean> login1(String userName, String pwd);
}