package com.muzi.weshop.model;

import java.util.List;

/**
 * @author muzi
 */
public class ClassifyContentModel {

    /**
     * classTypes : [{"className":"短袖","id":1,"image":"icon/1.png"}]
     * code : 0
     * msg : success
     * total : 0
     */

    private int code;
    private String msg;
    private int total;
    private List<ClassTypesModel> classTypes;

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

    public List<ClassTypesModel> getClassTypes() {
        return classTypes;
    }

    public void setClassTypes(List<ClassTypesModel> classTypes) {
        this.classTypes = classTypes;
    }
}
