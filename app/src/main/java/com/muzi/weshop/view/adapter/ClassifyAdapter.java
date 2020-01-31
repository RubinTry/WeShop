package com.muzi.weshop.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.common.utils.GlideUtils;
import com.muzi.weshop.model.ClassifyContentModel;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author muzi
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ClassifyContentViewHolder> {

    private List<ClassifyContentModel> dataList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassifyAdapter(List<ClassifyContentModel> dataList) {
        this.dataList = dataList;
    }


    public void setDataList(List<ClassifyContentModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ClassifyAdapter.ClassifyContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify_content, parent, false);
        ClassifyAdapter.ClassifyContentViewHolder classifyContentViewHolder = new ClassifyAdapter.ClassifyContentViewHolder(view);
        return classifyContentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifyContentViewHolder holder, int position) {
        ClassifyContentModel item = dataList.get(position);
        GlideUtils.load(WeShopApplication.getContext() , item.getResId() , holder.imgClassifyContent);
        holder.tvClassifyContent.setText(item.getTitle());
        //适配宽高
        ViewGroup.LayoutParams imgParams = holder.imgClassifyContent.getLayoutParams();
        imgParams.width = ScreenUtils.getScreenWidth() / 2 - SizeUtils.dp2px(64);
        imgParams.height = imgParams.width;
        holder.imgClassifyContent.setLayoutParams(imgParams);
        holder.glClassifyContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ClassifyContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgClassifyContent)
        ImageView imgClassifyContent;
        @BindView(R.id.tvClassifyContent)
        TextView tvClassifyContent;
        @BindView(R.id.glClassifyContainer)
        GridLayout glClassifyContainer;
        public ClassifyContentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }

    
    
    public interface OnItemClickListener{
        void onItemClick(ClassifyContentModel result);
    }
}
