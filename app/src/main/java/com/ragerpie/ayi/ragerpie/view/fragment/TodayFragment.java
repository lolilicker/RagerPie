package com.ragerpie.ayi.ragerpie.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.mock.MockOrderData;
import com.ragerpie.ayi.ragerpie.model.OrderBean;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangBo on 2016/10/28.
 */

public class TodayFragment extends BaseFragment {
    @BindView(R.id.rcv_today)
    RecyclerView rcvToday;

    private OrderListAdapter adapter;
    private List<OrderBean> dataList;
    private final Gson gson = new Gson();

    @Override
    protected void initVariable() {
        dataList = new ArrayList<>();
        adapter = new OrderListAdapter(dataList);
    }

    @Override
    protected void initView(View view) {
        rcvToday.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvToday.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        //TODO 获取数据
        String json = new MockOrderData().getJson();
        List<OrderBean> orderBeanList = gson.fromJson(json, new TypeToken<List<OrderBean>>() {
        }.getType());
        dataList.addAll(orderBeanList);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_today;
    }

    @Override
    public String getTitle() {
        return "今天";
    }
}
