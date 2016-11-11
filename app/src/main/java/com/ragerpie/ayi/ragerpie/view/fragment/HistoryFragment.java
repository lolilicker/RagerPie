package com.ragerpie.ayi.ragerpie.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.event.DatePickedEvent;
import com.ragerpie.ayi.ragerpie.event.FloatActionScrollEvent;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.impls.OrderModel;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerSubscriber;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;
import com.ragerpie.ayi.ragerpie.view.widget.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import retrofit2.Response;

/**
 * Created by WangBo on 2016/10/28.
 */

public class HistoryFragment extends BaseFragment {
    @BindView(R.id.rcv_history)
    RecyclerView recyclerView;

    private OrderListAdapter adapter;
    private List<OrderBean> dataList;
    private final Gson gson = new Gson();
    private IOrderModel orderModel;

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
        recyclerView.addItemDecoration(new TitleItemDecoration(dataList, getContext()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (HistoryFragment.this.recyclerView.computeVerticalScrollOffset() <= 0) {
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    public String getTitle() {
        return "历史";
    }

    @Override
    public void refreshData() {
        loadData();
    }

    @Override
    public void scrollFragment() {
        recyclerView.smoothScrollToPosition(0);
    }

    public void onEvent(DatePickedEvent event) {
        showToast(event.year + "-" + event.monthOfYear + "-" + event.dayOfMonth);
    }
}
