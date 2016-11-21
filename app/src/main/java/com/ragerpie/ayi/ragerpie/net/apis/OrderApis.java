package com.ragerpie.ayi.ragerpie.net.apis;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangBo on 2016/11/2.
 */

public interface OrderApis {
    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByDate();

    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByLastId(@Query("orderId") int orderId);

    @GET("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getUnFinishedOrderList();

    @POST("order/update.json")
//    Observable<Response<ResponseWrapper<OrderBean>>> updateOrderStatus(@Body HashMap<String, String> body);
    Observable<Response<ResponseWrapper<OrderBean>>> updateOrderStatus(@Query("id") int id,
                                                                       @Query("status") int status);


}
