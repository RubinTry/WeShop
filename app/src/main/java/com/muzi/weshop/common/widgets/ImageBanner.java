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

/**
 * @author logcat
 */
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
                    //如果pageCount等于0的话，Viewpager会不知道跳转到哪页而出现异常导致闪退
                    if(pageCount != 0){
                        //翻页
                        setCurrentItem(currentItem);
                        //这是一个接口，用来监听并传递页码
                        if (onPageScrollListener != null) {
                            onPageScrollListener.onPageScroll(currentItem);
                        }
                        //页码的大小处理，如果大于总页数就取它的余数，防止下标越界
                        if (currentItem < pageCount - 1) {
                            currentItem++;
                        } else {
                            currentItem++;
                            if(pageCount != 0){
                                currentItem %= pageCount;
                            }
                        }



                        //每隔多少秒滚动一次
                        if(canScroll){
                            bannerHandler.removeMessages(MESSAGE_WHAT);
                            Message newMsg = new Message();
                            newMsg.what = MESSAGE_WHAT;
                            bannerHandler.sendMessageDelayed(newMsg, 1000);
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
        //给写好的线程发送一个消息，消息的what值为MESSAGE_WHAT（1111）
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


    /**
     * 目的：把当前页的位置传给fragment,让TextView显示出当前页数
     */
    public interface OnPageScrollListener {
        void onPageScroll(int position);
    }
}
