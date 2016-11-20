package com.ragerpie.ayi.ragerpie.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.config.AppConfig;
import com.ragerpie.ayi.ragerpie.event.FloatActionScrollEvent;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.impls.OrderModel;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerSubscriber;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by WangBo on 2016/10/28.
 */

public class TodayFragment extends BaseFragment {
    @BindView(R.id.rcv_today)
    RecyclerView recyclerView;

    private OrderListAdapter adapter;
    private List<OrderBean> dataList;
    private final Gson gson = new Gson();
    private IOrderModel orderModel;
    private Subscription timerSubscription;
    private LinearLayoutManager layoutManager;

    private boolean enableEventBus;

    @Override
    protected void initVariable() {
        dataList = new ArrayList<>();
        adapter = new OrderListAdapter(dataList);
        orderModel = new OrderModel();
        layoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!enableEventBus) return;
                if (TodayFragment.this.recyclerView.computeVerticalScrollOffset() <= 0) {
                    EventBus.getDefault().post(new FloatActionScrollEvent(false));
                } else {
                    EventBus.getDefault().post(new FloatActionScrollEvent(true));
                }
            }
        });
    }

    @Override
    protected void loadData() {
        loadOrders();
        timerSubscription = Observable.interval(AppConfig.REFRESH_TIME, TimeUnit.SECONDS, Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        loadNewOrders();
                    }
                });
    }

    private void loadOrders() {
        orderModel.getOrdersByDate(new RagerSubscriber<Response<ResponseWrapper<List<OrderBean>>>>() {
            @Override
            public void onNext(Response<ResponseWrapper<List<OrderBean>>> response) {
                super.onNext(response);
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderBean> orderBeens = response.body().getDATA();
                    dataList.clear();
                    dataList.addAll(orderBeens);
                    adapter.notifyDataSetChanged();
                } else {
                    showToast(response.message());
                }
            }
        });
    }

    private void loadNewOrders() {
        orderModel.getOrdersByDate(new RagerSubscriber<Response<ResponseWrapper<List<OrderBean>>>>() {
            @Override
            public void onNext(Response<ResponseWrapper<List<OrderBean>>> response) {
                super.onNext(response);
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderBean> orderBeens = response.body().getDATA();
//                    dataList.clear();
                    dataList.addAll(0, orderBeens);
                    adapter.notifyItemRangeChanged(0, dataList.size());
                    if (checkItemOpened() != 0){
                        layoutManager.scrollToPositionWithOffset(checkItemOpened(), 0);
                    }
//                    recyclerView.scrollToPosition(checkItemOpened());
                } else {
                    showToast(response.message());
                }
            }
        });
    }

    private int checkItemOpened() {
        for (OrderBean orderBean : dataList) {
            if (orderBean.isExpand()) return dataList.indexOf(orderBean);
        }
        return 0;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_today;
    }

    @Override
    public String getTitle() {
        return "今天";
    }

    @Override
    public void refreshData() {
        loadOrders();
    }

    @Override
    public void onDestroy() {
        timerSubscription.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void scrollFragment() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onFragmentHiddenChanged(boolean hidden) {
        if (hidden) {
            //停止insert后发送EventBus
            enableEventBus = false;
        } else {
            enableEventBus = true;
        }

        if (!enableEventBus) return;
        if (TodayFragment.this.recyclerView.computeVerticalScrollOffset() <= 0) {
            EventBus.getDefault().post(new FloatActionScrollEvent(false));
        } else {
            EventBus.getDefault().post(new FloatActionScrollEvent(true));
        }
    }
}
