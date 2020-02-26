package com.muzi.weshop.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author logcat
 */
@Entity
public class GoodsModel{
    static final Long serialVersionUID = 536871008L;
    /**
     * classId : 1
     * details : 好看的小熊T恤
     * goodName : 小熊T恤
     * id : 1
     * img : short/1.jpg
     * price : 80
     */

    private String classId;
    private String details;
    private String goodName;
    @Id
    private Long id;
    private String img;
    private int price;
    private int count;
    private boolean selected;

    @Generated(hash = 433725122)
    public GoodsModel(String classId, String details, String goodName, Long id,
            String img, int price, int count, boolean selected) {
        this.classId = classId;
        this.details = details;
        this.goodName = goodName;
        this.id = id;
        this.img = img;
        this.price = price;
        this.count = count;
        this.selected = selected;
    }

    @Generated(hash = 971639536)
    public GoodsModel() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
