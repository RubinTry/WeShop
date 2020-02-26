package com.muzi.weshop.common.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.muzi.weshop.common.http.RetrofitManager;
import com.muzi.weshop.common.http.RxHelper;
import com.muzi.weshop.common.http.RxSubscriber;
import com.muzi.weshop.common.http.presenter.BasePresenter;
import com.muzi.weshop.common.http.presenter.IBasePresenter;
import com.muzi.weshop.model.CommentListModel;
import com.muzi.weshop.model.GoodsDetailModel;
import com.muzi.weshop.model.LoginModel;
import com.muzi.weshop.model.BaseModel;
import com.muzi.weshop.model.OrderRequestModel;
import com.muzi.weshop.model.SearchResultModel;
import com.muzi.weshop.model.OrderListModel;
import com.muzi.weshop.model.ThumbModel;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author 请求网络数据的类
 */
public class ApiPresenter extends BasePresenter {

    /**
     * 构造方法让Presenter层持有View层的引用
     *
     * @param context
     * @param view
     */
    public ApiPresenter(Context context, IBasePresenter view) {
        super(context, view);
    }



    @Override
    public void request(int requestCode) {

    }


    /**
     * 登录
     *
     * @param account     用户名
     * @param password    密码
     * @param requestCode 请求码（仅用于区分接口）
     */
    public void login(String account, String password, int requestCode) {
        //先拿到一个login的Observable
        RetrofitManager.getDefault().login(account, password)
                //绑定生命周期
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                //用订阅的方式去把请求给发起
                .subscribe(new RxSubscriber<LoginModel>(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     *
     * @param account
     * @param link
     * @param password
     * @param requestCode
     */
    public void register(String account, String link, String password, int requestCode) {
        RetrofitManager.getDefault().register(account, link, password)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     *
     * @param pageNum
     * @param pageSize
     * @param requestCode
     */
    public void getTypes(int pageNum, int pageSize, int requestCode) {
        RetrofitManager.getDefault().getTypes(pageNum, pageSize)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.DESTROY))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param requestCode
     */
    public void getClassGoodsList(int id, int pageNum, int pageSize, int requestCode) {
        RetrofitManager.getDefault().getClassGoodsList(id, pageNum, pageSize)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     *
     * @param goodsId
     * @param pageNum
     * @param pageSize
     * @param requestCode
     */
    public void getComment(Long goodsId, int pageNum, int pageSize, int requestCode) {
        RetrofitManager.getDefault().goodsCommentList(goodsId, pageNum, pageSize)
                .compose(RxHelper.<CommentListModel>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    /**
     * 商品点赞
     */
    public void goodsThumbUp(int id, int type, int requestCode) {
        RetrofitManager.getDefault().goodsThumbUp(id, type)
                .compose(RxHelper.<ThumbModel>bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber(context, false) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    public void getGoodsDetail(Long id, int requestCode) {
        RetrofitManager.getDefault().getGoodsDetail(id)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<GoodsDetailModel>(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    public void addComment(String comment, Long goodsId, int pId, int requestCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("comment", comment);
        params.put("goodsId", goodsId);
        params.put("pId", pId);
        RequestBody body = RequestBody.create(new Gson().toJson(params), MediaType.parse("application/json; charset=utf-8"));

        RetrofitManager.getDefault().addComment(body)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<BaseModel>(context, false) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }


    public void getGoodsByName(String goodsName, int pageNum, int pageSize, int requestCode) {
        RetrofitManager.getDefault().getGoodsByName(goodsName, pageNum, pageSize)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<SearchResultModel>(context, false) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }

    public void addOrder(List<OrderRequestModel> orderList, int requestCode) {

        Map<String , Object> params = new HashMap<>();
        params.put("addOrderDtos" , orderList);

        RequestBody body = RequestBody.create(new Gson().toJson(params), MediaType.parse("application/json; charset=utf-8"));
        RetrofitManager.getDefault().addOrder(body)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.PAUSE))
                .subscribe(new RxSubscriber<BaseModel>(context, false) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e, requestCode);
                    }
                });
    }

    public void getOrderList(int pageNum, int pageSize, int requestCode) {
        RetrofitManager.getDefault().getOrderList(pageNum, pageSize)
                .compose(RxHelper.bindToLifeCycle(context, ActivityEvent.DESTROY))
                .subscribe(new RxSubscriber<OrderListModel>(context) {
                    @Override
                    public void onSuccess(Object o) {
                        view.onNext(o, requestCode);
                    }

                    @Override
                    public void onFail(Throwable e) {
                        view.onError(e , requestCode);
                    }
                });
    }
}
