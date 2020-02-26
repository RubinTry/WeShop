package com.muzi.weshop.common;

import android.app.Application;
import android.content.Context;

import com.muzi.weshop.R;
import com.muzi.weshop.common.utils.DbOpenHelper;
import com.muzi.weshop.model.DaoMaster;
import com.muzi.weshop.model.DaoSession;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import org.greenrobot.greendao.database.Database;

/**
 * @@author 郑天阳
 */
public class WeShopApplication extends Application {
    
    private static Context context;


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white);//全局设置主题颜色
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Hawk.init(this).build();

        initDataBase();
    }

    private void initDataBase() {
        DbOpenHelper helper = new DbOpenHelper(this, "WeShop.db");
        Database db = helper.getEncryptedWritableDb("muzi");
        daoSession = new DaoMaster(db).newSession();
    }


    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getContext(){
        return context;
    }
}
