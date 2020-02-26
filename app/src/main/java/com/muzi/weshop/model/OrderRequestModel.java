package com.muzi.weshop.model;

public class OrderRequestModel {
    private Long goodsId;
    private int num;
    private int orderPrice;
    private int pId;

    public OrderRequestModel(Long goodsId, int num, int orderPrice, int pId) {
        this.goodsId = goodsId;
        this.num = num;
        this.orderPrice = orderPrice;
        this.pId = pId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }
}
