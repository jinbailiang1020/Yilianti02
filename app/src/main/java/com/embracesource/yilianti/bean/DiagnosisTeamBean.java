package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class DiagnosisTeamBean {

    /**
     * code : 1111
     * message : success
     * data : [{"id":1,"createdTime":1504750401000,"hVersion":1,"available":1,"name":"银屑病等红斑鳞屑性疾病团队","description":"银屑病等红斑鳞屑性疾病团队","groupId":1},{"id":2,"createdTime":1504755016000,"hVersion":1,"available":1,"name":"荨麻疹等过敏性疾病团队","description":"荨麻疹等过敏性疾病团队","groupId":1},{"id":3,"createdTime":1504755017000,"hVersion":1,"available":1,"name":"皮肤肿瘤与皮肤外科团队","description":"皮肤肿瘤与皮肤外科团队","groupId":1},{"id":4,"createdTime":1504606497000,"hVersion":1,"available":1,"name":"疑难病例及皮肤病理团队","description":"疑难病例及皮肤病理团队","groupId":1},{"id":5,"createdTime":1504606505000,"hVersion":1,"available":1,"name":"玫瑰痤疮等损容性皮肤病团队","description":"玫瑰痤疮等损容性皮肤病团队","groupId":1},{"id":6,"createdTime":1504606529000,"hVersion":1,"available":1,"name":"其他专家团队","description":"其他专家团队","groupId":1}]
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
         * createdTime : 1504750401000
         * hVersion : 1
         * available : 1
         * name : 银屑病等红斑鳞屑性疾病团队
         * description : 银屑病等红斑鳞屑性疾病团队
         * groupId : 1
         */

        private int id;
        private long createdTime;
        private int hVersion;
        private int available;
        private String name;
        private String description;
        private int groupId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }
    }
}
