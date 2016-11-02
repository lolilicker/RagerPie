package com.ragerpie.ayi.ragerpie.model.beans;

/**
 * Created by WangBo on 2016/11/2.
 */

public class ResponseWrapper<T> {

    /**
     * MESSAGE : 查询成功
     * SUCCESS : true
     * DATA : [{"id":27,"wechatId":100,"createTime":"2016-10-31 07:52:08","updateTime":"2016-10-31 07:52:08","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[]},{"id":28,"wechatId":100,"createTime":"2016-11-01 01:06:27","updateTime":"2016-11-01 01:06:27","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[]},{"id":29,"wechatId":100,"createTime":"2016-11-01 01:31:13","updateTime":"2016-11-01 01:31:13","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[]},{"id":30,"wechatId":100,"createTime":"2016-11-01 01:31:41","updateTime":"2016-11-01 01:31:41","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[]},{"id":32,"wechatId":100,"createTime":"2016-11-01 01:33:26","updateTime":"2016-11-01 01:33:26","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[]},{"id":52,"wechatId":100,"createTime":"2016-11-01 03:18:09","updateTime":"2016-11-01 03:18:09","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":21,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":22,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":53,"wechatId":100,"createTime":"2016-11-01 03:22:33","updateTime":"2016-11-01 03:22:33","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":23,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":24,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":55,"wechatId":100,"createTime":"2016-11-01 03:28:58","updateTime":"2016-11-01 03:28:58","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":27,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":28,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":56,"wechatId":100,"createTime":"2016-11-01 03:29:49","updateTime":"2016-11-01 03:29:49","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":29,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":30,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":57,"wechatId":100,"createTime":"2016-11-01 03:36:31","updateTime":"2016-11-01 03:36:31","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":31,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":32,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":58,"wechatId":100,"createTime":"2016-11-01 06:11:56","updateTime":"2016-11-01 06:11:56","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":33,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":34,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":60,"wechatId":100,"createTime":"2016-11-01 06:32:58","updateTime":"2016-11-01 06:32:58","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":37,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":38,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]},{"id":61,"wechatId":100,"createTime":"2016-11-01 06:35:42","updateTime":"2016-11-01 06:35:42","status":0,"remarks":"方法大幅","phone":"12345678901","address":"圣经吧","realName":"所发生的","goods":[{"id":39,"goodsId":1,"goodsName":"名称1","goodsSize":"型号1","goodsNumber":"1"},{"id":40,"goodsId":2,"goodsName":"名称2","goodsSize":"型号2","goodsNumber":"2"}]}]
     */

    private String MESSAGE;
    private boolean SUCCESS;
    private T DATA;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public boolean isSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(boolean SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public T getDATA() {
        return DATA;
    }

    public void setDATA(T DATA) {
        this.DATA = DATA;
    }
}
