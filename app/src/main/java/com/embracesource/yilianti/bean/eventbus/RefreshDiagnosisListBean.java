package com.embracesource.yilianti.bean.eventbus;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class RefreshDiagnosisListBean {
    private boolean success;
    private String msg;
    private Object o;

    public RefreshDiagnosisListBean(String msg) {
        this.msg = msg;
    }

    public RefreshDiagnosisListBean(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public RefreshDiagnosisListBean() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    @Override
    public String toString() {
        return "BaseEventBusBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", o=" + o +
                '}';
    }
}
