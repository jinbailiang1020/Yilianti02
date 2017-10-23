package com.embracesource.yilianti.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class UserInfoBean implements  Serializable{

    /**
     * code : 1111
     * message : success
     * data : {"id":3,"createdTime":"2017-09-12 01:06:23","hVersion":1,"available":1,"username":"ywc","pwd":"","groupId":7,"fullname":null,"group":null,"roleIds":null,"roles":null}
     * traceInfo : []
     * sessionid : null
     * fail : false
     * success : true
     */

    private String code;
    private String message;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean implements Serializable{
        /**
         * id : 3
         * createdTime : 2017-09-12 01:06:23
         * hVersion : 1
         * available : 1
         * username : ywc
         * pwd :
         * groupId : 7
         * fullname : null
         * group : null
         * roleIds : null
         * roles : null
         */

        private int id;
        private String createdTime;
        private int hVersion;
        private int available;
        private String username;
        private String pwd;
        private int groupId;
        private Object fullname;
        private Object group;
        private Object roleIds;
        private Object roles;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public Object getFullname() {
            return fullname;
        }

        public void setFullname(Object fullname) {
            this.fullname = fullname;
        }

        public Object getGroup() {
            return group;
        }

        public void setGroup(Object group) {
            this.group = group;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getRoles() {
            return roles;
        }

        public void setRoles(Object roles) {
            this.roles = roles;
        }
    }
}
