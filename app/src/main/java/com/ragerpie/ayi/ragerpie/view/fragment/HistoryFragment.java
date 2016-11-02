package com.ragerpie.ayi.ragerpie.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.ragerpie.ayi.ragerpie.R;
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
import retrofit2.Response;

/**
 * Created by WangBo on 2016/10/28.
 */

public class HistoryFragment extends BaseFragment {
    @BindView(R.id.rcv_history)
    RecyclerView rcvHistory;

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
        rcvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvHistory.setAdapter(adapter);
        rcvHistory.addItemDecoration(new TitleItemDecoration(dataList, getContext()));
    }

    @Override
    protected void loadData() {
        orderModel.getOrdersByDate(new RagerSubscriber<Response<ResponseWrapper<List<OrderBean>>>>() {
            @Override
            public void onNext(Response<ResponseWrapper<List<OrderBean>>> response) {
                super.onNext(response);
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderBean> orderBeens = response.body().getDATA();
                    dataList.addAll(orderBeens);
                    adapter.notifyDataSetChanged();
                } else {
                    showToast(response.message());
                }
            }
        });


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_history;
    }

    @Override
    public String getTitle() {
        return "历史";
    }
}
