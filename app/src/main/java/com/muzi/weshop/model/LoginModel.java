package com.muzi.weshop.model;

import java.util.List;

public class LoginModel {

    /**
     * code : 0
     * msg : string
     * personnel : {"account":0,"id":0,"link":"string","password":"string"}
     * personnels : [{"account":0,"id":0,"link":"string","password":"string"}]
     * responseEntity : OK
     * total : 0
     */

    private int code;
    private String msg;
    private PersonnelModel personnel;
    private String responseEntity;
    private int total;
    private List<PersonnelsModel> personnels;

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

    public PersonnelModel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(PersonnelModel personnel) {
        this.personnel = personnel;
    }

    public String getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(String responseEntity) {
        this.responseEntity = responseEntity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PersonnelsModel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(List<PersonnelsModel> personnels) {
        this.personnels = personnels;
    }
}
