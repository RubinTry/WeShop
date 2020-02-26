package com.muzi.weshop.model;

import java.io.Serializable;

public class RegisterInfoModel implements Serializable {
    private String account;
    private String password;

    public RegisterInfoModel(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
