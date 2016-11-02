package com.ragerpie.ayi.ragerpie.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WangBo on 2016/8/16.
 */
public class RagerRetrofit {
    private static Retrofit okoerRetrofit;
    private static OkHttpClient okHttpClient;
    private static final Object lock = new Object();


    private static Retrofit createRetrofit() {
        //okHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CacheInterceptor())
                .addInterceptor(new LogInterceptor())
                .cache(CacheInterceptor.getCache())
                .build();

        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetConfig.getEndPoint())
                .build();
    }

    public static Retrofit getRetrofit() {
        synchronized (lock) {
            if (okoerRetrofit == null) {
                synchronized (lock) {
                    if (okoerRetrofit == null) {
                        okoerRetrofit = createRetrofit();
                    }
                }
            }
            return okoerRetrofit;
        }
    }

    /**
     * 条形码接口数据有问题，没法直接用Retrofit返回Gson对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        if (okoerRetrofit == null) {
            getRetrofit();
        }
        return okHttpClient;
    }

}
