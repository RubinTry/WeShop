package com.muzi.weshop.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.model.LoginModel;
import com.orhanobut.hawk.Hawk;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @@author 郑天阳
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.edtAccount)
    EditText edtAccount;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    
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
    
    
    @OnClick({R.id.tvLoginBtn , R.id.tvRegister})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvLoginBtn:
                //发起一个登录请求，等待服务器上处理完之后，我们这边再做对应的处理
                apiPresenter.login(edtAccount.getText().toString() , edtPassword.getText().toString() , RequestCodeContants.LOGIN);
                break;
            case R.id.tvRegister:
                startActivity(new Intent(this , RegisterActivity.class));
                break;
                default:
                    break;
        }
    }


    /**
     * 请求成功就执行这里的代码
     * @param o
     * @param requestCode
     */
    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        switch (requestCode){
            case RequestCodeContants.LOGIN:
                //o是一个已经转换好的一个javaBean对象，只不过我们现在没法直接访问Object里面的数据，所以需要写一个JavaBean自己去强转
                LoginModel loginModel = (LoginModel) o;
                LoginManager.getInstance().setLoginInfo(loginModel);
                startActivity(new Intent(this , MainActivity.class));
                finish();
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
