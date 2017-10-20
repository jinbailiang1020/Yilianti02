package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public interface DiagnosisPictureCallBack {

    void getMyLaunchListOK(MyLaunchListBean response, int pageNum);

    void getMyParticipateListOK(MyLaunchListBean response, int pageNum);


    void getHospitalListOK(HospitalWaitHandleListBean response, int pageNum);
}
