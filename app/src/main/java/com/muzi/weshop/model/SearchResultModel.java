package com.muzi.weshop.model;

import java.util.ArrayList;
import java.util.List;

public class SearchResultModel {

    /**
     * code : 0
     * goodsList : [{"classId":"1","details":"好看的小熊T恤","goodName":"小熊T恤","id":1,"img":"short/1.jpg","price":80}]
     * msg : success
     * total : 0
     */

    private int code;
    private String msg;
    private int total;
    private List<GoodsModel> goodsList;

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

    public List<GoodsModel> getGoodsList() {
        return goodsList == null ? goodsList = new ArrayList<>() : goodsList;
    }

    public void setGoodsList(List<GoodsModel> goodsList) {
        this.goodsList = goodsList;
    }
}
