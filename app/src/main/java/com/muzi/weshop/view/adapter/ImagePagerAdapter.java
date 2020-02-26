package com.muzi.weshop.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.muzi.weshop.R;
import com.muzi.weshop.common.utils.GlideUtils;

import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {
    
    private Context context;
    private List<String> urlList;

    public ImagePagerAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context , R.layout.item_image , null);
        //这里开始绘制图片
        ImageView imgItem = view.findViewById(R.id.imgItem);
        if(urlList.size() > 0 && urlList != null){
            String item = urlList.get(position);
            GlideUtils.load(context , item , imgItem);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
