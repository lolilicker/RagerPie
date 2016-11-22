package com.ragerpie.ayi.ragerpie.net.apis;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangBo on 2016/11/2.
 */

public interface OrderApis {
    @POST("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByDate(@Query("starttime") String startTime,
                                                                              @Query("endtime") String endTime);

    @POST("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByLastId(@Query("orderId") int orderId);

    @POST("order/list.json")
    Observable<Response<ResponseWrapper<List<OrderBean>>>> getOrderListByStatus(@Query("status") int status);

    @POST("order/update.json")
//    Observable<Response<ResponseWrapper<OrderBean>>> updateOrderStatus(@Body HashMap<String, String> body);
    Observable<Response<ResponseWrapper<OrderBean>>> updateOrderStatus(@Query("id") int id,
                                                                       @Query("status") int status);


}
