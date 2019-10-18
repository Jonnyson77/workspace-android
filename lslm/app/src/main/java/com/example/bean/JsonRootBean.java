package com.example.bean;

public class JsonRootBean {

    /**
     * msg : 手机号或验证码错误
     * code : 500
     */

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
