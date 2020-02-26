package com.muzi.weshop.model;

import java.util.List;

public class ClassGoodsListDtoModel {
    /**
     * classType : {"className":"短袖","id":1,"image":"icon/1.png"}
     * goods : [{"classId":"1","details":"好看的小熊T恤","goodName":"小熊T恤","id":1,"img":"short/1.jpg","price":80},{"classId":"1","details":"好看的黑白T恤","goodName":"黑白T恤","id":2,"img":"short/2.jpg","price":80},{"classId":"1","details":"彪马T恤大甩卖","goodName":"彪马T恤","id":3,"img":"short/7.jpg","price":100},{"classId":"1","details":"猫咪T恤清仓","goodName":"猫咪T恤","id":4,"img":"short/8.jpg","price":80}]
     */

    private ClassTypeModel classType;
    private List<GoodsModel> goods;

    public ClassTypeModel getClassType() {
        return classType;
    }

    public void setClassType(ClassTypeModel classType) {
        this.classType = classType;
    }

    public List<GoodsModel> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsModel> goods) {
        this.goods = goods;
    }
}
