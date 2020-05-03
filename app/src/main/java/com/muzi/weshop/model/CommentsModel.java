package com.muzi.weshop.model;

public class CommentsModel {
    /**
     * bulous : 50000
     * comment : 商品太好了，老婆收到后很开心，下次还来
     * goodsId : 1
     * id : 1
     * pId : 13
     */



    private int bulous;
    private String comment;
    private int goodsId;
    private int id;
    private int pId;
    private boolean clicked;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getBulous() {
        return bulous;
    }

    public void setBulous(int bulous) {
        this.bulous = bulous;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }
}
