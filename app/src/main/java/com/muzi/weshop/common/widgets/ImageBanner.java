package com.muzi.weshop.common.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class ImageBanner extends ViewPager {
    private static final int MESSAGE_WHAT = 1111;
    private Handler bannerHandler;
    private int pageCount;
    private int currentItem;
    private String TAG = this.getClass().getSimpleName();
    private OnPageScrollListener onPageScrollListener;
    
    private boolean canScroll;

    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener) {
        this.onPageScrollListener = onPageScrollListener;
    }

    public ImageBanner(@NonNull Context context) {
        super(context);
    }

    public ImageBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        canScroll = true;
        currentItem = 0;
        bannerHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == MESSAGE_WHAT) {
                    if(pageCount != 0){
                        setCurrentItem(currentItem);
                        if (onPageScrollListener != null) {
                            onPageScrollListener.onPageScroll(currentItem);
                        }
                        if (currentItem < pageCount - 1) {
                            currentItem++;
                        } else {
                            currentItem++;
                            if(pageCount != 0){
                                currentItem %= pageCount;
                            }
                        }


                        if(canScroll){
                            bannerHandler.removeMessages(MESSAGE_WHAT);
                            Message newMsg = new Message();
                            newMsg.what = MESSAGE_WHAT;
                            bannerHandler.sendMessageDelayed(newMsg, 5000);
                        }
                    }
                }
            }
        };
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        if(onPageScrollListener != null){
            onPageScrollListener.onPageScroll(position);
        }
    }

    public void start() {
        //开始轮播
        Message msg = new Message();
        msg.what = MESSAGE_WHAT;
        bannerHandler.sendMessage(msg);
        pageCount = getChildCount();
    }

    

    public void setCount(int count) {
        pageCount = count;
    }
    
    public void release(){
        canScroll = false;
    }


    public interface OnPageScrollListener {
        void onPageScroll(int position);
    }
}
