package com.ragerpie.ayi.ragerpie.event;

/**
 * Created by WangBo on 2016/11/11.
 */

public class DatePickedEvent {
    public int year;
    public int monthOfYear;
    public int dayOfMonth;

    public DatePickedEvent(int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
    }
}
