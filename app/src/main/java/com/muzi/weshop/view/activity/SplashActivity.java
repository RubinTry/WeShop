package com.muzi.weshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.model.LoginModel;
import com.orhanobut.hawk.Hawk;

public class SplashActivity extends BaseActivity {


    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginModel loginModel = Hawk.get(Constants.LOGIN_INFO);
                if(loginModel == null){
                    startActivity(new Intent(SplashActivity.this , LoginActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this , MainActivity.class));
                    finish();
                }
            }
        } , 3000);
    }

    @Override
    protected void requestData() {

    }
}
