package com.muzi.weshop.model;

public class RecyclerData<T> {
    T data;
    int type;

    public RecyclerData(T data, int type) {
        this.data = data;
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
