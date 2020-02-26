package com.muzi.weshop.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.model.OrderMsgDtoListBean;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderMsgDtoListBean, BaseViewHolder> {
    private Context context;
    private OrderImgAdapter orderImgAdapter;
    private List<String> imgUrlList;

    public OrderListAdapter(int layoutResId, @Nullable List<OrderMsgDtoListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context.getApplicationContext();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderMsgDtoListBean item) {

        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        TextView tvShopName = helper.itemView.findViewById(R.id.tvShopName);
        tvShopName.setTypeface(iconfont);
        RecyclerView rvOrderImg = helper.itemView.findViewById(R.id.rvOrderImg);
        rvOrderImg.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        imgUrlList = new ArrayList<>();
        orderImgAdapter = new OrderImgAdapter(R.layout.item_order_img, imgUrlList);
        rvOrderImg.setAdapter(orderImgAdapter);

        try {
            if (item.getImg() != null) {
                String[] imgs = item.getImg().split(",");
                if (imgs != null) {
                    for (String img : imgs) {
                        StringBuffer urlBuffer = new StringBuffer();
                        urlBuffer.append(Constants.IMAGE_BASE_URL).append(img);
                        imgUrlList.add(urlBuffer.toString());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        orderImgAdapter.setNewData(imgUrlList);
    }
}
