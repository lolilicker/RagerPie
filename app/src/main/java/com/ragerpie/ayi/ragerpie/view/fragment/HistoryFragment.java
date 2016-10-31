package com.ragerpie.ayi.ragerpie.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.mock.MockOrderData;
import com.ragerpie.ayi.ragerpie.model.OrderBean;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;
import com.ragerpie.ayi.ragerpie.view.widget.TitleItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by WangBo on 2016/10/28.
 */

public class HistoryFragment extends BaseFragment {
    @BindView(R.id.rcv_history)
    RecyclerView rcvHistory;

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
        rcvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvHistory.setAdapter(adapter);
        rcvHistory.addItemDecoration(new TitleItemDecoration(dataList, getContext()));
    }

    @Override
    protected void loadData() {
        //TODO 获取数据
        String json = new MockOrderData().getJson();
        List<OrderBean> orderBeanList = gson.fromJson(json, new TypeToken<List<OrderBean>>() {
        }.getType());

        Observable.from(orderBeanList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OrderBean>() {
                    int i = 0;

                    @Override
                    public void call(OrderBean orderBean) {
                        if (i < 8) {
                            orderBean.setDate("今天");
                        } else {
                            orderBean.setDate("昨天");
                        }
                        i++;
                        dataList.add(orderBean);
                        adapter.notifyItemInserted(i);
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
