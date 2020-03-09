package com.muzi.weshop.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseFragment;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.widgets.ImageBanner;
import com.muzi.weshop.view.activity.SearchActivity;
import com.muzi.weshop.view.adapter.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author muzi
 * 首页
 */
public class HomeFragment extends BaseFragment implements ImageBanner.OnPageScrollListener {
    @BindView(R.id.imgBanner)
    ImageBanner imgBanner;
    @BindView(R.id.tvPageCurrentNum)
    TextView tvPageCurrentNum;
    private ImagePagerAdapter imagePagerAdapter;
    private List<String> imageUrlList;
    @Override
    protected int attachedLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        imageUrlList = new ArrayList<>();
        imageUrlList.add(Constants.IMAGE_BASE_URL + "short/1.jpg");
        imageUrlList.add(Constants.IMAGE_BASE_URL + "short/2.jpg");
        imageUrlList.add(Constants.IMAGE_BASE_URL + "short/7.jpg");
        imageUrlList.add(Constants.IMAGE_BASE_URL + "short/8.jpg");
        imageUrlList.add(Constants.IMAGE_BASE_URL + "trousers/1.jpg");
    }

    @Override
    protected void initViews() {
        //
        imagePagerAdapter = new ImagePagerAdapter(getContext() , imageUrlList);
        imgBanner.setOffscreenPageLimit(5);
        imgBanner.setAdapter(imagePagerAdapter);
        imagePagerAdapter.notifyDataSetChanged();

        imgBanner.setCount(imageUrlList.size());
        imgBanner.start();
        imgBanner.setOnPageScrollListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        imgBanner.start();
    }

    @Override
    protected void requestData() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        imgBanner.release();
    }

    /**
     * 图片翻页监听
     * @param position
     */
    @Override
    public void onPageScroll(int position) {
        tvPageCurrentNum.setText("第" + (position + 1) +"页/共" + imageUrlList.size() + "页");
    }

    
    @OnClick({R.id.tvSearchGoods})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tvSearchGoods:
                Intent intent = new Intent(getContext() , SearchActivity.class);
                getContext().startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
