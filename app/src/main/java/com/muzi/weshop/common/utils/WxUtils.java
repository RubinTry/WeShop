package com.muzi.weshop.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.muzi.weshop.R;
import com.muzi.weshop.common.contants.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

public class WxUtils {

    private static Context mContext;
    private static int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private static IWXAPI api;

    
    private static String buildTransaction(final String type){
        return(type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    public static void share(Context context , String url){
        WxUtils.mContext = context.getApplicationContext();
        api = WXAPIFactory.createWXAPI(mContext, Constants.APP_ID ,false);

        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;

//用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = mContext.getResources().getString(R.string.app_name);
        msg.description ="阳阳的小店铺是一个时尚达人，以衣物推荐为主的时尚型电商 App，提倡只推好货，商品下方有真实的时尚达人的评价，提供购物前后包括使用技巧以及小知识等一系列帮助。";
        Bitmap thumbBmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_icon);
        msg.thumbData = bmpToByteArray(thumbBmp, true);

//构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        req.userOpenId = Constants.APP_ID;

//调用api接口，发送数据到微信
        api.sendReq(req);
    }


    /**
     * 位图转为字节数组
     * @param bmp
     * @param needRecycle
     * @return
     */
    private static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
