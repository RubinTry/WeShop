package com.muzi.weshop.model;

import java.util.List;

/**
 * @author logcat
 */
public class OrderListModel {

    /**
     * code : 0
     * msg : success
     * orderMsgDtoList : [{"goodsId":0,"id":1,"num":0,"orderId":"1581509918783","orderPrice":0,"pId":0,"step":"0","time":"2020-02-12 20:18:38"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":2,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511040250","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:37:20"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":3,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511089221","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:38:09"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":4,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511203222","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:40:03"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":5,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511235758","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:40:35"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":6,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511282503","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:41:22"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":7,"image":"icon/1.png","img":"short/1.jpg","num":3,"orderId":"1581511325414","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:42:05"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":8,"image":"icon/1.png","img":"short/1.jpg","num":1,"orderId":"1581511770615","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 20:49:30"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":9,"image":"icon/1.png","img":"short/1.jpg","num":2,"orderId":"1581513416945","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 21:16:56"},{"classId":"1","className":"短袖","details":"好看的小熊T恤","goodName":"小熊T恤","goodsId":1,"id":10,"image":"icon/1.png","img":"short/1.jpg","num":4,"orderId":"1581513462630","orderPrice":80,"pId":18,"step":"0","time":"2020-02-12 21:17:42"}]
     * total : 0
     */

    private int code;
    private String msg;
    private int total;
    private List<OrderMsgDtoListBean> orderMsgDtoList;

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

    public List<OrderMsgDtoListBean> getOrderMsgDtoList() {
        return orderMsgDtoList;
    }

    public void setOrderMsgDtoList(List<OrderMsgDtoListBean> orderMsgDtoList) {
        this.orderMsgDtoList = orderMsgDtoList;
    }
}
