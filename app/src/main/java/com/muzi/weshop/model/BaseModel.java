package com.muzi.weshop.model;

/**
 * @@author 郑天阳
 */
public class BaseModel {

    /**
     * code : 0
     * msg : success
     * total : 0
     */

    private int code;
    private String msg;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
