package com.embracesource.yilianti.ui.homepage.diagnosis;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.embracesource.yilianti.bean.MyLaunchListBean;
import com.embracesource.yilianti.common.http.Api;
import com.embracesource.yilianti.ui.base.BaseActivity;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DiagnosisPictureViewModel extends ViewModel {
    private final Api api;
    private final DiagnosisPictureActivity callBack;
    private int pageSize = 10;

    @Inject
    public DiagnosisPictureViewModel(DiagnosisPictureActivity diagnosisPictureActivity) {
        api = new Api();
        this.callBack = diagnosisPictureActivity;
    }

    public void getMyLaunchList(Context mContext,int pageNum,String sessionid) {
//        Toast.makeText(mContext,"dragerr",Toast.LENGTH_SHORT).show();
        api.getMyLaunchList(pageNum,pageSize).subscribe(new BaseActivity.MyObserver<MyLaunchListBean>() {
            @Override
            public void onNext(@NonNull MyLaunchListBean response) {
                super.onNext(response);
                callBack.getMyLaunchListOK(response);
            }
        });

    }
}
