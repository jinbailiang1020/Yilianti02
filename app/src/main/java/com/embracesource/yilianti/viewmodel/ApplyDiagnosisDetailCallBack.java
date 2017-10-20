package com.embracesource.yilianti.viewmodel;

import com.embracesource.yilianti.bean.ApplyDiagnosisDetailBean;
import com.embracesource.yilianti.bean.SimpleBean;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public interface ApplyDiagnosisDetailCallBack extends BaseViewModelCallBack{
    void getApplyDiagnosisDetailOK(ApplyDiagnosisDetailBean applyDiagnosisDetailBean);

    void sendPassOK(SimpleBean bean);

    void sendUnPassOK(SimpleBean bean);
}
