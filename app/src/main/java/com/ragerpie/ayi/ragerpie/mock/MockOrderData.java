package com.ragerpie.ayi.ragerpie.mock;

/**
 * Created by WangBo on 2016/10/28.
 */

public class MockOrderData implements MockData {
    @Override
    public String getJson() {
        return "[{\"type\":1},{\"type\":1},{\"type\":1},{\"type\":1},{\"type\":1},{\"type\":1},{\"type\":1}," +
                "{\"type\":2},{\"type\":2},{\"type\":2},{\"type\":2},{\"type\":2},{\"type\":2},{\"type\":2}," +
                "{\"type\":3},{\"type\":3},{\"type\":3},{\"type\":3},{\"type\":3},{\"type\":3},{\"type\":3},{\"type\":3}]";
    }
}
