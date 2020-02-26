package com.muzi.weshop.model;


import org.greenrobot.greendao.annotation.Entity;

public class GoodsDetailModel {

    /**
     * code : 0
     * goodsMsgDto : {"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","id":1,"image":"icon/1.png","img":"short/1.jpg","price":80}
     * msg : success
     * total : 0
     */

    private int code;
    private GoodsMsgDtoModel goodsMsgDto;
    private String msg;
    private int total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GoodsMsgDtoModel getGoodsMsgDto() {
        return goodsMsgDto;
    }

    public void setGoodsMsgDto(GoodsMsgDtoModel goodsMsgDto) {
        this.goodsMsgDto = goodsMsgDto;
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
