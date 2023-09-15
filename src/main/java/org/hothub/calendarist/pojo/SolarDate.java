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

        double ratio = (this.year <= 2000 ? CalendaristConstants.SOLAR_TERM_INFO_20TH : CalendaristConstants.SOLAR_TERM_INFO_21TH)[termIndex];

        //年，取后两位
        int year = this.year % 100;
        int L = year / 4;
        if (this.year % 4 == 0 && this.year % 100 != 0 || this.year % 400 == 0) {
            // 注意：凡闰年3月1日前闰年数要减一，即：L=[(Y-1)/4],因为小寒、大寒、立春、雨水这两个节气都小于3月1日
            if (ratio == 5.4055 || ratio == 3.87) {
                L = (year - 1) / 4;
            }
        }

        //寿星通用公式  num =[Y * D + C] - L
        int num1 = (int) ((year * CalendaristConstants.SOLAR_TERM_RATIO + ratio) - L);

        return num1 == this.day ? TermType.getType(termIndex) : null;
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
