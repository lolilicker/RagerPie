package com.ragerpie.ayi.ragerpie.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ragerpie.ayi.ragerpie.BR;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.util.LogUtils;
import com.ragerpie.ayi.ragerpie.viewmodel.OrderViewModel;

import java.util.List;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderListAdapter extends RecyclerView.Adapter<BindingHolder> {
    private static final int TYPE_CLOSE = 0;
    private static final int TYPE_OPEN_UNFINISH = 1;
    private static final int TYPE_OPEN_FINISH = 2;

    private List<OrderBean> dataList;
    //上一个展开的item
    private int lastExpandIndex = -1;

    public OrderListAdapter(List<OrderBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId = viewType == TYPE_CLOSE ? R.layout.item_order_list : R.layout.item_order_detail;
        View viewDataBinding = LayoutInflater.from(parent.getContext())
                .inflate(resId, parent, false);
        LogUtils.d("onCreateViewHolder" + viewType);
        return new BindingHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        LogUtils.d("onBindViewHolder " + position);
        OrderBean orderBean = dataList.get(position);
        OrderViewModel orderViewModel = new OrderViewModel(holder.itemView.getContext(), position, this, dataList);

        orderViewModel.fillData(
                orderBean.getRealName(),
                orderBean.getPhone(),
                orderBean.getWechatId(),
                orderBean.getCreateTime().split(" ")[1],
                orderBean.getAddress(),
                orderBean.getSendMessage(),
                orderBean.getRemarks(),
                orderBean.getTotalPrice(),
                orderBean.getStatus(),
                orderBean.getGoods());

        if (getItemViewType(position) != TYPE_CLOSE) {
            //打开的话展示订单内容
            holder.recyclerView.setAdapter(new OrderGoodsAdapter(orderBean.getGoods()));
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        }
        holder.getBinding().setVariable(BR.order, orderViewModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        OrderBean orderBean = dataList.get(position);
        if (!orderBean.isExpand()) {
            return TYPE_CLOSE;
        } else {
            if (orderBean.getStatus() == OrderBean.STATE_DEAL) {
                return TYPE_OPEN_FINISH;
            } else {
                return TYPE_OPEN_UNFINISH;
            }
        }
    }

    public int getLastExpandIndex() {
        return lastExpandIndex;
    }

    public void setLastExpandIndex(int lastExpandIndex) {
        this.lastExpandIndex = lastExpandIndex;
    }
}
