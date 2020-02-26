package com.muzi.weshop.common.utils;

import com.muzi.weshop.common.WeShopApplication;
import com.muzi.weshop.model.DaoSession;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.model.GoodsModelDao;
import com.muzi.weshop.model.GoodsMsgDtoModel;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DbUtils {
    private static final class SingleTon{
        public static final DbUtils INSTANCE = new DbUtils();
    }

    public static DbUtils getInstance(){
        return SingleTon.INSTANCE;
    }

    private DaoSession getDaoSession(){
        return WeShopApplication.getDaoSession();
    }


    /**
     * 插入商品数据
     * @param model
     */
    public void insertGoods(GoodsModel model){
        getDaoSession().getGoodsModelDao().insertInTx(model);
    }


    /**
     * 删除商品数据
     * @param model
     */
    public void deleteGoods(GoodsModel model){
        getDaoSession().getGoodsModelDao().deleteInTx(model);
    }


    /**
     * 更改商品数据
     * @param model
     */
    public void updateGoods(GoodsModel model){
        getDaoSession().getGoodsModelDao().updateInTx(model);
    }


    /**
     * 根据id查询商品数据
     * @param goodsId
     * @return
     */
    public List<GoodsModel> queryGoodsById(Long goodsId){
        QueryBuilder<GoodsModel> builder = getDaoSession().getGoodsModelDao().queryBuilder();
        return builder.where(GoodsModelDao.Properties.Id.eq(goodsId)).build().list();
    }


    /**
     * 查询全部加进购物车的商品
     * @return
     */
    public List<GoodsModel> queryAllGoods(){
        QueryBuilder<GoodsModel> builder = getDaoSession().getGoodsModelDao().queryBuilder();
        return builder.build().list();
    }
}
