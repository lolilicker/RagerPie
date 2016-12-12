package com.ragerpie.ayi.ragerpie.viewmodel;

import com.ragerpie.ayi.ragerpie.util.LogUtils;

/**
 * Created by WangBo on 2016/11/2.
 */

public class GoodViewModel {
    private String goodsName;
    private String goodsSize;
    private String goodsNumber;
    private float goodsPrice;

    public GoodViewModel(String goodsName, String goodsSize, String goodsNumber, float goodsPrice) {
        this.goodsName = goodsName;
        this.goodsSize = goodsSize;
        this.goodsNumber = goodsNumber;
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        LogUtils.d("goodsSize = " + goodsSize);
        String size = goodsSize == null || goodsSize.equals("undefined") ? "" : " " + goodsSize;
        return goodsName + size;
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

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public String getGoodsPriceStr() {
        return goodsPrice + "元";
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsCountStr() {
        return goodsNumber + " * ";
    }
}
