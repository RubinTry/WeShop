package com.muzi.weshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.model.OrderListModel;
import com.muzi.weshop.model.OrderMsgDtoListBean;
import com.muzi.weshop.view.adapter.OrderListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author logcat
 */
public class OrderListActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {


    @BindView(R.id.rvOrderList)
    RecyclerView rvOrderList;
    @BindView(R.id.refreshOrderList)
    SmartRefreshLayout refreshOrderList;
    private OrderListAdapter orderListAdapter;
    private List<OrderMsgDtoListBean> orderList;

    private int page;

    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initViews() {
        page = 1;
        rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        rvOrderList.setNestedScrollingEnabled(false);
        orderList = new ArrayList<>();
        orderListAdapter = new OrderListAdapter(R.layout.item_my_order_list , orderList , this);
        rvOrderList.setAdapter(orderListAdapter);

        refreshOrderList.setOnRefreshListener(this);
        refreshOrderList.setOnLoadMoreListener(this);
    }

    @Override
    protected void requestData() {
        apiPresenter.getOrderList(page , 10 , RequestCodeContants.GET_ORDER_LIST);
    }


    @OnClick({R.id.imgBtnBack})
    void onClick(View view){
        switch (view.getId()){
            case R.id.imgBtnBack:
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshOrderList.finishRefresh();
        refreshOrderList.finishLoadMore();
        switch (requestCode){
            case RequestCodeContants.GET_ORDER_LIST:
                OrderListModel orderListModel = (OrderListModel) o;
                orderList.clear();
                orderList.addAll(orderListModel.getOrderMsgDtoList());
                orderListAdapter.setNewData(orderList);
                break;
            case RequestCodeContants.GET_ORDER_LIST_LOAD_MORE:
                OrderListModel orderListModelLoadMore = (OrderListModel) o;
                if(orderListModelLoadMore.getOrderMsgDtoList() != null){
                    orderListAdapter.addData(orderListModelLoadMore.getOrderMsgDtoList());
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshOrderList.finishRefresh();
        refreshOrderList.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        apiPresenter.getOrderList(page , 10 , RequestCodeContants.GET_ORDER_LIST);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++;
        apiPresenter.getOrderList(page , 10 , RequestCodeContants.GET_ORDER_LIST_LOAD_MORE);
    }
}
