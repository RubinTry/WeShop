package com.muzi.weshop.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author muzi
 * 图片加载工具
 */
public class GlideUtils {
    public static void load(Context context , int resId , ImageView imageView){
        Glide.with(context)
                .asBitmap()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(resId)
                .into(imageView);
    }
}
