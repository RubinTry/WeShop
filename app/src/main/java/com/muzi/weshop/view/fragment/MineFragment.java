package com.muzi.weshop.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseFragment;
import com.muzi.weshop.common.utils.WxUtils;
import com.muzi.weshop.view.activity.OrderListActivity;
import com.muzi.weshop.view.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author muzi
 * 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        tvNickName.setText(LoginManager.getInstance().getAccount());
    }

    @Override
    protected void requestData() {

    }
    
    @OnClick({R.id.llShare , R.id.llMyOrders , R.id.tvMineSetting})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvMineSetting:
                getContext().startActivity(new Intent(getContext() , SettingActivity.class));
                break;
            case R.id.llMyOrders:
                //进入订单列表
                startActivity(new Intent(getContext() , OrderListActivity.class));
                break;
            case R.id.llShare:
                WxUtils.share(getContext() , "http://www.baidu.com");
                break;
                default:
                    break;
        }
    }
}
