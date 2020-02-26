package com.muzi.weshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseActivity;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void requestData() {

    }
    
    @OnClick({R.id.imgBtnBack , R.id.tvLogout})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvLogout:
                //清除登录信息
                LoginManager.getInstance().clearLoginInfo();
                //跳转登录页
                startActivity(new Intent(this , LoginActivity.class));
                //销毁除登录页外的所有activity
                ActivityUtils.finishAllActivities();
                ToastUtils.showShort("退出成功");
                break;
            case R.id.imgBtnBack:
                finish();
                break;
                default:
                    break;
        }
    }
}
