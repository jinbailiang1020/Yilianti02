package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;

import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DiagnosisPictureViewModel extends ViewModel {
    private final Api api;
    private final DiagnosisPictureActivity callBack;

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
}
