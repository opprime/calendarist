package org.hothub.calendarist.pojo;

import org.hothub.calendarist.base.TermType;
import org.hothub.calendarist.constants.CalendaristConstants;
import org.hothub.calendarist.core.feature.ISolarTermFeature;
import org.hothub.calendarist.utils.CalendaristUtils;

import java.time.DayOfWeek;
import java.util.Calendar;

/**
 * 阳历日期
 */
public class SolarDate extends CalendaristDate implements ISolarTermFeature {

    /**
     * 星期
     */
    private DayOfWeek dayOfWeek;


    public SolarDate() {
    }

    public SolarDate(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public SolarDate(int year, int month, int day, int hour, int minute, int second, int millis) {
        super(year, month, day, hour, minute, second, millis);

        Calendar calendar = CalendaristUtils.getCalendarInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, millis);

        this.timestamp = calendar.getTimeInMillis();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        this.dayOfWeek = DayOfWeek.of(dayOfWeek == 1 ? 7 : dayOfWeek - 1);
    }

    /**
     * 获取该日期对应的节气，无则返回null
     *
     * @return {@link TermType}
     */
    @Override
    public TermType getTerm() {
        //每个月肯定只有两个节气，且两个节气必定在15号之前和之后
        //所以，离某日期最近的节气的下标如下
        int termIndex = (this.month * 2) - (this.day > 15 ? 1 : 2);
        if (termIndex < 0 || termIndex > 23) {
            return null;
        }

        long millis = ((long) (31556925974.7 * (this.year - 1900))) + (CalendaristConstants.SOLAR_TERM_INFO[termIndex] * 60000L);

        Calendar calendar = CalendaristUtils.getCalendarInstance();
        calendar.setTimeInMillis(millis + CalendaristConstants.SOLAR_TERM_BASE_TIMESTAMP);

        return calendar.get(Calendar.DAY_OF_MONTH) == this.day ? TermType.getType(termIndex) : null;
    }



    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }



    @Override
    public String toString() {
        return "SolarDate{" +
                "dayOfWeek=" + dayOfWeek +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", millis=" + millis +
                ", timestamp=" + timestamp +
                '}';
    }
}
