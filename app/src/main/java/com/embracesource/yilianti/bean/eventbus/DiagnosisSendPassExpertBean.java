package com.embracesource.yilianti.bean.eventbus;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class DiagnosisSendPassExpertBean extends  BaseEventBusBean {
    public DiagnosisSendPassExpertBean(String msg) {
        super(msg);
    }

    public DiagnosisSendPassExpertBean(boolean success, String msg) {
        super(success, msg);
    }
}
