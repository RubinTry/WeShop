package com.muzi.weshop.common.http;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.common.http.widgets.LoadingDialog;
import com.muzi.weshop.model.LoginModel;

import java.io.IOException;


import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @@author 郑天阳
 * RxJava
 */
public abstract class RxSubscriber<T> implements Observer {
    private String TAG = this.getClass().getSimpleName();
    private LoadingDialog loadingDialog;
    private Disposable disposable;
    private Context context;
    private String errorMsg;
    private boolean showDialog;

    public RxSubscriber(Context context) {
        this(context, true);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = new CompositeDisposable();
        if (showDialog) {
            showLoading();
        }
    }

    private void showLoading() {
        loadingDialog.show();
    }


    /**
     * 没有网络问题的情况下，服务端接口调用成功执行这个方法
     * @param o
     */
    @Override
    public void onNext(Object o) {
        dismissDialog();

        if(o instanceof LoginModel){
            LoginModel loginModel = (LoginModel) o;
            if(loginModel.getCode() == -1){
                onError(new Exception("登录失败"));
                return;
            }
        }

        onSuccess(o);

        //取消掉订阅
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    /**
     * api调用成功并有返回值
     *
//     * @param baseModel
     */
    public abstract void onSuccess(Object o);


    /**
     * api调用失败并抛出异常
     *
     * @param e
     */
    public abstract void onFail(Throwable e);




    /**
     * 没有网络问题的情况下，服务端接口调用成功
     * @param o
     */
    @Override
    public void onError(Throwable e) {
        dismissDialog();
        if (e instanceof IOException) {
            errorMsg = "网络异常，请检查";
        } else if (e instanceof HttpException) {

            /** 网络异常，http 请求失败，即 http 状态码不在 [200, 300) 之间*/
            errorMsg = ((HttpException) e).response().message();
        } else {
            /** 其他未知错误 */
            errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "unknown error";
        }

        ToastUtils.showShort(errorMsg);
        
        onFail(e);


        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    private void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            //cancel方法中包含了dismiss()
            loadingDialog.cancel();
        }
    }

    @Override
    public void onComplete() {

    }
}
