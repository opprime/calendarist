package org.hothub.calendarist.pojo;

import org.hothub.calendarist.base.TermType;
import org.hothub.calendarist.core.feature.ITermFeature;
import org.hothub.calendarist.utils.CalendaristUtils;

import java.time.DayOfWeek;
import java.util.Calendar;

/**
 * 阳历日期
 */
public class SolarDate extends CalendaristDate implements ITermFeature {

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
        if (this.year < 1900 || this.year > 2100) {
            throw new IllegalArgumentException("the property 'year' must between 1900 and 2100");
        }

        if (this.month < 1 || this.month > 12) {
            throw new IllegalArgumentException("the property 'month' must between 1 and 12");
        }

        //每个月肯定只有两个节气，且两个节气必定在15号之前和之后
        //所以，离某日期最近的节气的下标如下
        int termIndex = (this.month * 2) - (this.day > 15 ? 1 : 2);

        //该节气在具体哪一天
        int termDay = CalendaristUtils.getTermDay(this.year, TermType.getType(termIndex));

        return termDay == this.day ? TermType.getType(termIndex) : null;
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
