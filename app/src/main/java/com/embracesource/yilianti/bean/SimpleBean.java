package com.embracesource.yilianti.bean;

/**
 * Created by Administrator on 2017/10/18 0018.
 */

public class SimpleBean {
    //    BaseBean{code=1111, message='success', success=true}
//    {"code":"0000","message":"操作失败!","data":null,"traceInfo":["0000"],"sessionid":null,"fail":true,"success":false}

    private String code;
    private String message;
    private boolean success;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
