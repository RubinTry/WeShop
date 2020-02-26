package com.muzi.weshop.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.utils.GlideUtils;
import com.muzi.weshop.model.GoodsModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


/**
 * @@author 郑天阳
 */
public class ClassGoodsAdapter extends BaseQuickAdapter<GoodsModel , BaseViewHolder> {

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassGoodsAdapter(int layoutResId, @Nullable List<GoodsModel> data) {
        super(layoutResId , data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable GoodsModel goodsModel) {
        StringBuffer urlBuffer = new StringBuffer();
        String imgAddr = "";
        if(goodsModel.getImg().split(",").length > 1){
            imgAddr = goodsModel.getImg().split(",")[0];
        }else{
            imgAddr = goodsModel.getImg();
        }
        urlBuffer.append(Constants.IMAGE_BASE_URL).append(imgAddr);
        ImageView imageView = baseViewHolder.itemView.findViewById(R.id.imgClassGoodsPic);
        GlideUtils.load(WeShopApplication.getContext() , urlBuffer.toString() , imageView);
        baseViewHolder.setText(R.id.tvGoodsName , goodsModel.getGoodName());
        //四舍五入并保留2为小数
        baseViewHolder.setText(R.id.tvGoodsPrice , "￥" + goodsModel.getPrice() + ".00");

        LinearLayout llClassGoodsContainer = baseViewHolder.itemView.findViewById(R.id.llClassGoodsContainer);
        llClassGoodsContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(goodsModel);
                }
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(GoodsModel result);
    }
}
