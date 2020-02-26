package com.muzi.weshop.common.http;

import com.muzi.weshop.model.ClassGoodsModel;
import com.muzi.weshop.model.ClassifyContentModel;
import com.muzi.weshop.model.CommentListModel;
import com.muzi.weshop.model.GoodsDetailModel;
import com.muzi.weshop.model.LoginModel;
import com.muzi.weshop.model.BaseModel;
import com.muzi.weshop.model.SearchResultModel;
import com.muzi.weshop.model.OrderListModel;
import com.muzi.weshop.model.ThumbModel;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @@author 郑天阳
 */
public interface ApiService {


    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @POST("/tcaBack/login/login")
    Observable<LoginModel> login(@Query("account") String account,
                                 @Query("password") String password);


    /**
     * 注册账号
     * @param account
     * @param link
     * @param password
     * @return
     */
    @POST("/tcaBack/login/register")
    Observable<BaseModel> register(@Query("account") String account,
                                   @Query("link") String link,
                                   @Query("password") String password);


    /**
     * 查询所有分类
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("/tcaBack/class-type/get-class-list")
    Observable<ClassifyContentModel> getTypes(@Query("pageNum") int pageNum,
                                              @Query("pageSize") int pageSize);


    /**
     * 查询分类下的所有商品
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("/tcaBack/class-type/get-class-goods-list")
    Observable<ClassGoodsModel> getClassGoodsList(@Query("id") int id,
                                                  @Query("pageNum") int pageNum,
                                                  @Query("pageSize") int pageSize );


    /**
     * 获取商品评价
     * @param goodsId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("/tcaBack/goods/get-goods-comment-list")
    Observable<CommentListModel> goodsCommentList(@Query("goodsId") Long goodsId,
                                                  @Query("pageNum") int pageNum,
                                                  @Query("pageSize") int pageSize);


    /**
     * 商品评论点赞
     * @param id   评论id
     * @param type 点赞状态 0 ：点赞 1：取消点赞
     * @return
     */
    @GET("/tcaBack/goods/give-the-thumbs-up")
    Observable<ThumbModel> goodsThumbUp(@Query("id") int id,
                                        @Query("type") int type);


    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @GET("/tcaBack/goods/get-goods-msg")
    Observable<GoodsDetailModel> getGoodsDetail(@Query("id") Long id);


    /**
     * 添加评论
     * @param body
     * @return
     */
    @POST("/tcaBack/goods/add-comment")
    Observable<BaseModel> addComment(@Body RequestBody body);


    /**
     * 获取商品列表
     * @param goodsName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("/tcaBack/goods/get-goods-list")
    Observable<SearchResultModel> getGoodsByName(@Query("goodsName") String goodsName,
                                                 @Query("pageNum") int pageNum,
                                                 @Query("pageSize") int pageSize);


    /**
     * 添加订单
     * @param body
     * @return
     */
    @POST("/tcaBack/order/add-order")
    Observable<BaseModel> addOrder(@Body RequestBody body);


    /**
     * 获取订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("/tcaBack/order/get-order-list")
    Observable<OrderListModel> getOrderList(@Query("pageNum") int pageNum,
                                            @Query("pageSize") int pageSize);
}
