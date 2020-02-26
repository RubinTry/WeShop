package com.muzi.weshop.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseFragment;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.common.utils.DbUtils;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.model.OrderRequestModel;
import com.muzi.weshop.view.activity.OrderListActivity;
import com.muzi.weshop.view.adapter.ShoppingCartAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author muzi
 * 购物车
 */
public class ShoppingCartFragment extends BaseFragment implements OnLoadMoreListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "ShoppingCartFragment";
    @BindView(R.id.rvShoppingCart)
    RecyclerView rvShoppingCart;
    @BindView(R.id.ckSelectAll)
    CheckBox ckSelectAll;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.tvDeleteGoods)
    TextView tvDeleteGoods;
    @BindView(R.id.tvGoToPay)
    TextView tvGoToPay;

    /**
     * 是否处于编辑状态
     */
    private boolean isEditing;
    private List<GoodsModel> orderList;
    private ShoppingCartAdapter shoppingCartAdapter;
    /**
     * 总价格
     */
    private int totalPrice = 0;

    /**
     * 商品总数量
     */
    private int totalCount = 0;

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {
        orderList = DbUtils.getInstance().queryAllGoods();
    }

    @Override
    protected void initViews() {
        isEditing = false;
        rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        rvShoppingCart.setNestedScrollingEnabled(false);
        orderList = new ArrayList<>();
        shoppingCartAdapter = new ShoppingCartAdapter(R.layout.item_shopping_cart, orderList);
        rvShoppingCart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void onAddClick(int count, GoodsModel goodsModel, TextView tvGoodsCount) {
                count++;
                goodsModel.setCount(count);
                tvGoodsCount.setText(count + "");
                if (DbUtils.getInstance().queryGoodsById(goodsModel.getId()).size() > 0) {
                    DbUtils.getInstance().updateGoods(goodsModel);
                } else {
                    DbUtils.getInstance().insertGoods(goodsModel);
                }


                if(goodsModel.getSelected()){
                    totalCount ++;
                }

                totalPrice += goodsModel.getPrice();

                tvTotalPrice.setText("合计：￥" + totalPrice + ".00");
                tvGoToPay.setText("去结算(" + totalCount + ")");
            }

            @Override
            public void onReduceClick(int count, GoodsModel goodsModel, TextView tvGoodsCount) {
                if (count > 1) {
                    count--;
                    goodsModel.setCount(count);
                    tvGoodsCount.setText(count + "");
                    if (DbUtils.getInstance().queryGoodsById(goodsModel.getId()).size() > 0) {
                        DbUtils.getInstance().updateGoods(goodsModel);
                    } else {
                        DbUtils.getInstance().insertGoods(goodsModel);
                    }


                    if(goodsModel.getSelected()){
                        totalCount --;
                    }
                    totalPrice -= goodsModel.getPrice();
                    tvTotalPrice.setText("合计：￥" + totalPrice + ".00");
                    tvGoToPay.setText("去结算(" + totalCount + ")");

                }else{
                    ToastUtils.showShort("最少买一件哦");
                }
            }

            @Override
            public void onChecked(boolean isChecked, GoodsModel goodsModel) {
                if(isChecked){
                    totalCount += goodsModel.getCount();

                    //统计总价
                    totalPrice += goodsModel.getPrice() * goodsModel.getCount();
                }else{
                    totalCount -= goodsModel.getCount();

                    //统计总价
                    totalPrice -= goodsModel.getPrice() * goodsModel.getCount();
                }
                tvGoToPay.setText("去结算(" + totalCount + ")");
                tvTotalPrice.setText("合计：￥" + totalPrice + ".00");



            }

        });

        ckSelectAll.setOnCheckedChangeListener(this);

    }

    @Override
    protected void requestData() {
    }


    @Override
    public void onResume() {
        super.onResume();
        loadCacheData();
    }


    /**
     * 加载缓存数据
     */
    private void loadCacheData() {
        orderList.clear();
        List<GoodsModel> cachesGoodsList = DbUtils.getInstance().queryAllGoods();
        if(cachesGoodsList != null){
            for (GoodsModel goodsModel : cachesGoodsList) {
                if(goodsModel.getCount() != 0){
                    orderList.add(goodsModel);
                }
            }
        }
        for (GoodsModel goodsModel : orderList) {
            if(goodsModel.getSelected()){
                totalPrice += (goodsModel.getPrice() * goodsModel.getCount());
                totalCount += goodsModel.getCount();
            }
        }
        if(totalCount == 0){
            ckSelectAll.setChecked(false);
        }else{
            ckSelectAll.setChecked(true);
        }
        tvTotalPrice.setText("合计：￥" + totalPrice + ".00");
        tvGoToPay.setText("去结算(" + totalCount + ")");
        shoppingCartAdapter.setNewData(orderList);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.ckSelectAll:
                if (isChecked) {
                    //选中全部
                    for (GoodsModel goodsModel : orderList) {
                        goodsModel.setSelected(true);
                    }
                    shoppingCartAdapter.setNewData(orderList);
                } else {
                    //取消全选
                    for (GoodsModel goodsModel : orderList) {
                        goodsModel.setSelected(false);
                    }
                    shoppingCartAdapter.setNewData(orderList);
                }
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.tvGoToPay , R.id.tvDeleteGoods , R.id.tvEdit})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvEdit:
                if(!isEditing){
                    //进入编辑状态
                    isEditing = true;
                    tvEdit.setText("完成");
                    tvDeleteGoods.setVisibility(View.VISIBLE);
                }else{
                    isEditing = false;
                    tvEdit.setText("编辑");
                    tvDeleteGoods.setVisibility(View.GONE);
                }
                shoppingCartAdapter.notifyDataSetChanged();

                break;
            case R.id.tvDeleteGoods:
                int orderCount = 0;
                for (int i = 0; i < orderList.size(); i++) {
                    if(orderList.get(i).getSelected()){
                        orderList.get(i).setCount(0);
                        DbUtils.getInstance().updateGoods(orderList.get(i));
                        orderCount ++;
                    }
                }

                //如果没有未删除的数据
                if(orderCount == 0){
                    ckSelectAll.setChecked(false);
                }
                loadCacheData();
                break;
            case R.id.tvGoToPay:

                List<OrderRequestModel> orderRequestList = new ArrayList<>();
                //选中的条数
                int selectedCount = 0;
                for (GoodsModel goodsModel : orderList) {
                    if(goodsModel.getCount() != 0 && goodsModel.getSelected()){
                        orderRequestList.add(new OrderRequestModel(goodsModel.getId() , goodsModel.getCount() , goodsModel.getPrice() , LoginManager.getInstance().getPersonalId()));
                    }
                }
                if(selectedCount == 0){
                    ToastUtils.showShort("最少勾选一件商品哦");
                    return;
                }
                apiPresenter.addOrder(orderRequestList , RequestCodeContants.ADD_ORDER);
                break;
                default:
                    break;
        }
    }


    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode){
            case RequestCodeContants.ADD_ORDER:
                //调用支付宝/微信支付的过程略，直接跳转至订单列表
                ToastUtils.showShort("下单成功");
                startActivity(new Intent(getContext() , OrderListActivity.class));
                break;
                default:
                    break;
        }
    }
}
