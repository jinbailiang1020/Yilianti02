package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.ApplyDiagnosisGoalBean;
import com.embracesource.yilianti.bean.LoginBean;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface ApplyDiagnosisViewModelCallBack extends BaseViewModelCallBack {
     void getBaseDataOK(ApplyDiagnosisGoalBean response);
}
