package com.muzi.weshop.view.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.common.utils.GlideUtils;

import java.util.List;

/**
 * @author logcat
 * 详情长图适配器
 */
public class LongImgAdapter extends BaseQuickAdapter<String , BaseViewHolder> {
    public LongImgAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imgPicDetail = helper.itemView.findViewById(R.id.imgPicDetail);
        GlideUtils.load(WeShopApplication.getContext() , item , imgPicDetail);
    }
}
