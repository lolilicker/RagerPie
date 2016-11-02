package com.ragerpie.ayi.ragerpie.net;


import com.ragerpie.ayi.ragerpie.config.Constants;
import com.ragerpie.ayi.ragerpie.context.AppContext;
import com.ragerpie.ayi.ragerpie.util.DeviceUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WangBo on 2016/9/28.
 */

public class CacheInterceptor implements Interceptor {

    private static final File cacheFile = new File(AppContext.getInstance().getExternalCacheDir(),
            Constants.RETROFIT_CACHE_FILE);
    private static final Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!DeviceUtil.hasInternet(AppContext.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (DeviceUtil.hasInternet(AppContext.getInstance())) {
            int maxAge = 900;
            // 有网络时 设置缓存超时时间一天
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }

    public static Cache getCache() {
        return cache;
    }
}
