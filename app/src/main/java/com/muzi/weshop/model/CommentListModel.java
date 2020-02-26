package com.muzi.weshop.model;

import java.util.List;

public class CommentListModel {

    /**
     * code : 0
     * comments : [{"bulous":50000,"comment":"商品太好了，老婆收到后很开心，下次还来","goodsId":1,"id":1,"pId":13}]
     * msg : success
     * total : 0
     */

    private int code;
    private String msg;
    private int total;
    private List<CommentsModel> commentDtos;

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

    public List<CommentsModel> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentsModel> commentDtos) {
        this.commentDtos = commentDtos;
    }
}
