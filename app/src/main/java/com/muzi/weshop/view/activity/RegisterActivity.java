package com.muzi.weshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.model.RegisterInfoModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @@author 郑天阳
 */
public class RegisterActivity extends BaseActivity {

    
    @BindView(R.id.edtAccount)
    EditText edtAccount;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtPasswordAgain)
    EditText edtPasswordAgain;
    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {

    }
    
    @OnClick({R.id.tvRegisterBtn})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvRegisterBtn:
                if(TextUtils.isEmpty(edtAccount.getText().toString())){
                    ToastUtils.showShort("请输入用户名");
                    return;
                }
                
                if(TextUtils.isEmpty(edtPassword.getText().toString())){
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                
                if(TextUtils.isEmpty(edtPasswordAgain.getText().toString())){
                    ToastUtils.showShort("请再次输入密码");
                    return;
                }
                
                if(!TextUtils.equals(edtPassword.getText().toString() , edtPasswordAgain.getText().toString())){
                    ToastUtils.showShort("两次输入密码不相同");
                    return;
                }

                Intent intent = new Intent(this , IdentityActivity.class);
                RegisterInfoModel infoModel = new RegisterInfoModel(edtAccount.getText().toString() , edtPassword.getText().toString());
                intent.putExtra(Constants.REGISTER_INFO , infoModel);
                startActivity(intent);
                finish();
                break;
                default:
                    break;
        }
    }

    @Override
    protected void requestData() {

    }
}
