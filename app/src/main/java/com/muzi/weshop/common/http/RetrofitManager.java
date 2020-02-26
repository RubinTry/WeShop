package com.muzi.weshop.common.http;

import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.muzi.weshop.common.WeShopApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @@author 郑天阳
 */
public class RetrofitManager {

    /**
     * 要请求的服务器的地址和端口号
     */
    public static final String BASE_URL = "http://116.62.25.132:8180";



    private static ApiService service;
    
    
    public static ApiService getDefault(){
        //创建一个okhttpClient构造器
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //把所有关于网络请求的日志全部输出在控制台logcat里
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        //加入log日志拦截器
        builder.addInterceptor(httpLoggingInterceptor);


        //把okhttpClient构建出来
        OkHttpClient client = builder
                .connectTimeout(10 , TimeUnit.SECONDS)
                .readTimeout(10 , TimeUnit.SECONDS)
                .writeTimeout(10 , TimeUnit.SECONDS)
                .cache(new Cache(new File(WeShopApplication.getContext().getExternalFilesDir(null) + "/WeShop"), 1024 * 1024 * 10))
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(WeShopApplication.getContext())))
                .retryOnConnectionFailure(true)//是否断线重连
                .build();


        //拿到ApiService对象
            service = new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //加入json转换器（基于gson框架）
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(ApiService.class);
        
        return service;
    }
}
