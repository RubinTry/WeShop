package com.muzi.weshop.model;

/**
 * @author muzi
 */
public class ClassifyContentModel {
    private int resId;
    private String title;

    public ClassifyContentModel(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
