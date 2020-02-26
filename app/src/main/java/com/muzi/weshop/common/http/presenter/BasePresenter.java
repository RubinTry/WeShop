package com.muzi.weshop.common.http.presenter;

import android.content.Context;

/**
 * @@author 郑天阳
 * presenter的基类
 */
public abstract class BasePresenter <V extends IBasePresenter> {
    protected Context context;
    
    protected V view;

    /**
     * 构造方法让Presenter层持有View层的引用
     * @param context
     * @param view
     */
    public BasePresenter(Context context, V view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 必须实现的统一请求方法
     * @param requestCode
     */
    public abstract void request(int requestCode);
    
}
