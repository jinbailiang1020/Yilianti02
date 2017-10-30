package com.embracesource.yilianti.ui.homepage.needhandle;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.CustomerServiceDiagnosisListBean;
import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.bean.SimpleBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.NeedHandleCallBack;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class NeedHandleViewModel extends ViewModel {
    private final Api api;
    private final NeedHandleCallBack callBack;

//    @Inject
    public NeedHandleViewModel(NeedHandleActivity NeedHandleActivity) {
        api = new Api();
        this.callBack = NeedHandleActivity;
    }

    public void  getMyLaunchList(final int pageNum,final int pageSize) {
        api.getMyLaunchList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<MyLaunchListBean>() {//
            @Override
            public void onNextUI(@NonNull MyLaunchListBean response) {
                callBack.getMyLaunchListOK(response,pageNum);//sss
            }

            @Override
            public void onErrorUI(Throwable e) {
                super.onErrorUI(e);
                callBack.getMyLaunchListOK(null,pageNum);//sss
            }
        });

    }

    public void getMyParticipateList(final int pageNum, int pageSize) {
        api.getMyParticipateList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<MyLaunchListBean>() {//
            @Override
            public void onNextUI(@NonNull MyLaunchListBean response) {
                callBack.getMyParticipateListOK(response,pageNum);//sss
            }

            @Override
            public void onErrorUI(Throwable e) {
                super.onErrorUI(e);
                callBack.getMyParticipateListOK(null,pageNum);//sss
            }
        });
    }

    //待办事项
    public void getNeedHandleList(final int pageNum, int pageSize) {
        //完成标识符flagFinish：0 未完成；1 已完成
//        http://192.168.1.165:8002/workbench/t    odo/list?pageNum={pageNum}&pageSize={pageSize}
        api.getNeedHandleList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<HospitalWaitHandleListBean>() {//
            @Override
            public void onNextUI(@NonNull HospitalWaitHandleListBean response) {
                callBack.getNeedHandleListOK(response,pageNum);//
            }
        });
    }

    public void nextStep(int id, int available) {
        api.customerService_nextStep(id,available).subscribe(new BaseActivity.MyObserver<SimpleBean>() {//
            @Override
            public void onNextUI(@NonNull SimpleBean response) {
                callBack.customer_Service_nextStepOK(response);//
            }
        });
    }

    public void getCustomerServiceList(int flagFinish) {//客服获取列表
        api.getCustomerServiceList(flagFinish).subscribe(new BaseActivity.MyObserver<CustomerServiceDiagnosisListBean>() {//
            @Override
            public void onNextUI(@NonNull CustomerServiceDiagnosisListBean response) {
                callBack.getCustomerServiceListOK(response);//
            }
        });
    }

}
