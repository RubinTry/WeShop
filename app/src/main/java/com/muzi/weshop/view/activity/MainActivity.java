package com.muzi.weshop.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.BusConstants;
import com.muzi.weshop.common.widgets.FitSystemWindowViewPager;
import com.muzi.weshop.view.adapter.MainPagerAdapter;
import com.muzi.weshop.view.fragment.ClassifyFragment;
import com.muzi.weshop.view.fragment.HomeFragment;
import com.muzi.weshop.view.fragment.MineFragment;
import com.muzi.weshop.view.fragment.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @@author 郑天阳
 */
public class MainActivity extends BaseActivity {


    private static final int REQUEST_PERMISSION = 11111;
    @BindView(R.id.tbMain)
    TabLayout tbMain;
    @BindView(R.id.vpMain)
    FitSystemWindowViewPager vpMain;
    private MainPagerAdapter mainPagerAdapter;
    private List<Fragment> pageList;
    private List<String> titleList;
    private String TAG = "MainActivity";


    @Override
    protected boolean hideStatusBar() {
        return true;
    }



    @Override
    protected int attachedLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusUtils.unregister(this);
    }

    @Override
    protected void initViews() {
        BusUtils.register(this);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, REQUEST_PERMISSION);
        }//请求权限


        //用来储存fragment的list
        pageList = new ArrayList<>();
        //用来储存标题字符串的list
        titleList = new ArrayList<>();


        //加入五个tab标题
        titleList.add("首页");
        titleList.add("分类");
        //如果是顾客才有购物车
        if(LoginManager.getInstance().getPersonal() != null){
            if(LoginManager.getInstance().getPersonal().getLink().equals("0")){
                titleList.add("购物车");
            }

        }
        titleList.add("我的");


        //加入四个fragment
        pageList.add(new HomeFragment());
        pageList.add(new ClassifyFragment());
        //如果是顾客才有购物车
        if(LoginManager.getInstance().getPersonal().getLink().equals("0")){
            pageList.add(new ShoppingCartFragment());
        }
        pageList.add(new MineFragment());



        //绑定 左右滑动控件 的适配器
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, pageList);
        vpMain.setAdapter(mainPagerAdapter);//viewPager
        //更新UI
        mainPagerAdapter.notifyDataSetChanged();


        //要渲染几个fragment
        vpMain.setOffscreenPageLimit(4);
        //添加一个 页面滑动 的监听，跟tabLayout联动
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbMain));

        //监听底部导航栏点击
        tbMain.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            /**
             * 选中执行这里
             * @param tab
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab选中时调用这里    变红
                changeTabSelected(tab);
            }


            /**
             *
             * 取消选中执行这里
             * @param tab
             */
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab释放时调用这里   恢复成原来的样子
                recoveryTab(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //往tablayout里添加tab
        for (int i = 0; i < titleList.size(); i++) {

            if (i == 0) {
                //第一个tab要默认选中
                tbMain.addTab(tbMain.newTab().setCustomView(getTabView(i)), true);
            } else {
                //不是第一个就执行下面的代码
                tbMain.addTab(tbMain.newTab().setCustomView(getTabView(i)));
            }
        }
    }


    /**
     * 恢复tab样式
     *
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
                if(LoginManager.getInstance().getPersonal().getLink().equals("0")){
                    //顾客
                    imgTabItem.setImageResource(R.mipmap.shopping_car_normal);
                }else{
                    //商家
                    imgTabItem.setImageResource(R.mipmap.mine_normal);
                }

                break;
            case 3:
                imgTabItem.setImageResource(R.mipmap.mine_normal);
                break;
            default:
                break;
        }
        tvTabItem.setTextColor(ContextCompat.getColor(this, R.color.txtNormalColor));
    }


    /**
     * 改变tab样式
     * 同时，执行翻页操作
     *
     * @param tab
     */
    private void changeTabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView imgTabItem = view.findViewById(R.id.imgTabItem);
        AppCompatTextView tvTabItem = view.findViewById(R.id.tvTabItem);
        switch (tab.getPosition()) {
            case 0:
                //渲染对应的图片
                imgTabItem.setImageResource(R.mipmap.home_select);
                //翻到第1页
                vpMain.setCurrentItem(0);
                break;
            case 1:
                imgTabItem.setImageResource(R.mipmap.type_select);
                //翻到第2页
                vpMain.setCurrentItem(1);
                break;
            case 2:
                if(LoginManager.getInstance().getPersonal().getLink().equals("0")){
                    //顾客
                    imgTabItem.setImageResource(R.mipmap.shopping_car_select);
                }else{
                    //商家
                    imgTabItem.setImageResource(R.mipmap.mine_select);
                }
                vpMain.setCurrentItem(2);
                break;
            case 3:
                imgTabItem.setImageResource(R.mipmap.mine_select);
                vpMain.setCurrentItem(3);
                break;
            default:
                break;
        }
        tvTabItem.setTextColor(ContextCompat.getColor(this, R.color.mainColor));
    }


    /**
     * 获取tab样式
     *
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
                if(LoginManager.getInstance().getPersonal().getLink().equals("0")){
                    //顾客
                    imgTabItem.setImageResource(R.mipmap.shopping_car_normal);
                }else{
                    //商家
                    imgTabItem.setImageResource(R.mipmap.mine_normal);
                }
                break;
            case 3:
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


    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {
        //跳转至桌面而不销毁本activity
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            ToastUtils.showShort("您拒绝了权限，将影响app的部分功能正常使用");
            Log.d(TAG, "授权失败: ");
        }
    }


    @BusUtils.Bus(tag = BusConstants.JUMP_TO_SHOPPING_CART, threadMode = BusUtils.ThreadMode.MAIN)
    public void jumpToShoppingCart() {
        tbMain.getTabAt(2).select();
    }
}
