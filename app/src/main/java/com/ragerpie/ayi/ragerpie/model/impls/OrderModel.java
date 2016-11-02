package com.ragerpie.ayi.ragerpie.model.impls;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerRetrofit;
import com.ragerpie.ayi.ragerpie.net.apis.OrderApis;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WangBo on 2016/11/2.
 */

public class OrderModel implements IOrderModel {
    private OrderApis orderApis;

    public OrderModel() {
        Retrofit ragerRetrofit = RagerRetrofit.getRetrofit();
        orderApis = ragerRetrofit.create(OrderApis.class);
    }

    @Override
    public void getOrders(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        orderApis.getOrderList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
