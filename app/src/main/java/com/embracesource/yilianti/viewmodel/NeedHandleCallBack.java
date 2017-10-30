package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public interface NeedHandleCallBack {

    void getMyLaunchListOK(MyLaunchListBean response, int pageNum);

    void getMyParticipateListOK(MyLaunchListBean response, int pageNum);


    void getNeedHandleListOK(HospitalWaitHandleListBean response, int pageNum);

    void customer_Service_nextStepOK(SimpleBean response);

    void getCustomerServiceListOK(CustomerServiceDiagnosisListBean response);
}
