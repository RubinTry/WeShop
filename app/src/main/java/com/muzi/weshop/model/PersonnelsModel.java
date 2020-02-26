package com.muzi.weshop.model;

public class PersonnelsModel {
    /**
     * account : 0
     * id : 0
     * link : string
     * password : string
     */

    private int account;
    private int id;
    private String link;
    private String password;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
