package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.HospitalWaitHandleListBean;
import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;
import com.embracesource.yilianti.viewmodel.DiagnosisPictureCallBack;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DiagnosisPictureViewModel extends ViewModel {
    private final Api api;
    private final DiagnosisPictureCallBack callBack;

//    @Inject
    public DiagnosisPictureViewModel(DiagnosisPictureActivity diagnosisPictureActivity) {
        api = new Api();
        this.callBack = diagnosisPictureActivity;
    }

    public void getMyLaunchList(final int pageNum,final int pageSize) {
        api.getMyLaunchList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<MyLaunchListBean>() {//
            @Override
            public void onNext(@NonNull MyLaunchListBean response) {
                super.onNext(response);
                callBack.getMyLaunchListOK(response,pageNum);//sss
            }
        });

    }

    public void getMyParticipateList(final int pageNum, int pageSize) {
        api.getMyParticipateList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<MyLaunchListBean>() {//
            @Override
            public void onNext(@NonNull MyLaunchListBean response) {
                super.onNext(response);
                callBack.getMyParticipateListOK(response,pageNum);//sss
            }
        });
    }

    public void getHospitalList(final int pageNum, int pageSize) {
        //完成标识符flagFinish：0 未完成；1 已完成
        api.getHospitalList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<HospitalWaitHandleListBean>() {//
            @Override
            public void onNext(@NonNull HospitalWaitHandleListBean response) {
                super.onNext(response);
                callBack.getHospitalListOK(response,pageNum);//
            }
        });
    }
}
