package com.ragerpie.ayi.ragerpie.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.ragerpie.ayi.ragerpie.model.OrderBean;

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

    public OrderViewModel(int indexOfDataList, DataListener dataListener, List<OrderBean> dataList) {
        this.indexOfDataList = indexOfDataList;
        this.dataListener = dataListener;
        this.dataList = dataList;
    }

    public void onContainerClick(View view) {
        dataList.get(indexOfDataList).setExpand(!dataList.get(indexOfDataList).isExpand());
        if (dataListener != null) {
            dataListener.onDataChanged();
        }
    }

    public void onInvalidOrder(View view) {

    }

    public void onFinishOrder(View view) {

    }

    public interface DataListener {
        void onDataChanged();
    }
}
