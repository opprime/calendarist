package org.hothub.calendarist.pojo;

import org.hothub.calendarist.utils.CalendaristUtils;

/**
 * 阴历日期
 */
public class LunarDate extends CalendaristDate {

    //当前月是否是闰月
    private boolean leapMonthOfCurrent;

    //该年的闰月月份
    private int leapMonth;


    public LunarDate() {
    }

    public LunarDate(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public LunarDate(int year, int month, int day, int hour, int minute, int second, int millis) {
        this(year, month, day, hour, minute, second, millis, false, CalendaristUtils.leapMonth(year));
    }

    public LunarDate(int year, int month, int day, int hour, int minute, int second, int millis, boolean leapMonthOfCurrent, int leapMonth) {
        super(year, month, day, hour, minute, second, millis);
        this.leapMonthOfCurrent = leapMonthOfCurrent;
        this.leapMonth = leapMonth;
    }


    public boolean isLeapMonthOfCurrent() {
        return leapMonthOfCurrent;
    }

    public void setLeapMonthOfCurrent(boolean leapMonthOfCurrent) {
        this.leapMonthOfCurrent = leapMonthOfCurrent;
    }

    public int getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(int leapMonth) {
        this.leapMonth = leapMonth;
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
        sb.append(", leapMonthOfCurrent=").append(leapMonthOfCurrent);
        sb.append(", leapMonth=").append(leapMonth);
        sb.append('}');
        return sb.toString();
    }
}
