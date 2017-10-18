package com.embracesource.yilianti.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class DoctorBean {

    /**
     * code : 1111
     * message : success
     * data : [{"id":1,"fullname":"无"},{"id":6,"fullname":"谢红付"},{"id":7,"fullname":"陈翔"},{"id":8,"fullname":"陈明亮"},{"id":9,"fullname":"张江林"},{"id":10,"fullname":"朱 武"},{"id":11,"fullname":"施 为"},{"id":12,"fullname":"陈 欢"},{"id":13,"fullname":"邓 波"},{"id":14,"fullname":"李 捷"},{"id":15,"fullname":"简 丹"},{"id":16,"fullname":"李 吉"},{"id":17,"fullname":"李金茂"},{"id":18,"fullname":"匡叶红"},{"id":19,"fullname":"粟 娟"},{"id":20,"fullname":"易 梅"},{"id":21,"fullname":"刘芳芬"},{"id":22,"fullname":"李芳芳"},{"id":23,"fullname":"赵 爽"},{"id":24,"fullname":"汪 犇"},{"id":25,"fullname":"黄莹雪"}]
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
         * fullname : 无
         */

        private int id;
        private String fullname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }
}
