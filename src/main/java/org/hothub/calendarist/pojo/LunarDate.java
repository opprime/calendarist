package org.hothub.calendarist.pojo;

/**
 * 阴历日期
 */
public class LunarDate extends CalendaristDate {

    //该月是否是闰月
    private boolean leap;

    //该年的闰月月份
    private int leapMonth;


    public LunarDate() {
    }

    public LunarDate(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public LunarDate(int year, int month, int day, int hour, int minute, int second, int millis) {
        super(year, month, day, hour, minute, second, millis);
    }



    public boolean isLeap() {
        return leap;
    }

    public void setLeap(boolean leap) {
        this.leap = leap;
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
        sb.append(", leap=").append(leap);
        sb.append(", leapMonth=").append(leapMonth);
        sb.append('}');
        return sb.toString();
    }
}
