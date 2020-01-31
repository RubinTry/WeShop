package com.muzi.weshop.view.fragment;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.DiffTCallback;
import com.muzi.weshop.common.base.BaseFragment;
import com.muzi.weshop.model.ClassifyContentModel;
import com.muzi.weshop.model.RecyclerData;
import com.muzi.weshop.view.adapter.ClassifyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author muzi
 * 分类
 */
public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.rvCommodityType)
    RecyclerView rvCommodityType;
    private ClassifyAdapter classifyAdapter;
    private List<ClassifyContentModel> typeList;
    private List<ClassifyContentModel> clearTypeList;
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
        rvCommodityType.setLayoutManager(new GridLayoutManager(getContext() , 2));
        rvCommodityType.setNestedScrollingEnabled(false);
        classifyAdapter = new ClassifyAdapter(typeList);
        rvCommodityType.setAdapter(classifyAdapter);
        classifyAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ClassifyContentModel result) {
                ToastUtils.showShort(result.getTitle());
            }
        });
    }

    @Override
    protected void requestData() {
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_01 , "橘色毛衣"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_02 , "爱心毛衣"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_03 , "王炸牛仔裤"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_04 , "米色毛呢裤"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_05 , "长靴"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_06 , "毛毛球鞋"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_01 , "橘色毛衣"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_02 , "爱心毛衣"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_03 , "王炸牛仔裤"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_04 , "米色毛呢裤"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_05 , "长靴"));
        typeList.add(new ClassifyContentModel(R.mipmap.classify_content_06 , "毛毛球鞋"));
        updateView();
    }

    private void updateView() {
        typeDiffResult = DiffUtil.calculateDiff(new DiffTCallback<ClassifyContentModel>(clearTypeList , typeList) , false);
        typeDiffResult.dispatchUpdatesTo(classifyAdapter);
        classifyAdapter.setDataList(typeList);
        clearTypeList.clear();
        clearTypeList.addAll(typeList);
    }
}
