package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ApplyDiagnosisGoalBean  {


    /**
     * code : 1111
     * message : success
     * data : [
     * {"id":1,"createdTime":1504771845000,"hVersion":1,"available":1,"code":"consultation_objective","value":1,"description":"明确诊断","order":1,"parentid":null,"mark":"会诊目的"},
     * {"id":2,"createdTime":1504771780000,"hVersion":1,"available":1,"code":"consultation_objective","value":2,"description":"协助治疗","order":2,"parentid":null,"mark":"会诊目的"},
     * {"id":3,"createdTime":1504771811000,"hVersion":1,"available":1,"code":"consultation_objective","value":3,"description":"要求转诊","order":3,"parentid":null,"mark":"会诊目的"},
     * {"id":4,"createdTime":1504771817000,"hVersion":1,"available":1,"code":"consultation_objective","value":4,"description":"其他","order":4,"parentid":null,"mark":"会诊目的"}]
     * traceInfo : []
     * sessionid : null
     * fail : false
     * success : true
     */

    private String code;
    private String message;
    private Object sessionid;
    private boolean fail;
    private boolean success;
    private List<DataBean> data;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<?> getTraceInfo() {
        return traceInfo;
    }

    public void setTraceInfo(List<?> traceInfo) {
        this.traceInfo = traceInfo;
    }

    public static class DataBean {
        /**
         * id : 1
         * createdTime : 1504771845000
         * hVersion : 1
         * available : 1
         * code : consultation_objective
         * value : 1
         * description : 明确诊断
         * order : 1
         * parentid : null
         * mark : 会诊目的
         */

        private int id;
        private long createdTime;
        private int hVersion;
        private int available;
        private String code;
        private int value;
        private String description;
        private int order;
        private Object parentid;
        private String mark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public int getHVersion() {
            return hVersion;
        }

        public void setHVersion(int hVersion) {
            this.hVersion = hVersion;
        }

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }
}
