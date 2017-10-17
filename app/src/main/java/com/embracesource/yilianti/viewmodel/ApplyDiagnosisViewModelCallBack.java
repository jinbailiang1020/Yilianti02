package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.BaseBean;
import com.embracesource.yilianti.common.dragger.AppModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
@Component(modules = AppModule.class)
public interface ApplyDiagnosisViewModelCallBack extends BaseViewModelCallBack {
     void getBaseDataOK(ApplyDiagnosisGoalBean response);

    void submitApplyDiagnosisOK(BaseBean response);
}
