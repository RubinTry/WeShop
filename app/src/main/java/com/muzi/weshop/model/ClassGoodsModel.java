package com.muzi.weshop.model;

public class ClassGoodsModel {

    /**
     * classGoodsListDto : {"classType":{"className":"短袖","id":1,"image":"icon/1.png"},"goods":[{"classId":"1","details":"好看的小熊T恤","goodName":"小熊T恤","id":1,"img":"short/1.jpg","price":80},{"classId":"1","details":"好看的黑白T恤","goodName":"黑白T恤","id":2,"img":"short/2.jpg","price":80},{"classId":"1","details":"彪马T恤大甩卖","goodName":"彪马T恤","id":3,"img":"short/7.jpg","price":100},{"classId":"1","details":"猫咪T恤清仓","goodName":"猫咪T恤","id":4,"img":"short/8.jpg","price":80}]}
     * code : 0
     * msg : success
     * total : 0
     */

    private ClassGoodsListDtoModel classGoodsListDto;
    private int code;
    private String msg;
    private int total;

    public ClassGoodsListDtoModel getClassGoodsListDto() {
        return classGoodsListDto;
    }

    public void setClassGoodsListDto(ClassGoodsListDtoModel classGoodsListDto) {
        this.classGoodsListDto = classGoodsListDto;
    }

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
