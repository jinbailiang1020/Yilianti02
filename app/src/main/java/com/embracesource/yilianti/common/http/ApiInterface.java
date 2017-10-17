package com.embracesource.yilianti.common.http;

import com.embracesource.yilianti.bean.ApplyDiagnosisRequestBean;
import com.embracesource.yilianti.bean.LoginBean;

import io.reactivex.Observable;

public interface ApiInterface {

    Observable<LoginBean> login(String userName, String pwd);

    Observable getMyLaunchList(int pageNum,int pageSize);

    Observable getApplyDiagnosisDetail(String id,String flag);

    Observable submitApplyDiagnosis(ApplyDiagnosisRequestBean bean);

//    Observable<LoginBean> login1(String userName, String pwd);
}