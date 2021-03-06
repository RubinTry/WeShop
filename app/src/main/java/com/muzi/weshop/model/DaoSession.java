package com.muzi.weshop.model;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.muzi.weshop.model.GoodsModel;

import com.muzi.weshop.model.GoodsModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig goodsModelDaoConfig;

    private final GoodsModelDao goodsModelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        goodsModelDaoConfig = daoConfigMap.get(GoodsModelDao.class).clone();
        goodsModelDaoConfig.initIdentityScope(type);

        goodsModelDao = new GoodsModelDao(goodsModelDaoConfig, this);

        registerDao(GoodsModel.class, goodsModelDao);
    }
    
    public void clear() {
        goodsModelDaoConfig.clearIdentityScope();
    }

    public GoodsModelDao getGoodsModelDao() {
        return goodsModelDao;
    }

}
