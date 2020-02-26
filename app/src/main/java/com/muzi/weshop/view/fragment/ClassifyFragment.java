package com.muzi.weshop.view.fragment;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.DiffTCallback;
import com.muzi.weshop.common.base.BaseFragment;
import com.muzi.weshop.common.contants.ExtraConstants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.model.ClassTypesModel;
import com.muzi.weshop.model.ClassifyContentModel;
import com.muzi.weshop.model.RecyclerData;
import com.muzi.weshop.view.activity.SelectedCommodityListActivity;
import com.muzi.weshop.view.adapter.ClassifyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author muzi
 * 分类
 */
public class ClassifyFragment extends BaseFragment implements OnRefreshListener {
    @BindView(R.id.rvCommodityType)
    RecyclerView rvCommodityType;
    @BindView(R.id.refreshClassify)
    SmartRefreshLayout refreshClassify;
    private ClassifyAdapter classifyAdapter;
    private List<ClassTypesModel> typeList;
    private List<ClassTypesModel> clearTypeList;
    private DiffUtil.DiffResult typeDiffResult;

    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initData() {
        typeList = new ArrayList<>();
        clearTypeList = new ArrayList<>();
    }

    @Override
    protected void initViews() {
        rvCommodityType.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCommodityType.setNestedScrollingEnabled(false);
        classifyAdapter = new ClassifyAdapter(R.layout.item_classify_content , typeList , this);
        rvCommodityType.setAdapter(classifyAdapter);
        classifyAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClassTypesModel result) {
                Intent intent = new Intent(getContext() , SelectedCommodityListActivity.class);
                intent.putExtra(ExtraConstants.CLASS_ID , result.getId());
                startActivity(intent);
            }
        });
        refreshClassify.setOnRefreshListener(this);
    }

    @Override
    protected void requestData() {
        apiPresenter.getTypes(1 , 10 , RequestCodeContants.GET_TYPES);
    }



    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshClassify.finishRefresh();
        refreshClassify.finishLoadMore();
        switch (requestCode){
            case RequestCodeContants.GET_TYPES:
                ClassifyContentModel classifyContentModel = (ClassifyContentModel) o;
                typeList.clear();
                typeList.addAll(classifyContentModel.getClassTypes());
                classifyAdapter.setNewData(typeList);
                break;
        }
    }


    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshClassify.finishRefresh();
        refreshClassify.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestData();
    }
}
