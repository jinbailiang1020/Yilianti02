package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class CustomerServiceDiagnosisListBean {

    /**
     * code : 1111
     * message : success
     * data : [{"id":9,"createdTime":"2017-09-13 16:47:12","hVersion":1,"available":1,"name":"客服工作台","code":null,"description":"对客服的工作台的访问权限","displayName":"客服工作台","icon":null,"url":"/html/account/medicalwork.html","displayOrder":1,"parentId":null,"type":1,"level":1}]
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
         * id : 9
         * createdTime : 2017-09-13 16:47:12
         * hVersion : 1
         * available : 1
         * name : 客服工作台
         * code : null
         * description : 对客服的工作台的访问权限
         * displayName : 客服工作台
         * icon : null
         * url : /html/account/medicalwork.html
         * displayOrder : 1
         * parentId : null
         * type : 1
         * level : 1
         */

        private int id;
        private String createdTime;
        private int hVersion;
        private int available;
        private String name;
        private Object code;
        private String description;
        private String displayName;
        private Object icon;
        private String url;
        private int displayOrder;
        private Object parentId;
        private int type;
        private int level;

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

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
