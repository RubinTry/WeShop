package com.muzi.weshop.view.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.muzi.weshop.R;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.view.adapter.MainPagerAdapter;
import com.muzi.weshop.view.fragment.ClassifyFragment;
import com.muzi.weshop.view.fragment.DiscoverFragment;
import com.muzi.weshop.view.fragment.HomeFragment;
import com.muzi.weshop.view.fragment.MineFragment;
import com.muzi.weshop.view.fragment.ShoppingCarFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author logcat
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tbMain)
    TabLayout tbMain;
    @BindView(R.id.vpMain)
    ViewPager vpMain;
    private MainPagerAdapter mainPagerAdapter;
    private List<Fragment> pageList;
    private List<String> titleList;
    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        pageList = new ArrayList<>();
        titleList = new ArrayList<>();
        //加入五个标题
        titleList.add("首页");
        titleList.add("分类");
        titleList.add("发现");
        titleList.add("购物车");
        titleList.add("我的");
        //加入五个碎片页
        pageList.add(new HomeFragment());
        pageList.add(new ClassifyFragment());
        pageList.add(new DiscoverFragment());
        pageList.add(new ShoppingCarFragment());
        pageList.add(new MineFragment());
        //绑定 左右滑动控件 的适配器
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager() , FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT , pageList);
        vpMain.setAdapter(mainPagerAdapter);
        mainPagerAdapter.notifyDataSetChanged();
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbMain));
        tbMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab选中时调用这里
                changeTabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab释放时调用这里
                recoveryTab(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //往tablayout里添加tab
        for (int i = 0; i < titleList.size(); i++) {
            if(i == 0){
                tbMain.addTab(tbMain.newTab().setCustomView(getTabView(i)) , true);
            }else{
                tbMain.addTab(tbMain.newTab().setCustomView(getTabView(i)));
            }
        }
    }


    /**
     * 恢复tab样式
     * @param tab
     */
    private void recoveryTab(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView imgTabItem = view.findViewById(R.id.imgTabItem);
        AppCompatTextView tvTabItem = view.findViewById(R.id.tvTabItem);
        switch (tab.getPosition()) {
            case 0:
                imgTabItem.setImageResource(R.mipmap.home_normal);
                break;
            case 1:
                imgTabItem.setImageResource(R.mipmap.type_normal);
                break;
            case 2:
                imgTabItem.setImageResource(R.mipmap.discover_normal);
                break;
            case 3:
                imgTabItem.setImageResource(R.mipmap.shopping_car_normal);
                break;
            case 4:
                imgTabItem.setImageResource(R.mipmap.mine_normal);
                break;
                default:
                    break;
        }
        tvTabItem.setTextColor(ContextCompat.getColor(this, R.color.txtNormalColor));
    }


    /**
     * 改变tab样式
     * @param tab
     */
    private void changeTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView imgTabItem = view.findViewById(R.id.imgTabItem);
        AppCompatTextView tvTabItem = view.findViewById(R.id.tvTabItem);
        switch (tab.getPosition()) {
            case 0:
                imgTabItem.setImageResource(R.mipmap.home_select);
                vpMain.setCurrentItem(0);
                break;
            case 1:
                imgTabItem.setImageResource(R.mipmap.type_select);
                vpMain.setCurrentItem(1);
                break;
            case 2:
                imgTabItem.setImageResource(R.mipmap.discover_select);
                vpMain.setCurrentItem(2);
                break;
            case 3:
                imgTabItem.setImageResource(R.mipmap.shopping_car_select);
                vpMain.setCurrentItem(3);
                break;
            case 4:
                imgTabItem.setImageResource(R.mipmap.mine_select);
                vpMain.setCurrentItem(4);
                break;
            default:
                break;
        }
        tvTabItem.setTextColor(ContextCompat.getColor(this, R.color.mainColor));
    }


    /**
     * 获取tab样式
     * @param position
     * @return
     */
    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        AppCompatTextView tvTabItem = view.findViewById(R.id.tvTabItem);
        tvTabItem.setText(titleList.get(position));

        ImageView imgTabItem = view.findViewById(R.id.imgTabItem);
        switch (position) {
            case 0:
                imgTabItem.setImageResource(R.mipmap.home_normal);
                break;
            case 1:
                imgTabItem.setImageResource(R.mipmap.type_normal);
                break;
            case 2:
                imgTabItem.setImageResource(R.mipmap.discover_normal);
                break;
            case 3:
                imgTabItem.setImageResource(R.mipmap.shopping_car_normal);
                break;
            case 4:
                imgTabItem.setImageResource(R.mipmap.mine_normal);
                break;
            default:
                break;
        }
        
        return view;
    }

    @Override
    protected void requestData() {

    }
}
