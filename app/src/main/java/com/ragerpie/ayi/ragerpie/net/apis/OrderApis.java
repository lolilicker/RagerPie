package com.ragerpie.ayi.ragerpie.net.apis;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by WangBo on 2016/11/2.
 */

public interface OrderApis {
    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByDate();

    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByLastId();

    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getUnFinishedOrderList();

}
