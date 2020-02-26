package com.muzi.weshop.common.utils;

import android.content.Context;

import com.muzi.weshop.model.DaoMaster;
import com.muzi.weshop.model.GoodsModelDao;

import org.greenrobot.greendao.database.Database;


/**
 * @author logcat
 * 数据库创建帮助类
 */
public class DbOpenHelper extends DaoMaster.DevOpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },  GoodsModelDao.class);

    }
}
