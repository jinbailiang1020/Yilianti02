package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */

public class ImageUploadBean {

    /**
     * code : 1111
     * message : success
     * data : 6ef4755c-10be-4346-8d32-c791d91d73a3
     * traceInfo : []
     * sessionid : null
     * fail : false
     * success : true
     */

    private String code;
    private String message;
    private String data;
    private Object sessionid;
    private boolean fail;
    private boolean success;
    private List<?> traceInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getSessionid() {
        return sessionid;
    }

    public void setSessionid(Object sessionid) {
        this.sessionid = sessionid;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }
}
