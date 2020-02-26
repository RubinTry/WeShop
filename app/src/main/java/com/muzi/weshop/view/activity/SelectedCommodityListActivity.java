package com.muzi.weshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.BusUtils;
import com.google.gson.Gson;
import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.BusConstants;
import com.muzi.weshop.common.contants.ExtraConstants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.common.http.callback.DiffTCallback;
import com.muzi.weshop.model.ClassGoodsListDtoModel;
import com.muzi.weshop.model.ClassGoodsModel;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.view.adapter.ClassGoodsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @@author 郑天阳
 * 分类后的商品列表activity
 */
public class SelectedCommodityListActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    
    @BindView(R.id.rvSelectedTypeCommodity)
    RecyclerView rvSelectedTypeCommodity;
    @BindView(R.id.refreshClassGoods)
    SmartRefreshLayout refreshClassGoods;
    private List<GoodsModel> goodsList;
    private ClassGoodsAdapter classGoodsAdapter;
    private int id;
    private int page;

    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_selected_commodity_list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusUtils.unregister(this);
    }

    @Override
    protected void initViews() {
        BusUtils.register(this);
        page = 1;
        id = getIntent().getIntExtra(ExtraConstants.CLASS_ID , 0);
        goodsList = new ArrayList<>();
        classGoodsAdapter = new ClassGoodsAdapter(R.layout.item_class_goods , goodsList);
        rvSelectedTypeCommodity.setLayoutManager(new LinearLayoutManager(this));
        rvSelectedTypeCommodity.setNestedScrollingEnabled(false);
        rvSelectedTypeCommodity.setAdapter(classGoodsAdapter);
        classGoodsAdapter.setOnItemClickListener(new ClassGoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GoodsModel result) {
                Intent intent = new Intent(SelectedCommodityListActivity.this , GoodsDetailActivity.class);
                intent.putExtra(ExtraConstants.GOODS_MODEL , new Gson().toJson(result));
                startActivity(intent);
            }
        });
        refreshClassGoods.setOnRefreshListener(this);
        refreshClassGoods.setOnLoadMoreListener(this);
    }

    @Override
    protected void requestData() {
        apiPresenter.getClassGoodsList(id , page , 100 , RequestCodeContants.CLASS_GOODS);
    }
    
    @OnClick({R.id.imgBtnBack , R.id.flSearch})
    void onClick(View view){
        switch (view.getId()){
            case R.id.imgBtnBack:
                finish();
                break;
            case R.id.flSearch:
                Intent intent = new Intent(this , SearchActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshClassGoods.finishRefresh();
        refreshClassGoods.finishLoadMore();
        switch (requestCode){
            case RequestCodeContants.CLASS_GOODS:
                ClassGoodsModel classGoodsModel = (ClassGoodsModel) o;
                if(page == 1){
                    goodsList.clear();
                    goodsList.addAll(classGoodsModel.getClassGoodsListDto().getGoods());
                    classGoodsAdapter.setNewData(goodsList);
                }else{
                    for (GoodsModel good : classGoodsModel.getClassGoodsListDto().getGoods()) {
                        classGoodsAdapter.addData(good);
                    }
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshClassGoods.finishRefresh();
        refreshClassGoods.finishLoadMore();
    }

    

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        requestData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++;
        requestData();
    }

    @BusUtils.Bus(tag = BusConstants.JUMP_TO_SHOPPING_CART , threadMode = BusUtils.ThreadMode.MAIN)
    public void jumpToShoppingCart(){
        finish();
    }
}
