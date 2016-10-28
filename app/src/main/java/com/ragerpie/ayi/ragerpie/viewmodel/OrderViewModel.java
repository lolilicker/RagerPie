package com.ragerpie.ayi.ragerpie.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.ragerpie.ayi.ragerpie.model.OrderBean;
import com.ragerpie.ayi.ragerpie.util.LogUtils;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;

import java.util.List;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderViewModel {
    private ObservableField<String> name;
    private ObservableField<String> phone;
    private ObservableField<String> weixin;
    private ObservableField<String> time;
    private ObservableField<String> addr;
    private ObservableField<String> deliverTime;
    private ObservableField<String> remark;
    private int indexOfDataList;
    private DataListener dataListener;
    private List<OrderBean> dataList;
    OrderListAdapter adapter;

    public OrderViewModel(int indexOfDataList, OrderListAdapter adapter, List<OrderBean> dataList) {
        this.indexOfDataList = indexOfDataList;
        this.adapter = adapter;
        this.dataList = dataList;
    }

    public void onContainerClick(View view) {
        int lastExpandIndex = adapter.getLastExpandIndex();
        boolean isClickedItemExpanded = dataList.get(indexOfDataList).isExpand();
        if (isClickedItemExpanded) {
            //点的是打开的，关闭
            dataList.get(indexOfDataList).setExpand(false);
            adapter.setLastExpandIndex(-1);
        } else {
            //关闭上一个打开的
            if (lastExpandIndex != -1) {
                dataList.get(lastExpandIndex).setExpand(false);
                adapter.notifyItemChanged(lastExpandIndex);
                LogUtils.d("关闭上一个打开的:" + lastExpandIndex);
            }
            //打开当前点击的
            dataList.get(indexOfDataList).setExpand(true);
            adapter.setLastExpandIndex(indexOfDataList);
        }
        adapter.notifyItemChanged(indexOfDataList);

    }

    public void onInvalidOrder(View view) {

    }

    public void onFinishOrder(View view) {

    }

    public interface DataListener {
        void onDataChanged(int index);
    }
}
