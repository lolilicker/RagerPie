package com.ragerpie.ayi.ragerpie.net;

import com.ragerpie.ayi.ragerpie.context.AppContext;
import com.ragerpie.ayi.ragerpie.util.DeviceUtil;
import com.ragerpie.ayi.ragerpie.util.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WangBo on 2016/9/28.
 */

public class HeaderInterceptor implements Interceptor {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String OKOER_STAT = "Okoer-Stat";
    private static final String FLAVOR = "android-market";
    private static String deviceInfo;

    public HeaderInterceptor() {
        deviceInfo = DeviceUtil.getDeviceInfoDetail(AppContext.getInstance());
        LogUtils.d("deviceInfo : " + deviceInfo);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //增加token header
        Request newRequest;
        Request.Builder builder = request.newBuilder();
        newRequest = builder.build();

        //进行请求
        Response response = chain.proceed(newRequest);

        return response;
    }
}
