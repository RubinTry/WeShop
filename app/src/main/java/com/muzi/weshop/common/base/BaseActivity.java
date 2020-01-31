package com.muzi.weshop.common.base;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.logcat.widget.top.Topbar;
import com.logcat.widget.top.callback.BaseOnBackClickListener;
import com.muzi.weshop.R2;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author logcat
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R2.id.mTopBar)
    Topbar topBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachedLayout());
        ButterKnife.bind(this);

        //是否隐藏状态栏（就是显示电量的那个地方）
        if(hideStatusBar()){
            QMUIStatusBarHelper.translucent(this);
        }else{
            BarUtils.setStatusBarColor(this , Color.WHITE);
        }
        BarUtils.setStatusBarLightMode(this , true);


        initViews();
        requestData();
    }

    /**
     * 是否显示状态栏
     * 如果不显示则自动调整为沉浸式状态栏
     * @return
     */
    protected abstract boolean hideStatusBar();

    /**
     * 绑定activity的布局文件
     * @return
     */
    protected abstract int attachedLayout();


    /**
     * 初始化view
     */
    protected abstract void initViews();

    /**
     * 请求网络数据
     */
    protected abstract void requestData();



    /**
     * 初始化标题栏
     */
    private void initTopbar() {
        if (topBar != null) {
            topBar.fitWindow(hideStatusBar());
            topBar.setBackBtnVisible(false);
            setTitleScroll(true);

            //设置默认的返回键点击事件（如有其他需要，可重写setOnBackClickListener）
            topBar.setOnBackClickListener(new BaseOnBackClickListener() {
                @Override
                public void onBackClick() {
                    finish();
                }
            });
            topBar.setRightMenuText("");
            topBar.setRightMenuVisible(false);
            topBar.setImgRightBtnSize(SizeUtils.dp2px(24) , SizeUtils.dp2px(24) , this);
        }
    }


    /**
     * 设置标题是否自动滚动（当文字大于10个字符是，自动开启跑马灯滚动效果）
     * @param scroll
     */
    public void setTitleScroll(boolean scroll) {
        if (scroll) {
            topBar.setTitleScroll(true);
        } else {
            topBar.setTitleScroll(false);
        }
    }


    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitleString(CharSequence title) {
        if (topBar != null && !TextUtils.isEmpty(title)) {
            topBar.setTitle(title);
        }
    }

    /**
     * 设置右侧menu显隐
     *
     * @param visible
     */
    public void setRightMenuVisible(boolean visible) {
        topBar.setRightMenuVisible(visible);
    }


    /**
     * 设置右侧menu文本
     *
     * @param charSequence
     */
    public void setRightMenuString(CharSequence charSequence) {
        topBar.setRightMenuText(charSequence);
    }


    /**
     * 设置右侧menu的文本颜色
     *
     * @param color
     */
    public void setRightMenuTextColor(int color) {
        topBar.setRightMenuTextColor(color);
    }

    public void setOnRightMenuClickListener(Topbar.OnRightMenuClickListener onRightMenuClickListener) {
        topBar.setOnRightMenuClickListener(onRightMenuClickListener);
    }


    /***
     * 设置右侧菜单文字的大小
     * @param spValue
     */
    public void setRightMenuTextSize(int spValue) {
        topBar.setRightMenuTextSize(spValue);
    }

    /**
     * 设置返回键显示/隐藏
     *
     * @param visible
     */
    public void setBackBtnVisible(boolean visible) {
        topBar.setBackBtnVisible(visible);
    }

    /**
     * 设置标题返回键文字
     *
     * @param backBtnText
     */
    public void setBackBtnText(CharSequence backBtnText) {
        if (topBar != null && !TextUtils.isEmpty(backBtnText)) {
            topBar.setBackBtnText(backBtnText);
        }
    }
}
