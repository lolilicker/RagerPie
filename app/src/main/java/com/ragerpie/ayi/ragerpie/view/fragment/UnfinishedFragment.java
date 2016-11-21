package com.ragerpie.ayi.ragerpie.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.event.FloatActionScrollEvent;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.impls.OrderModel;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerSubscriber;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import retrofit2.Response;

/**
 * Created by WangBo on 2016/10/28.
 */

public class UnfinishedFragment extends BaseFragment {
    @BindView(R.id.rcv_unfinished)
    RecyclerView recyclerView;

    private OrderListAdapter adapter;
    private List<OrderBean> dataList;
    private IOrderModel orderModel;
    private boolean enableEventBus;

    @Override
    protected void initVariable() {
        dataList = new ArrayList<>();
        adapter = new OrderListAdapter(dataList);
        orderModel = new OrderModel();
    }

    @Override
    protected void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!enableEventBus) return;
                if (UnfinishedFragment.this.recyclerView.computeVerticalScrollOffset() <= 0) {
                    EventBus.getDefault().post(new FloatActionScrollEvent(false));
                } else {
                    EventBus.getDefault().post(new FloatActionScrollEvent(true));
                }
            }
        });
    }

    @Override
    protected void loadData() {
        orderModel.getOrdersByDate(new RagerSubscriber<Response<ResponseWrapper<List<OrderBean>>>>() {
            @Override
            public void onNext(Response<ResponseWrapper<List<OrderBean>>> response) {
                super.onNext(response);
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderBean> orderBeans = response.body().getDATA();
                    Collections.reverse(orderBeans);
                    dataList.clear();
                    dataList.addAll(orderBeans);
                    adapter.notifyDataSetChanged();
                } else {
                    showToast(response.message());
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_unfinished;
    }

    @Override
    public String getTitle() {
        return "未处理";
    }

    @Override
    public void refreshData() {
        loadData();
    }

    @Override
    public void scrollFragment() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onFragmentHiddenChanged(boolean hidden) {
        enableEventBus = !hidden;
        if (!enableEventBus) return;
        loadData();
        if (UnfinishedFragment.this.recyclerView.computeVerticalScrollOffset() <= 0) {
            EventBus.getDefault().post(new FloatActionScrollEvent(false));
        } else {
            EventBus.getDefault().post(new FloatActionScrollEvent(true));
        }
    }
}
