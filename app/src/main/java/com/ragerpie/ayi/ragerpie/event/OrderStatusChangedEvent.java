package com.ragerpie.ayi.ragerpie.event;

/**
 * Created by WangBo on 2016/11/21.
 */

public class OrderStatusChangedEvent {
    private int orderId;
    private int status;

    public OrderStatusChangedEvent(int orderId, int status) {
        this.orderId = orderId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
