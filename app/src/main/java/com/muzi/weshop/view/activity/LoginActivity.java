package com.muzi.weshop.view.activity;

import android.content.Intent;
import android.view.View;

import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author logcat
 */
public class LoginActivity extends BaseActivity {


    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void requestData() {

    }
    
    
    @OnClick({R.id.tvLoginBtn})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvLoginBtn:
                startActivity(new Intent(this , IdentityActivity.class));
                break;
                default:
                    break;
        }
    }
}
