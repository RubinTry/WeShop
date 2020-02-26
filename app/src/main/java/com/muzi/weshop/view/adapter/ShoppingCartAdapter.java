package com.muzi.weshop.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.utils.GlideUtils;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.model.GoodsMsgDtoModel;
import com.muzi.weshop.model.OrderMsgDtoListBean;

import java.util.List;

/**
 * @author logcat
 */
public class ShoppingCartAdapter extends BaseQuickAdapter<GoodsModel, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;

    private int count;



    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ShoppingCartAdapter(int layoutResId, @Nullable List<GoodsModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsModel item) {
        CheckBox ckNeedCommit = helper.itemView.findViewById(R.id.ckNeedCommit);
        ckNeedCommit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setSelected(isChecked);
                if(onItemClickListener != null){
                    onItemClickListener.onChecked(isChecked , item);
                }
            }
        });
        if(item.getSelected()){
            ckNeedCommit.setChecked(true);
        }else{
            ckNeedCommit.setChecked(false);
        }
        ImageView imgGoodsPic = helper.itemView.findViewById(R.id.imgGoodsPic);
        ImageView imgReduceGoods = helper.itemView.findViewById(R.id.imgReduceGoods);
        ImageView imgAddGoods = helper.itemView.findViewById(R.id.imgAddGoods);
        TextView tvGoodsCount = helper.itemView.findViewById(R.id.tvGoodsCount);

        StringBuffer urlBuffer = new StringBuffer();
        String imgAddr = "";
        if(item.getImg().split(",").length > 1){
            imgAddr = item.getImg().split(",")[0];
        }else{
            imgAddr = item.getImg();
        }
        urlBuffer.append(Constants.IMAGE_BASE_URL).append(imgAddr);
        GlideUtils.load(WeShopApplication.getContext() , urlBuffer.toString() , imgGoodsPic);

        helper.setText(R.id.tvGoodsName , item.getGoodName());
        helper.setText(R.id.tvGoodsPrice , "￥" + item.getPrice() + ".00");
        helper.setText(R.id.tvGoodsCount , item.getCount() + "");
        imgAddGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onAddClick(item.getCount() , item , tvGoodsCount);
                }
            }
        });


        imgReduceGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onReduceClick(item.getCount() , item , tvGoodsCount);
                }
            }
        });
    }



    public interface OnItemClickListener{
        void onAddClick(int count , GoodsModel goodsModel , TextView tvGoodsCount);

        void onReduceClick(int count , GoodsModel goodsModel , TextView tvGoodsCount);

        void onChecked(boolean isChecked , GoodsModel goodsModel);
    }
}
