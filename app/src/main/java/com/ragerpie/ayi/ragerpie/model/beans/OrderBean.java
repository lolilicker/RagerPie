package com.ragerpie.ayi.ragerpie.model.beans;

import java.util.List;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderBean {
    public static final int STATE_UNFINISH = 0;
    public static final int STATE_FINISH = 1;
    /**
     * id : 52
     * wechatId : 100
     * createTime : 2016-11-01 03:18:09
     * updateTime : 2016-11-01 03:18:09
     * status : 0
     * remarks : 方法大幅
     * phone : 12345678901
     * address : 圣经吧
     * realName : 所发生的
     * goods : [{"id":21,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":22,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]
     */

    private int id;
    private String wechatId;
    private String createTime;
    private String updateTime;
    private int status;
    private String remarks;
    private String phone;
    private String address;
    private String realName;
    private String sendMessage;
    private float totalPrice;
    /**
     * id : 21
     * goodsId : 1
     * goodsName : 名称1
     * goodsSize : 型号1
     * goodsNumber : 1
     */

    private List<GoodsBean> goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * ****************************** 自定义 ***************************
     */

    //是否展开
    private boolean expand;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    //获取日期
    public String getDateStr() {
        return createTime.split(" ")[0];
    }
}
