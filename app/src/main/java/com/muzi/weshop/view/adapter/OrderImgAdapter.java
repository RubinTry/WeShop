package com.muzi.weshop.view.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.common.utils.GlideUtils;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

public class OrderImgAdapter extends BaseQuickAdapter<String , BaseViewHolder> {
    public OrderImgAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        QMUIRadiusImageView imgOrderItem = helper.itemView.findViewById(R.id.imgOrderItem);
        GlideUtils.load(WeShopApplication.getContext() , item , imgOrderItem);
    }
}
