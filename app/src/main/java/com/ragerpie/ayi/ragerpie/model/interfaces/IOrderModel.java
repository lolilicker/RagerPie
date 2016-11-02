package com.ragerpie.ayi.ragerpie.model.interfaces;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;

import java.util.List;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by WangBo on 2016/11/2.
 */

public interface IOrderModel {
    void getOrdersByDate(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber);

    void getOrdersByLastId(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber);

    void getUnFinishedOrderList(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber);
}
