package com.muzi.weshop.view.activity;

import android.content.Intent;
import android.view.View;

import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;

import butterknife.OnClick;

/**
 * @author logcat
 * 身份选择页
 */
public class IdentityActivity extends BaseActivity {


    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_identity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void requestData() {

    }
    
    @OnClick({R.id.clImSeller , R.id.clImBuyer})
    void onClick(View view){
        switch (view.getId()){
            case R.id.clImSeller:
                startActivity(new Intent(this , MainActivity.class));
                break;
            case R.id.clImBuyer:
                startActivity(new Intent(this , MainActivity.class));
                break;
                default:
                    break;
        }
    }
}
