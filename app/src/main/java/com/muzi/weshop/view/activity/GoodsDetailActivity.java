package com.muzi.weshop.view.activity;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.muzi.weshop.R;
import com.muzi.weshop.common.LoginManager;
import com.muzi.weshop.common.base.BaseActivity;
import com.muzi.weshop.common.contants.BusConstants;
import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.common.contants.ExtraConstants;
import com.muzi.weshop.common.contants.RequestCodeContants;
import com.muzi.weshop.common.utils.DbUtils;
import com.muzi.weshop.common.widgets.ImageBanner;
import com.muzi.weshop.model.CommentListModel;
import com.muzi.weshop.model.CommentsModel;
import com.muzi.weshop.model.GoodsDetailModel;
import com.muzi.weshop.model.GoodsModel;
import com.muzi.weshop.view.adapter.CommentAdapter;
import com.muzi.weshop.view.adapter.ImagePagerAdapter;
import com.muzi.weshop.view.adapter.LongImgAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @@author 郑天阳
 */
public class GoodsDetailActivity extends BaseActivity implements ImageBanner.OnPageScrollListener, OnRefreshListener, OnLoadMoreListener {

    private Long id;
    private GoodsModel goodsModel;
    private String goodsName;

    @BindView(R.id.tbGoodsDetail)
    TabLayout tbGoodsDetail;
    @BindView(R.id.tvGoodsTitle)
    TextView tvGoodsTitle;
    @BindView(R.id.imgGoodsDetailBanner)
    ImageBanner imgGoodsDetailBanner;
    @BindView(R.id.tvPageIndicator)
    TextView tvPageIndicator;
//    @BindView(R.id.imgPicDetail)
//    ImageView imgPicDetail;
    @BindView(R.id.nslGoodsDetail)
    NestedScrollView nslGoodsDetail;
    @BindView(R.id.llPicDetail)
    LinearLayout llPicDetail;
    @BindView(R.id.rvComment)
    RecyclerView rvComment;
    @BindView(R.id.llComment)
    LinearLayout llComment;
    @BindView(R.id.llDetailGoods)
    LinearLayout llDetailGoods;
    @BindView(R.id.refreshGoodsDetail)
    SmartRefreshLayout refreshGoodsDetail;
    @BindView(R.id.imgAddToShoppingCart)
    ImageView imgAddToShoppingCart;
    @BindView(R.id.llOrderOperaContainer)
    LinearLayout llOrderOperaContainer;
    @BindView(R.id.tvGoodsName)
    TextView tvGoodsName;
    @BindView(R.id.tvGoodsPrice)
    TextView tvGoodsPrice;
    @BindView(R.id.edtAddComment)
    EditText edtAddComment;
    @BindView(R.id.tvGoodsCount)
    TextView tvGoodsCount;
    @BindView(R.id.llAddToShoppingCartContainer)
    LinearLayout llAddToShoppingCartContainer;
    @BindView(R.id.llSelectType)
    LinearLayout llSelectType;
    @BindView(R.id.rvImgList)
    RecyclerView rvImgList;

    private CommentAdapter commentAdapter;
    private LongImgAdapter longimgAdapter;
    private List<CommentsModel> commentList;

    private ImagePagerAdapter imagePagerAdapter;
    private List<String> titleList;
    private List<String> imageUrlList;
    private List<String> longImgList;
    private int tabIndex = 0;
    private int commentPageNum;
    private int commentPosition;
    private boolean scrollviewFlag = false;

