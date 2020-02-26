package com.muzi.weshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;
import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.ExtraConstants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.model.SearchResultModel;
import com.muzi.weshop.view.adapter.ClassGoodsAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author logcat
 */
public class SearchActivity extends BaseActivity implements OnLoadMoreListener {


    private static final long INTERVAL = 500;
    @BindView(R.id.edtKeyWord)
    EditText edtKeyWord;
    @BindView(R.id.rvSearchGoodsList)
    RecyclerView rvSearchGoodsList;
    @BindView(R.id.refreshSearch)
    SmartRefreshLayout refreshSearch;
    private ClassGoodsAdapter goodsAdapter;
    private List<GoodsModel> goodsList;

    /**
     * 列表页数
     */
    private int page;
    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        page = 1;
        //初始化recyclerView
        rvSearchGoodsList.setLayoutManager(new LinearLayoutManager(this));
        rvSearchGoodsList.setNestedScrollingEnabled(false);
        goodsList = new ArrayList<>();
        goodsAdapter = new ClassGoodsAdapter(R.layout.item_class_goods , goodsList);
        rvSearchGoodsList.setAdapter(goodsAdapter);
        goodsAdapter.setOnItemClickListener(new ClassGoodsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GoodsModel result) {
                Intent intent = new Intent(SearchActivity.this , GoodsDetailActivity.class);
                intent.putExtra(ExtraConstants.GOODS_MODEL , new Gson().toJson(result));
                startActivity(intent);
            }
        });

        refreshSearch.setOnLoadMoreListener(this);
        
        //添加延时监听
        RxTextView.textChangeEvents(edtKeyWord)
                .debounce(INTERVAL , TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        if(TextUtils.isEmpty(edtKeyWord.getText().toString())){
                            goodsList.clear();
                            goodsAdapter.setNewData(goodsList);
                        }else{
                            apiPresenter.getGoodsByName(edtKeyWord.getText().toString() , 1 , 10 , RequestCodeContants.SEARCH_BY_NAME);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void requestData() {
        
    }
    
    @OnClick({R.id.imgBtnBack , R.id.tvSearch})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvSearch:
                apiPresenter.getGoodsByName(edtKeyWord.getText().toString() , 1 , 10 , RequestCodeContants.SEARCH_BY_NAME);
                break;
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
        refreshSearch.finishLoadMore();
        switch (requestCode){
            case RequestCodeContants.SEARCH_BY_NAME:
                SearchResultModel searchResultModel = (SearchResultModel) o;
                goodsList.clear();
                goodsList.addAll(searchResultModel.getGoodsList());
                goodsAdapter.setNewData(goodsList);
                break;
            case RequestCodeContants.SEARCH_BY_NAME_LOAD_MORE:
                SearchResultModel searchResultModelLoadMore = (SearchResultModel) o;
                goodsAdapter.addData(searchResultModelLoadMore.getGoodsList());
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshSearch.finishLoadMore();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        //如果搜索关键词不为空
        if(!TextUtils.isEmpty(edtKeyWord.getText().toString())){
            page ++;
            apiPresenter.getGoodsByName(edtKeyWord.getText().toString() , page , 10 , RequestCodeContants.SEARCH_BY_NAME_LOAD_MORE);
        }else{
            refreshSearch.finishLoadMore();
        }
    }
}
