package com.ragerpie.ayi.ragerpie.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ragerpie.ayi.ragerpie.BR;
import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.model.beans.GoodsBean;
import com.ragerpie.ayi.ragerpie.viewmodel.GoodViewModel;

import java.util.List;

/**
 * Created by WangBo on 2016/11/2.
 */

public class OrderGoodsAdapter extends RecyclerView.Adapter<BindingHolder> {
    private List<GoodsBean> dataList;

    public OrderGoodsAdapter(List<GoodsBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewDataBinding = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_goods, parent, false);
        return new BindingHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        GoodsBean goodsBean = dataList.get(position);
        GoodViewModel goodViewModel = new GoodViewModel(goodsBean.getGoodsName(),
                goodsBean.getGoodsSize(),
                goodsBean.getGoodsNumber(),
                goodsBean.getGoodsPrice());
        holder.getBinding().setVariable(BR.goods, goodViewModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
