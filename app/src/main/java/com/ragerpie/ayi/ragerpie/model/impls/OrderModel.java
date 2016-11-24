package com.ragerpie.ayi.ragerpie.model.impls;

import com.ragerpie.ayi.ragerpie.config.Constants;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerRetrofit;
import com.ragerpie.ayi.ragerpie.net.apis.OrderApis;

import java.util.Calendar;
import java.util.HashMap;
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
    public void getTodayOrder(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        Calendar calendar = Calendar.getInstance();
        String todayDate = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH) + 1) +
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        orderApis.getOrderListByDate(todayDate + Constants.START_TIME_PREFIX, todayDate + Constants.END_TIME_PREFIX)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getTodayAndYesterdayOrder(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        Calendar calendar = Calendar.getInstance();
        String yesterdayDate = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH) + 1) +
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) - 1) + Constants.START_TIME_PREFIX;
        String todayDate = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH) + 1) +
                String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + Constants.END_TIME_PREFIX;
        orderApis.getOrderListByDate(yesterdayDate, todayDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getOrdersByDate(String startDate, String endDate, Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        orderApis.getOrderListByDate(startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getOrdersByLastId(int orderId, Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        orderApis.getOrderListByLastId(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getUnFinishedOrderList(Subscriber<Response<ResponseWrapper<List<OrderBean>>>> subscriber) {
        orderApis.getOrderListByStatus(OrderBean.STATE_CREATE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void invalidOrder(int orderId, Subscriber<Response<ResponseWrapper<OrderBean>>> subscriber) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(orderId));
        params.put("status", String.valueOf(OrderBean.STATE_NOUSED));
        orderApis.updateOrderStatus(orderId, OrderBean.STATE_NOUSED)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void finishOrder(int orderId, Subscriber<Response<ResponseWrapper<OrderBean>>> subscriber) {
//        orderApis.updateOrderStatus(new OrderStateBody(orderId, OrderBean.STATE_DEAL))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);

        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(orderId));
        params.put("status", String.valueOf(OrderBean.STATE_DEAL));
        orderApis.updateOrderStatus(orderId, OrderBean.STATE_DEAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
