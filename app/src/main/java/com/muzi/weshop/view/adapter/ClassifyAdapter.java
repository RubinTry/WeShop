package com.muzi.weshop.view.adapter;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.utils.GlideUtils;
import com.muzi.weshop.model.ClassTypesModel;
import java.util.List;

/**
 * 分类列表的适配器
 * @author muzi
 */
public class ClassifyAdapter extends BaseQuickAdapter<ClassTypesModel , BaseViewHolder> {

    private OnItemClickListener onItemClickListener;
    private Fragment fragment;

    public ClassifyAdapter(int layoutResId, @Nullable List<ClassTypesModel> data , Fragment fragment) {
        super(layoutResId, data);
        this.fragment = fragment;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * 把拿到的数据给显示在对应的控件上
     * 每次渲染item的时候都会调用这个方法
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, ClassTypesModel item) {
        StringBuffer urlBuffer = new StringBuffer();
        //拼接图片地址
        urlBuffer.append(Constants.IMAGE_BASE_URL).append(item.getImage());
        //获取到图片控件
        ImageView imgCommodityType = helper.itemView.findViewById(R.id.imgCommodityType);
        //用Glide框架去把图片绘制到imgCommodityType上
        GlideUtils.load(fragment , urlBuffer.toString() , imgCommodityType);
        helper.setText(R.id.tvTypeName , item.getClassName());
        helper.itemView.findViewById(R.id.llClassifyContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(item);
                }
            }
        });
    }
    

    

    
    
    public interface OnItemClickListener{
        void onItemClick(ClassTypesModel result);
    }
}
