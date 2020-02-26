package com.muzi.weshop.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.muzi.weshop.common.http.presenter.IBasePresenter;
import com.muzi.weshop.common.presenter.ApiPresenter;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBasePresenter {
    private View rootView;
    private BaseActivity context;
    public ApiPresenter apiPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(attachedLayoutRes() , container , false);
        ButterKnife.bind(this , rootView);
        context = (BaseActivity) getActivity();
        apiPresenter = new ApiPresenter(getContext() , this);
        initData();
        initViews();
        requestData();
        return rootView;
    }


    /**
     * 布局文件绑定
     * @return
     */
    protected abstract int attachedLayoutRes();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /***
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 发起请求
     */
    protected abstract void requestData();


    @Nullable
    @Override
    public BaseActivity getContext() {
        return context;
    }

    @Override
    public void onNext(Object o, int requestCode) {
        
    }

    @Override
    public void onError(Throwable e, int request) {

    }
}
