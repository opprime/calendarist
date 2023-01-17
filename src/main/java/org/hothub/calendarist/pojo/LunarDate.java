package org.hothub.calendarist.pojo;

import org.hothub.calendarist.utils.CalendaristUtils;

/**
 * 阴历日期
 */
public class LunarDate extends CalendaristDate {

    //年，中文
    private String yearZh;

    //月，中文
    private String monthZh;

    //日，中文
    private String dayZh;

    //该年的闰月月份
    private int leapMonth;

    //当前月是否是闰月
    @Deprecated
    private boolean itsLeapMonth;

    //当前月是否是闰月
    private boolean leap;


    public LunarDate() {
    }

    public LunarDate(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public LunarDate(int year, int month, int day, int hour, int minute, int second, int millis) {
        this(year, month, day, hour, minute, second, millis, false);
    }

    public LunarDate(int year, int month, int day, int hour, int minute, int second, int millis, boolean isLeap) {
        super(year, month, day, hour, minute, second, millis);

        //获取该年真实的闰月月份
        this.leapMonth = CalendaristUtils.leapMonth(year);

        //如果设置了当前月是闰月，则要验证真实性
        this.itsLeapMonth = isLeap && this.leapMonth != 0 && this.leapMonth == month;
        this.leap = this.itsLeapMonth;

        //年月日，中文
        this.yearZh = CalendaristUtils.getYearZh(year);
        this.monthZh = CalendaristUtils.getMonthZh(month);
        this.dayZh = CalendaristUtils.getDayZh(day);
    }


    public int getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(int leapMonth) {
        this.leapMonth = leapMonth;
    }

    /**
     * 已废弃，请使用isLeap()方法替代。
     *
     * @return boolean
     */
    @Deprecated
    public boolean isItsLeapMonth() {
        return itsLeapMonth;
    }

    @Deprecated
    public void setItsLeapMonth(boolean itsLeapMonth) {
        this.itsLeapMonth = itsLeapMonth;
    }

    public String getYearZh() {
        return yearZh;
    }

    public void setYearZh(String yearZh) {
        this.yearZh = yearZh;
    }

    public String getMonthZh() {
        return monthZh;
    }

    public void setMonthZh(String monthZh) {
        this.monthZh = monthZh;
    }

    public String getDayZh() {
        return dayZh;
    }

    public void setDayZh(String dayZh) {
        this.dayZh = dayZh;
    }

    public boolean isLeap() {
        return leap;
    }

    public void setLeap(boolean leap) {
        this.leap = leap;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LunarDate{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", hour=").append(hour);
        sb.append(", minute=").append(minute);
        sb.append(", second=").append(second);
        sb.append(", millis=").append(millis);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", yearZh=").append(yearZh);
        sb.append(", monthZh=").append(monthZh);
        sb.append(", dayZh=").append(dayZh);
        sb.append(", leapMonth=").append(leapMonth);
        sb.append(", leap=").append(leap);
        sb.append('}');
        return sb.toString();
    }
}
