package com.muzi.weshop.view.activity;

import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.model.RegisterInfoModel;
import com.muzi.weshop.model.BaseModel;

import butterknife.OnClick;

/**
 * @@author 郑天阳
 * 身份选择页
 */
public class IdentityActivity extends BaseActivity {


    private RegisterInfoModel infoModel;
    private String TAG = this.getClass().getSimpleName();

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
        infoModel = (RegisterInfoModel) getIntent().getSerializableExtra(Constants.REGISTER_INFO);
        
    }

    @Override
    protected void requestData() {

    }
    
    @OnClick({R.id.clImSeller , R.id.clImBuyer})
    void onClick(View view){
        switch (view.getId()){
            case R.id.clImSeller:
                //卖家
                apiPresenter.register(infoModel.getAccount() , "1" , infoModel.getPassword() ,  RequestCodeContants.REGISTER);
                break;
            case R.id.clImBuyer:
                //买家
                apiPresenter.register(infoModel.getAccount() , "1" , infoModel.getPassword() ,  RequestCodeContants.REGISTER);
                break;
                default:
                    break;
        }
    }


    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode){
            case RequestCodeContants.REGISTER:
                Log.d(TAG, "onNext: " + o);
                BaseModel baseModel = (BaseModel) o;
                if(baseModel.getCode() == 0){
                    //成功
                    ToastUtils.showShort("注册成功");
                    finish();
                }else{
                    ToastUtils.showShort("注册失败: " + baseModel.getMsg());
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
    }
}
