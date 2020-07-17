package org.hothub.calendarist;

import org.hothub.calendarist.core.CalendaristBase;
import org.hothub.calendarist.core.convert.CalendaristConvert;
import org.hothub.calendarist.pojo.CycleDate;
import org.hothub.calendarist.pojo.LunarDate;
import org.hothub.calendarist.pojo.SolarDate;

import java.util.Calendar;


public class Calendarist extends CalendaristBase {



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
        calendarist.set(DAY_OF_MONTH, day);
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
     * 从阳历时间戳转换
     *
     * @param timeMillis 阳历时间戳
     * @return {@link Calendarist}
     */
    public static Calendarist fromSolar(Long timeMillis) {
        if (timeMillis == null) {
            throw new IllegalArgumentException("timemillis must not be null");
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(timeMillis);

        return fromSolar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND),
                calendar.get(Calendar.MILLISECOND)
        );
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
        calendarist.set(DAY_OF_MONTH, day);
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
                    fields[Calendarist.DAY_OF_MONTH],
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
                    fields[Calendarist.DAY_OF_MONTH],
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


}
