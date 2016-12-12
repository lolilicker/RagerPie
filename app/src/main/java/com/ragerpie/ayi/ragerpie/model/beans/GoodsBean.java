package com.ragerpie.ayi.ragerpie.model.beans;

/**
 * Created by WangBo on 2016/11/2.
 */

public class GoodsBean {

    /**
     * id : 56
     * goods : {"goodsId":4,"goodsName":"Chunky Pinkin'","goodsType":{"typeId":1,"typeName":"Sweet Pie"},"goodsPicurl":"images/ChunkyPinkin.png","goodsPrice":"25","goodsSize":"4''","goodsMaterial":""}
     * goodsName : Chunky Pinkin'
     * goodsSize : undefined
     * goodsNumber : 1
     * goodsPrice : 25
     */

    private int id;
    private GoodsDetail goods;
    private String goodsName;
    private String goodsSize;
    private String goodsNumber;
    private float goodsPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsDetail getGoods() {
        return goods;
    }

    public void setGoods(GoodsDetail goods) {
        this.goods = goods;
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

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public static class GoodsDetail {
        /**
         * goodsId : 4
         * goodsName : Chunky Pinkin'
         * goodsType : {"typeId":1,"typeName":"Sweet Pie"}
         * goodsPicurl : images/ChunkyPinkin.png
         * goodsPrice : 25
         * goodsSize : 4''
         * goodsMaterial :
         */

        private int goodsId;
        private String goodsName;
        private GoodsTypeBean goodsType;
        private String goodsPicurl;
        private float goodsPrice;
        private String goodsSize;
        private String goodsMaterial;

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

        public GoodsTypeBean getGoodsType() {
            return goodsType;
        }

        public void setGoodsType(GoodsTypeBean goodsType) {
            this.goodsType = goodsType;
        }

        public String getGoodsPicurl() {
            return goodsPicurl;
        }

        public void setGoodsPicurl(String goodsPicurl) {
            this.goodsPicurl = goodsPicurl;
        }

        public float getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(float goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsSize() {
            return goodsSize;
        }

        public void setGoodsSize(String goodsSize) {
            this.goodsSize = goodsSize;
        }

        public String getGoodsMaterial() {
            return goodsMaterial;
        }

        public void setGoodsMaterial(String goodsMaterial) {
            this.goodsMaterial = goodsMaterial;
        }

        public static class GoodsTypeBean {
            /**
             * typeId : 1
             * typeName : Sweet Pie
             */

            private int typeId;
            private String typeName;

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }
        }
    }
}
