package org.hothub.calendarist.core;


public abstract class CalendaristBase {


    /**
     * 从阳历转换 OR 从阴历转换
     * 1: 阳历
     * 2: 阴历
     */
    protected int from;

    protected int[] fields = new int[25];


    public void set(int field, int value) {
        fields[field] = value;
    }


    public void setFrom(int from) {
        this.from = from;
    }
}