    /**
     * 已添加的货物数量
     */
    private int goodsCount = 0;
    private GoodsDetailModel goodsDetailModel;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        imgGoodsDetailBanner.release();
    }

    @Override
    protected boolean hideStatusBar() {
        return true;
    }

    @Override
    protected int attachedLayout() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initViews() {
        commentPageNum = 1;
        goodsModel = new Gson().fromJson(getIntent().getStringExtra(ExtraConstants.GOODS_MODEL) , GoodsModel.class);
        goodsName = goodsModel.getGoodName();
        if (!TextUtils.isEmpty(goodsName)) {
            tvGoodsTitle.setText(goodsName);
        }
        id = goodsModel.getId();
        if(DbUtils.getInstance().queryGoodsById(id).size() > 0){
            goodsCount = DbUtils.getInstance().queryGoodsById(id).get(0).getCount();
            tvGoodsCount.setText(goodsCount + "");
        }

        //如果是商家就不能买东西
        if(LoginManager.getInstance().getPersonal().getLink().equals("1")){
            llAddToShoppingCartContainer.setVisibility(View.GONE);
            llSelectType.setVisibility(View.GONE);
            imgAddToShoppingCart.setVisibility(View.GONE);
        }else{
            llAddToShoppingCartContainer.setVisibility(View.VISIBLE);
            llSelectType.setVisibility(View.VISIBLE);
            imgAddToShoppingCart.setVisibility(View.VISIBLE);
        }
        initBanner();
        initDetail();
        initRecyclerView();
        titleList = new ArrayList<>();
        titleList.add("商品");
        titleList.add("详情");
        titleList.add("评价");

        tbGoodsDetail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!scrollviewFlag) {
                    switch (tab.getPosition()) {
                        case 0://scrollview移动到tv1的顶部坐标处
                            nslGoodsDetail.scrollTo(0, llDetailGoods.getTop());
                            break;
                        case 1://scrollview移动到tv2的顶部坐标处
                            nslGoodsDetail.scrollTo(0, llPicDetail.getTop());
                            break;
                        case 2://scrollview移动到tv3的顶部坐标处
                            nslGoodsDetail.scrollTo(0, llComment.getTop());
                            break;
                    }
                }
                //用户点击tablayout时，标记不是scrollview主动滑动
                scrollviewFlag = false;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < titleList.size(); i++) {
            if (i == 0) {
                tbGoodsDetail.addTab(tbGoodsDetail.newTab().setText(titleList.get(i)), true);
            } else {
                tbGoodsDetail.addTab(tbGoodsDetail.newTab().setText(titleList.get(i)));
            }
        }

        nslGoodsDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollviewFlag = true;
                tabIndex = tbGoodsDetail.getSelectedTabPosition();
                if (scrollY < llPicDetail.getTop()) {
                    if (tabIndex != 0) {//增加判断，如果滑动的区域是tableIndex=0对应的区域，则不改变tablayout的状态
                        tbGoodsDetail.getTabAt(0).select();
                    }
                } else if (scrollY >= llPicDetail.getTop() && scrollY < llComment.getTop()) {
                    if (tabIndex != 1) {
                        tbGoodsDetail.getTabAt(1).select();
                    }
                } else if (scrollY >= llComment.getTop()) {
                    if (tabIndex != 2) {
                        tbGoodsDetail.getTabAt(2).select();
                    }
                }
                scrollviewFlag = false;
            }
        });

        refreshGoodsDetail.setOnRefreshListener(this);
        refreshGoodsDetail.setOnLoadMoreListener(this);
    }

    private void initRecyclerView() {
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setNestedScrollingEnabled(false);
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(R.layout.item_comment, commentList);
        rvComment.setAdapter(commentAdapter);
        commentAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {


            @Override
            public void onThumbUp(int id, int position) {
                //点赞
                apiPresenter.goodsThumbUp(id, 0, RequestCodeContants.THUMB_UP);
                commentPosition = position;
            }

            @Override
            public void onCancelThumbUp(int id, int position) {
                //取消点赞
                apiPresenter.goodsThumbUp(id, 1, RequestCodeContants.CANCEL_THUMB_UP);
                commentPosition = position;
            }
        });


        rvImgList.setLayoutManager(new LinearLayoutManager(this));
        rvImgList.setNestedScrollingEnabled(false);
        longimgAdapter = new LongImgAdapter(R.layout.item_long_img , imageUrlList);
        rvImgList.setAdapter(longimgAdapter);
    }

    private void initDetail() {
//        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.pic_detail);
//        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
//                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        drawable.draw(canvas);
//        imgPicDetail.setImageBitmap(bitmap);
    }

    private void initBanner() {
        imageUrlList = new ArrayList<>();
        longImgList = new ArrayList<>();
        imagePagerAdapter = new ImagePagerAdapter(this, imageUrlList);
        imgGoodsDetailBanner.setOffscreenPageLimit(5);
        imgGoodsDetailBanner.setAdapter(imagePagerAdapter);
        imagePagerAdapter.notifyDataSetChanged();
        imgGoodsDetailBanner.setCount(imageUrlList.size());
        imgGoodsDetailBanner.setOnPageScrollListener(this);
    }

    @Override
    protected void requestData() {
        apiPresenter.getGoodsDetail(id, RequestCodeContants.GET_GOODS_DETAIL);
        apiPresenter.getComment(id, commentPageNum, 10, RequestCodeContants.GET_COMMENTS);
    }

    @OnClick({R.id.imgReduceGoods , R.id.imgAddGoods , R.id.tvShowComment, R.id.tvSendComment, R.id.imgBtnBack,  R.id.imgAddToShoppingCart})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBtnBack:
                finish();
                break;
            case R.id.imgAddToShoppingCart:
                BusUtils.post(BusConstants.JUMP_TO_SHOPPING_CART);
                finish();
                break;
            case R.id.tvSendComment:
                apiPresenter.addComment(edtAddComment.getText().toString(), id, LoginManager.getInstance().getPersonalId(), RequestCodeContants.ADD_COMMENT);
                break;
            case R.id.tvShowComment:
                //选中第三个tab
                tbGoodsDetail.getTabAt(2).select();
                break;
            case R.id.imgAddGoods:
                goodsCount ++;
                tvGoodsCount.setText(goodsCount + "");
                goodsModel.setCount(goodsCount);
                //如果缓存中有这个商品
                if(DbUtils.getInstance().queryGoodsById(id).size() > 0){
                    DbUtils.getInstance().updateGoods(goodsModel);
                }else{
                    DbUtils.getInstance().insertGoods(goodsModel);
                }
                ToastUtils.showShort("添加成功");
                break;
            case R.id.imgReduceGoods:
                //商品件数不能小于0
                if(goodsCount > 0){
                    goodsCount --;
                    tvGoodsCount.setText(goodsCount + "");


                    goodsModel.setCount(goodsCount);
                    //如果缓存中有这个商品
                    if(DbUtils.getInstance().queryGoodsById(id).size() > 0){
                        DbUtils.getInstance().updateGoods(goodsModel);
                    }else{
                        DbUtils.getInstance().insertGoods(goodsModel);
                    }
                    ToastUtils.showShort("减少成功");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScroll(int position) {
        tvPageIndicator.setText("第" + (position + 1) + "页/共" + imageUrlList.size() + "页");
    }


    @Override
    public void onNext(Object o, int requestCode) {
        super.onNext(o, requestCode);
        refreshGoodsDetail.finishLoadMore();
        refreshGoodsDetail.finishRefresh();
        switch (requestCode) {
            case RequestCodeContants.GET_COMMENTS:
                CommentListModel commentListModel = (CommentListModel) o;
                if (commentListModel.getCommentDtos() != null) {
                    if (commentPageNum == 1) {
                        commentList.clear();
                        commentList.addAll(commentListModel.getCommentDtos());
                        commentAdapter.setNewData(commentList);
                    } else {
                        commentAdapter.addData(commentListModel.getCommentDtos());
                    }
                }

                break;

            case RequestCodeContants.GET_COMMENTS_UPDATE:
                CommentListModel commentModel = (CommentListModel) o;
                if (commentModel.getCommentDtos() != null) {
                    if (commentPageNum == 1) {
                        commentList.clear();
                        commentList.addAll(commentModel.getCommentDtos());
                        commentAdapter.setNewData(commentList);
                    } else {
                        commentAdapter.addData(commentModel.getCommentDtos());
                    }
                }
                //直接选中“评价”tab，这样能看到最新的评论（最顶上的评论）
                tbGoodsDetail.getTabAt(2).select();
                break;

            case RequestCodeContants.THUMB_UP:
                ToastUtils.showShort("点赞成功");
                int oldBulous = commentList.get(commentPosition).getBulous();
                oldBulous++;
                commentList.get(commentPosition).setBulous(oldBulous);
                commentList.get(commentPosition).setClicked(true);
                commentAdapter.notifyItemChanged(commentPosition);
                break;
            case RequestCodeContants.CANCEL_THUMB_UP:
                ToastUtils.showShort("取消点赞成功");
                int oldBulous2 = commentList.get(commentPosition).getBulous();
                oldBulous2--;
                commentList.get(commentPosition).setBulous(oldBulous2);
                commentList.get(commentPosition).setClicked(false);
                commentAdapter.notifyItemChanged(commentPosition);
                break;
            case RequestCodeContants.GET_GOODS_DETAIL:
                goodsDetailModel = (GoodsDetailModel) o;
                tvGoodsName.setText(goodsDetailModel.getGoodsMsgDto().getGoodName());
                tvGoodsPrice.setText("￥" + goodsDetailModel.getGoodsMsgDto().getPrice() + ".00");
                String[] imgList = goodsDetailModel.getGoodsMsgDto().getImg().split(",");
                imageUrlList.clear();
                longImgList.clear();
                for (String s : imgList) {
                    imageUrlList.add(Constants.IMAGE_BASE_URL + s);
                    longImgList.add(Constants.IMAGE_BASE_URL + s);
                }
                longimgAdapter.setNewData(longImgList);
                imagePagerAdapter.notifyDataSetChanged();
                imgGoodsDetailBanner.start();
                break;
            case RequestCodeContants.ADD_COMMENT:
                edtAddComment.setText("");
                ToastUtils.showShort("添加成功");

                //刷新评价数据
                apiPresenter.getComment(id, commentPageNum, 10, RequestCodeContants.GET_COMMENTS_UPDATE);
                break;
            case RequestCodeContants.ADD_ORDER:
                ToastUtils.showShort("成功添加到购物车");
                goodsCount = 0;
                tvGoodsCount.setText("0");
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(Throwable e, int request) {
        super.onError(e, request);
        refreshGoodsDetail.finishLoadMore();
        refreshGoodsDetail.finishRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        commentPageNum = 1;
        requestData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        commentPageNum++;
        requestData();
    }

}
