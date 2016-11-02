package com.ragerpie.ayi.ragerpie.model.beans;

import java.util.List;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderBean {
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
    private int wechatId;
    private String createTime;
    private String updateTime;
    private int status;
    private String remarks;
    private String phone;
    private String address;
    private String realName;
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

    public int getWechatId() {
        return wechatId;
    }

    public void setWechatId(int wechatId) {
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

    public static class GoodsBean {
        private int id;
        private int goodsId;
        private String goodsName;
        private String goodsSize;
        private String goodsNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsSize() {
            return goodsSize;
        }

        public void setGoodsSize(String goodsSize) {
            this.goodsSize = goodsSize;
        }

        public String getGoodsNumber() {
            return goodsNumber;
        }

        public void setGoodsNumber(String goodsNumber) {
            this.goodsNumber = goodsNumber;
        }
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
