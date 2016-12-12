package com.ragerpie.ayi.ragerpie.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.config.Constants;
import com.ragerpie.ayi.ragerpie.event.OrderStatusChangedEvent;
import com.ragerpie.ayi.ragerpie.model.beans.GoodsBean;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.model.beans.ResponseWrapper;
import com.ragerpie.ayi.ragerpie.model.impls.OrderModel;
import com.ragerpie.ayi.ragerpie.model.interfaces.IOrderModel;
import com.ragerpie.ayi.ragerpie.net.RagerSubscriber;
import com.ragerpie.ayi.ragerpie.util.LogUtils;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderListAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Response;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderViewModel {
    private IOrderModel orderModel;
    private Context context;
    private Toast toast;

    /**
     * xml用到的字段
     */
    private String realName;
    private String phone;
    private String wechatId;
    private String time;
    private String address;
    private String sendMessage;
    private String remarks;
    private Float totalPrice;
    private String message;
    private int status;
    private List<GoodsBean> goodList;

    private ObservableInt controlLlVisibility;
    private ObservableInt stateTvVisibility;
    private ObservableField<String> statusStr;

    //头像
    private ObservableField<String> headStr;
    private ObservableInt headBg;
    private ObservableInt headStrColor;

    private int indexOfDataList;
    private List<OrderBean> dataList;
    private OrderListAdapter adapter;


    public OrderViewModel(Context context, int indexOfDataList, OrderListAdapter adapter, List<OrderBean> dataList) {
        this.context = context;
        this.indexOfDataList = indexOfDataList;
        this.adapter = adapter;
        this.dataList = dataList;
        orderModel = new OrderModel();
        controlLlVisibility = new ObservableInt();
        stateTvVisibility = new ObservableInt();
        statusStr = new ObservableField<>();
        headStr = new ObservableField<>();
        headBg = new ObservableInt();
        headStrColor = new ObservableInt();

        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }

    public void fillData(String realName, String phone, String wechatId, String time,
                         String address, String sendMessage, String remarks,
                         Float totalPrice,
                         int status, List<GoodsBean> goodList) {
        this.realName = realName;
        this.phone = phone;
        this.wechatId = wechatId;
        this.time = time;
        this.address = address;
        this.sendMessage = sendMessage;
        this.remarks = remarks;
        this.goodList = goodList;
        this.totalPrice = totalPrice;
        this.status = status;
        message = "￥" + totalPrice + " | " + sendMessage;
        updateStat(status);
    }

    public String getRealName() {
        return realName;
    }

    public String getPhone() {
        return phone;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public String getRemarks() {
        return remarks;
    }

    public List<GoodsBean> getGoodList() {
        return goodList;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoneStr() {
        return "电话：" + phone;
    }

    public String getWeichatStr() {
        return "微信：" + realName;
    }

    public String getAddrStr() {
        return "地址：" + address;
    }

    public String getSendMessageStr() {
        return "时间：" + sendMessage;
    }

    public String getRemarkStr() {
        return "备注：" + remarks;
    }

    public String getTotalPriceStr() {
        return totalPrice + "元";
    }

    public ObservableInt getControlLlVisibility() {
        return controlLlVisibility;
    }

    public ObservableInt getStateTvVisibility() {
        return stateTvVisibility;
    }

    public ObservableField<String> getStatusStr() {
        return statusStr;
    }

    public ObservableField<String> getHeadStr() {
        return headStr;
    }

    public ObservableInt getHeadBg() {
        return headBg;
    }

    public ObservableInt getHeadStrColor() {
        return headStrColor;
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
            if (lastExpandIndex != -1 && lastExpandIndex < dataList.size()) {
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
        orderModel.invalidOrder(dataList.get(indexOfDataList).getId(),
                new OrderSubscriber(OrderBean.STATE_NOUSED));
    }

    public void onFinishOrder(View view) {
        updateStat(OrderBean.STATE_DEAL);
        orderModel.finishOrder(dataList.get(indexOfDataList).getId(),
                new OrderSubscriber(OrderBean.STATE_DEAL));
    }

    private class OrderSubscriber extends RagerSubscriber<Response<ResponseWrapper<OrderBean>>> {

        private int status;

        public OrderSubscriber(int status) {
            this.status = status;
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            toast.setText("网络或服务器错误");
            toast.show();
            EventBus.getDefault().post(new OrderStatusChangedEvent(0,
                    0));
        }

        @Override
        public void onNext(Response<ResponseWrapper<OrderBean>> o) {
            if (o.isSuccessful() && o.body().isSUCCESS()) {
                updateStat(status);
            } else {
                if (o.isSuccessful()) {
                    toast.setText(o.body().getMESSAGE());
                    OrderBean orderBean = o.body().getDATA();
                    EventBus.getDefault().post(new OrderStatusChangedEvent(orderBean.getId(),
                            orderBean.getStatus()));
                } else {
                    toast.setText("未知错误");
                }
                toast.show();
            }
        }
    }

    private void updateStat(int stat) {
        this.status = stat;
        //数据
        dataList.get(indexOfDataList).setStatus(stat);
        //底部操作按钮可见性
        updateBottomStatePanelVisibility(stat);
        //底部订单状态
        statusStr.set(Constants.resolveStatusStr(stat));
        //左侧头像
        updateHeadStr(stat);
    }

    /**
     * 底部订单操作按钮可见性
     */
    private void updateBottomStatePanelVisibility(int stat) {
        switch (stat) {
            case OrderBean.STATE_CREATE:
            case OrderBean.STATE_SENT:
                controlLlVisibility.set(View.VISIBLE);
                stateTvVisibility.set(View.GONE);
                break;
            case OrderBean.STATE_NOUSED:
            case OrderBean.STATE_DEAL:
            case OrderBean.STATE_DELETE:
                controlLlVisibility.set(View.GONE);
                stateTvVisibility.set(View.VISIBLE);
                break;
        }
    }

    /**
     * 左侧头像文字
     */
    private void updateHeadStr(int stat) {
        switch (stat) {
            case OrderBean.STATE_CREATE:
            case OrderBean.STATE_SENT:
                String nameChar = (realName == null || realName.length() == 0)
                        ? "?"
                        : String.valueOf(realName.charAt(0));
                headStr.set(nameChar);
                headBg.set(context.getResources().getColor(R.color.colorPrimary));
                headStrColor.set(context.getResources().getColor(R.color.white));
                break;
            case OrderBean.STATE_DEAL:
                headStr.set("OK");
                headBg.set(context.getResources().getColor(R.color.green));
                headStrColor.set(context.getResources().getColor(R.color.white));
                break;
            case OrderBean.STATE_DELETE:
            case OrderBean.STATE_NOUSED:
                headStr.set("DEL");
                headBg.set(context.getResources().getColor(R.color.gray));
                headStrColor.set(context.getResources().getColor(R.color.white));
                break;
        }
    }
}
