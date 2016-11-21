package com.ragerpie.ayi.ragerpie.net.apis.bodybeans;

/**
 * Created by WangBo on 2016/11/21.
 */

public class OrderStateBody {
    private int id;
    private int status;

    public OrderStateBody(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
