package com.ragerpie.ayi.ragerpie.config;

import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;

/**
 * Created by WangBo on 2016/11/2.
 */

public class Constants {
    public static final String RETROFIT_CACHE_FILE = "cache";

    public static final String START_TIME_PREFIX = "000000";
    public static final String END_TIME_PREFIX = "235959";

    /**
     * 底部订单状态说明
     */
    public static final String resolveStatusStr(int stat) {
        switch (stat) {
            case OrderBean.STATE_CREATE:
                return "新增订单";
            case OrderBean.STATE_SENT:
                return "已发送订单";
            case OrderBean.STATE_DEAL:
                return "已处理订单(完成)";
            case OrderBean.STATE_DELETE:
                return "删除订单";
            case OrderBean.STATE_NOUSED:
                return "无效订单";
            default:
                return "未知状态";
        }
    }

    public static final String EXTRA_GOTO_TODAY_ORDER = "today_order";
}
