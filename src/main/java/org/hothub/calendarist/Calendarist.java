package org.hothub.calendarist;

import org.hothub.calendarist.core.CalendaristBase;
import org.hothub.calendarist.core.convert.CalendaristConvert;
import org.hothub.calendarist.pojo.CycleDate;
import org.hothub.calendarist.pojo.LunarDate;
import org.hothub.calendarist.pojo.SolarDate;


public class Calendarist extends CalendaristBase {


    public final static int YEAR = 1;

    public final static int MONTH = 2;

    public final static int WEEK_OF_YEAR = 3;

    public final static int WEEK_OF_MONTH = 4;

    public final static int DATE = 5;

    public final static int DAY_OF_MONTH = 5;

    public final static int DAY_OF_YEAR = 6;

    public final static int DAY_OF_WEEK = 7;

    public final static int DAY_OF_WEEK_IN_MONTH = 8;

    public final static int AM_PM = 9;

    public final static int HOUR = 10;

    public final static int HOUR_OF_DAY = 11;

    public final static int MINUTE = 12;

    public final static int SECOND = 13;

    public final static int MILLISECOND = 14;

    public final static int TIMEMILLIS = 15;

    public final static int LEAP_MONTH_OF_CURRENT = 21;

    public final static int LEAP_MONTH = 22;



    /**
     * 从阴历开始转换
     *
     * @param lunarDate 阴历日期
     * @return {@link Calendarist}
     */
    public static Calendarist fromLunar(LunarDate lunarDate) {
        return fromLunar(lunarDate.getYear(), lunarDate.getMonth(), lunarDate.getDay(), lunarDate.getHour(), lunarDate.getMinute(), lunarDate.getSecond(), lunarDate.getMillis());
    }

    public static Calendarist fromLunar(int year, int month, int day) {
        return fromLunar(year, month, day, 0, 0, 0, 0);
    }

    public static Calendarist fromLunar(int year, int month, int day, int hour, int minute, int second, int millis) {
        return fromLunar(year, month, day, hour, minute, second, millis, false, 0);
    }

    public static Calendarist fromLunar(int year, int month, int day, int hour, int minute, int second, int millis, boolean leapMonthOfCurrent, int leapMonth) {
        validate(year, month, day, hour, minute, second, millis);

        Calendarist calendarist = new Calendarist();

        calendarist.set(YEAR, year);
        calendarist.set(MONTH, month);
        calendarist.set(DATE, day);
        calendarist.set(HOUR_OF_DAY, hour);
        calendarist.set(MINUTE, minute);
        calendarist.set(SECOND, second);
        calendarist.set(MILLISECOND, millis);
        calendarist.set(LEAP_MONTH_OF_CURRENT, leapMonthOfCurrent ? 1 : 0);
        calendarist.set(LEAP_MONTH, leapMonth);


        calendarist.setFrom(2);

        return calendarist;
    }


    /**
     * 从阳历开始转换
     *
     * @param solarDate 阳历日期
     * @return {@link Calendarist}
     */
    public static Calendarist fromSolar(SolarDate solarDate) {
        return fromSolar(solarDate.getYear(), solarDate.getMonth(), solarDate.getDay(), solarDate.getHour(), solarDate.getMinute(), solarDate.getSecond(), solarDate.getMillis());
    }

    public static Calendarist fromSolar(int year, int month, int day) {
        return fromSolar(year, month, day, 0, 0, 0, 0);
    }

    public static Calendarist fromSolar(int year, int month, int day, int hour, int minute, int second, int millis) {
        validate(year, month, day, hour, minute, second, millis);

        Calendarist calendarist = new Calendarist();

        calendarist.set(YEAR, year);
        calendarist.set(MONTH, month);
        calendarist.set(DATE, day);
        calendarist.set(HOUR_OF_DAY, hour);
        calendarist.set(MINUTE, minute);
        calendarist.set(SECOND, second);
        calendarist.set(MILLISECOND, millis);

        calendarist.setFrom(1);

        return calendarist;
    }





    public LunarDate toLunar() {
        return from == 1
                ? CalendaristConvert.toLunar(toSolar().getTimestamp())
                : new LunarDate(
                    fields[Calendarist.YEAR],
                    fields[Calendarist.MONTH],
                    fields[Calendarist.DATE],
                    fields[Calendarist.HOUR_OF_DAY],
                    fields[Calendarist.MINUTE],
                    fields[Calendarist.SECOND],
                    fields[Calendarist.MILLISECOND],
                fields[Calendarist.LEAP_MONTH_OF_CURRENT] == 1,
                    fields[Calendarist.LEAP_MONTH]
                );
    }

    public SolarDate toSolar() {
        return from == 1
                ? new SolarDate(
                    fields[Calendarist.YEAR],
                    fields[Calendarist.MONTH],
                    fields[Calendarist.DATE],
                    fields[Calendarist.HOUR_OF_DAY],
                    fields[Calendarist.MINUTE],
                    fields[Calendarist.SECOND],
                    fields[Calendarist.MILLISECOND]
                )
                : CalendaristConvert.toSolar(toLunar());
    }

    public CycleDate toCycle() {
        return CalendaristConvert.toCycle(toLunar());
    }



    private static void validate(int year, int month, int day, int hour, int minute, int second, int millis) {
        if (year < 1900 || year > 2100) {
            throw new IllegalArgumentException("the param 'year' must between 1900 and 2100");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("the param 'month' must between 1 and 12");
        }

        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("the param 'day' must between 1 and 31");
        }

        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("the param 'hour' must between 0 and 23");
        }

        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("the param 'minute' must between 0 and 59");
        }

        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("the param 'second' must between 0 and 59");
        }

        if (millis < 0 || millis > 999) {
            throw new IllegalArgumentException("the param 'millis' must between 0 and 999");
        }
    }


}
