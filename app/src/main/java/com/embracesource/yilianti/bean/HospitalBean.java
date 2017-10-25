package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class HospitalBean   {


    /**
     * code : 1111
     * message : success
     * data : [{"id":193,"createdTime":"2017-10-24 17:05:53","hVersion":1,"available":1,"name":"中南大学湘雅医院","code":null,"description":"中南大学湘雅医院","level":1,"parentId":0,"parentName":null},{"id":1,"createdTime":"2017-09-11 14:43:02","hVersion":1,"available":1,"name":"中南大学湘雅二医院","code":"","description":"中南大学湘雅二医院","level":1,"parentId":0,"parentName":null},{"id":196,"createdTime":"2017-10-24 17:07:38","hVersion":1,"available":1,"name":"中南大学湘雅三医院","code":null,"description":"中南大学湘雅三医院","level":1,"parentId":0,"parentName":null}]
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
         * id : 193
         * createdTime : 2017-10-24 17:05:53
         * hVersion : 1
         * available : 1
         * name : 中南大学湘雅医院
         * code : null
         * description : 中南大学湘雅医院
         * level : 1
         * parentId : 0
         * parentName : null
         */

        private int id;
        private String createdTime;
        private int hVersion;
        private int available;
        private String name;
        private Object code;
        private String description;
        private int level;
        private int parentId;
        private Object parentName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public Object getParentName() {
            return parentName;
        }

        public void setParentName(Object parentName) {
            this.parentName = parentName;
        }
    }
}
