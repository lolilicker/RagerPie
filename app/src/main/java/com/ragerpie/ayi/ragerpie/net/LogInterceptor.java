package com.ragerpie.ayi.ragerpie.net;

import com.ragerpie.ayi.ragerpie.util.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by WangBo on 2016/9/28.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //增加Request log信息
        long t1 = System.nanoTime();
        LogUtils.i((String.format("Sending [%s] request =====> %s on %s%n%s", request.method(),
                request.url(), chain.connection(), request.headers())));

        //进行请求
        Response response = chain.proceed(request);
        //body()方法只能调一次，之后会close
        String bodyString = response.body().string();
        MediaType contentType = response.body().contentType();
        //增加Response log信息
        long t2 = System.nanoTime();
        String responseLog = String.format("Received response [%d] <===== from %s in %.1fms%n%s",
                response.code(), response.request().url(), (t2 - t1) / 1e6d, bodyString);
        if (response.isSuccessful()) {
            LogUtils.i(responseLog);
        } else {
            LogUtils.e(responseLog);
        }
        ResponseBody body = ResponseBody.create(contentType, bodyString);
        //旧的response对象的body已经被close了，新建一个
        Response newResponse = response.newBuilder().body(body).build();
        return newResponse;
    }
}
