package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
//@Entity
public class LoginBean extends BaseBean{
    /**
     * code : 1111
     * message : success
     * data : {"code":0,"message":"登录成功","data":null,"traceInfo":[],"sessionid":"B0AC60DAC706A8D1D0C2CA7AA726ED64","fail":true,"success":false}
     * traceInfo : []
     * sessionid : null
     * fail : false
     * success : true
     */
    private String code;
    private String message;
    private DataBean data;

//    @Property(nameInDb = "sessionid")
    private String sessionid;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
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

    public static class DataBean {
        /**
         * code : 0
         * message : 登录成功
         * data : null
         * traceInfo : []
         * sessionid : B0AC60DAC706A8D1D0C2CA7AA726ED64
         * fail : true
         * success : false
         */

        private int code;
        private String message;
        private Object data;
        private String sessionid;
        private boolean fail;
        private boolean success;
        private List<?> traceInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getSessionid() {
            return sessionid;
        }

        public void setSessionid(String sessionid) {
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
//{code=1111, message=success,
// data={code=0000, message=登录成功, data=null, traceInfo=[], sessionid=B0AC60DAC706A8D1D0C2CA7AA726ED64, fail=true, success=false},
// traceInfo=[], sessionid=null, fail=false, success=true}




}
