package com.muzi.weshop.view.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.muzi.weshop.R;
import com.muzi.weshop.model.CommentsModel;

import java.util.List;


/**
 * @author 郑天阳
 * 评论列表适配器
 */
public class CommentAdapter extends BaseQuickAdapter<CommentsModel , BaseViewHolder> {
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CommentAdapter(int layoutResId, @Nullable List<CommentsModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentsModel item) {
        helper.setText(R.id.tvCommentNickName , item.getAccount());
        helper.setText(R.id.tvCommentContent , item.getComment());


        //获取到"已点赞"和"未点赞"两种状态的图标
        ImageView imgZan = helper.itemView.findViewById(R.id.imgZan);
        ImageView imgZan2 = helper.itemView.findViewById(R.id.imgZan2);
        if(item.isClicked()){
            //把图标变成点赞状态
            imgZan2.setVisibility(View.VISIBLE);
            imgZan.setVisibility(View.GONE);
        }else{
            //把图标变成未点赞状态
            imgZan2.setVisibility(View.GONE);
            imgZan.setVisibility(View.VISIBLE);
        }


        if(item.getBulous() != 0){

            int bulous = item.getBulous();
            if(bulous > 9999){
                //如果点赞数量大于9999，则换算成以w结尾的数 ， 如： 5.0w
                float bulousF = bulous / 10000f;
                bulousF = (float) ((int)(bulousF * 10) / (double)10);
                helper.setText(R.id.tvBulous , String.valueOf(bulousF)+ "w");
            }else{
                //如果点赞数量未过万，则直接显示真实数字
                helper.setText(R.id.tvBulous , String.valueOf(bulous));
            }
        }else{
            helper.setText(R.id.tvBulous , "赞");
        }
        
        //绑定点赞事件
        imgZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onThumbUp(item.getId() , helper.getAdapterPosition());
                }
                imgZan.setVisibility(View.GONE);
                imgZan2.setVisibility(View.VISIBLE);
            }
        });
        
        //绑定取消点赞事件
        imgZan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onCancelThumbUp(item.getId() , helper.getAdapterPosition());
                }
                imgZan.setVisibility(View.VISIBLE);
                imgZan2.setVisibility(View.GONE);
            }
        });
    }
    
    
    public interface OnItemClickListener{
        /**
         * 点赞回调
         * @param id  评论id
         */
        void onThumbUp(int id , int position);

        /**
         * 取消点赞回调
         * @param id  评论id
         */
        void onCancelThumbUp(int id , int position);
    }
}
