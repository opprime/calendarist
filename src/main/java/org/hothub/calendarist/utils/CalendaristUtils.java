package org.hothub.calendarist.utils;

import org.hothub.calendarist.base.TermType;
import org.hothub.calendarist.constants.CalendaristConstants;
import org.hothub.calendarist.pojo.SolarDate;

import java.util.Calendar;

public class CalendaristUtils {


    /**
     * 获取JAVA日期处理实例
     *
     * @return Calendar
     */
    public static Calendar getCalendarInstance() {
        return Calendar.getInstance(CalendaristConstants.DEFAULT_TIME_ZONE);
    }


    /**
     * 干支日期中的年月日转化为干支
     *
     * @param lunarValue 年 OR 月 OR 日
     * @return String
     */
    public static String ganZhi(int lunarValue) {
        return CalendaristConstants.TIANGAN_INFO[lunarValue % 10] + CalendaristConstants.DIZHI_INFO[lunarValue % 12];
    }


    /**
     * 获取农历年份对应的生肖
     *
     * @param lunarYear 农历年份
     * @return String
     */
    public static String getZodiac(int lunarYear) {
        return CalendaristConstants.ZODIAC_INFO[(lunarYear - 4) % 12];
    }


    /**
     * 年份，中文
     *
     * @param year 年份
     * @return String
     */
    public static String getYearZh(int year) {
        if (year <= 0) {
            return "";
        }

        char[] years = String.valueOf(year).toCharArray();

        int length = years.length;
        if (length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (char value : years) {
            result.append(CalendaristConstants.NUMBER_ZH_INFO[(int) value - 48]);
        }

        return result.toString();
    }


    /**
     * 月份，中文
     *
     * @param month 月份
     * @return String
     */
    public static String getMonthZh(int month) {
        if (month <= 0 || month > 12) {
            return "";
        }

        return CalendaristConstants.LUNAR_MONTH_INFO[month - 1];
    }


    /**
     * 日，中文
     *
     * @param day 日
     * @return String
     */
    public static String getDayZh(int day) {
        if (day <= 0 || day > 31) {
            return "";
        }

        int lastNumber = day % 10;

        String dayZh = lastNumber == 0 ? "十" : CalendaristConstants.NUMBER_ZH_INFO[lastNumber];

        if (day <= 10) {
            dayZh = "初" + dayZh;
        } else if (day < 20) {
            dayZh = "十" + dayZh;
        } else if (day < 30) {
            dayZh = "廿" + (lastNumber == 0 ? "" : dayZh);
        } else {
            dayZh = "卅" + (lastNumber == 0 ? "" : dayZh);
        }

        return dayZh;
    }



    /**
     * 干支日期中的小时转为干支时
     *
     * @param cycleDay 干支日期的天
     * @param cycleHour 干支日期的小时
     * @return String
     */
    public static String hourGanZhi(int cycleDay, int cycleHour) {
        int index = (cycleDay % 10);

        // 五个为一周期
        index = index % 5;

        //小时对应的地支索引
        int hourOfDiZhiIndex = hourZhi(cycleHour);

        //小时对应的天干索引
        int hourOfTianGanIndex = (hourOfDiZhiIndex) + (index * 12);

        return CalendaristConstants.TIANGAN_INFO[hourOfTianGanIndex % 10] + CalendaristConstants.DIZHI_INFO[hourOfDiZhiIndex];
    }



    /**
     * 返回小时对应的支的索引
     *
     * @param lunarHour 干支日期的小时数
     * @return int
     */
    public static int hourZhi(int lunarHour) {
        if (lunarHour >= 23 || lunarHour < 1) {
            return 0;
        } else if (lunarHour < 3) {
            return 1;
        } else if (lunarHour < 5) {
            return 2;
        } else if (lunarHour < 7) {
            return 3;
        } else if (lunarHour < 9) {
            return 4;
        } else if (lunarHour < 11) {
            return 5;
        } else if (lunarHour < 13) {
            return 6;
        } else if (lunarHour < 15) {
            return 7;
        } else if (lunarHour < 17) {
            return 8;
        } else if (lunarHour < 19) {
            return 9;
        } else if (lunarHour < 21) {
            return 10;
        } else {
            return 11;
        }
    }



    /**
     * 获取某阳历年的春节当天的阳历日期
     *
     * @param solarYear 阳历年份
     * @return int
     */
    public static int chineseNewYear(Integer solarYear) {
        if (solarYear > 2100 || solarYear < 1900) {
            throw new RuntimeException("the year should between 1900 and 2100!");
        }

        return CalendaristConstants.CHINESE_NEW_YEAR[solarYear - CalendaristConstants.MIN_YEAR];
    }



    /**
     * 获取某阳历年的春节当天的阳历时间戳
     *
     * @param solarYear 年份
     * @return long
     */
    public static long chineseNewYearTimestamp(Integer solarYear) {
        if (solarYear > 2100 || solarYear < 1900) {
            throw new RuntimeException("the year should between 1900 and 2100!");
        }

        return CalendaristConstants.CHINESE_NEW_YEAR_TIMESTAMP[solarYear - CalendaristConstants.MIN_YEAR];
    }





    // WARNING: Dates before Oct. 1582 are inaccurate
    public static long solarToInt(int y, int m, int d) {
        m = (m + 9) % 12;
        y = y - m / 10;
        return 365L * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10 + (d - 1);
    }

    public static SolarDate solarFromInt(long g) {
        long year = (10000 * g + 14780) / 3652425;
        long ddd = g - (365 * year + year / 4 - year / 100 + year / 400);
        if (ddd < 0) {
            year--;
            ddd = g - (365 * year + year / 4 - year / 100 + year / 400);
        }
        long mi = (100 * ddd + 52) / 3060;
        long mm = (mi + 2) % 12 + 1;
        year = year + (mi + 2) / 12;
        long dd = ddd - (mi * 306 + 5) / 10 + 1;

        return new SolarDate((int) year, (int) mm, (int) dd);
    }



    public static int getBitInt(int data, int length, int shift) {
        return (data & (((1 << length) - 1) << shift)) >> shift;
    }




    /**
     * 农历某年的总天数
     *
     * @param lunarYear 农历年份
     * @return int
     */
    public static int daysOfYear(int lunarYear) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            if ((CalendaristConstants.LUNAR_CODE[lunarYear - CalendaristConstants.MIN_YEAR] & i) != 0) sum += 1;
        }

        return (sum + daysOfLeapMonth(lunarYear));
    }

    /**
     * 农历某年某月的总天数
     *
     * @param lunarYear 农历年份
     * @param lunarMonth 农历月份
     * @return int
     */
    public static int daysOfMonth(int lunarYear, int lunarMonth) {
        if ((CalendaristConstants.LUNAR_CODE[lunarYear - CalendaristConstants.MIN_YEAR] & (0x10000 >> lunarMonth)) == 0) {
            return 29;
        } else {
            return 30;
        }
    }

    /**
     * 农历某年闰月的天数
     *
     * @param lunarYear 农历年份
     * @return int
     */
    public static int daysOfLeapMonth(int lunarYear) {
        if (leapMonth(lunarYear) != 0) {
            if ((CalendaristConstants.LUNAR_CODE[lunarYear - CalendaristConstants.MIN_YEAR] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    /**
     * 农历某年闰哪个月
     * 1-12, 无闰月返回0
     *
     * @param lunarYear 农历年份
     * @return int
     */
    public static int leapMonth(int lunarYear) {
        return CalendaristConstants.LUNAR_CODE[lunarYear - CalendaristConstants.MIN_YEAR] & 0xf;
    }



    /**
     * 获取某节气，在某一阳历年，某月份的哪一天
     *
     * @param solarYear 阳历年
     * @param termType 节气
     * @return int
     */
    public static int getTermDay(int solarYear, TermType termType) {
        if (solarYear < 1900 || solarYear > 2100) {
            throw new IllegalArgumentException("the argument 'solarYear' must between 1900 and 2100");
        }

        if (termType == null) {
            throw new IllegalArgumentException("the argument 'termType' must not be null");
        }

        double ratio = (solarYear <= 2000 ? CalendaristConstants.SOLAR_TERM_INFO_20TH : CalendaristConstants.SOLAR_TERM_INFO_21TH)[termType.ordinal()];

        //年，取后两位
        int year = solarYear % 100;

        //先判断是否是润年
        boolean isLeapYear = (solarYear % 4 == 0 && solarYear % 100 != 0) || solarYear % 400 == 0;

        //闰年数
        //凡闰年，3月1日前的节气，闰年数要减一，即：L=(Y-1)/4，因为小寒、大寒、立春、雨水这四个节气都在3月1日这前
        int leapCount = isLeapYear && termType.ordinal() <= 3 ? (year - 1) / 4 : year /4;

        //Y=年代数的后2位、D=0.2422、L=闰年数、C取决于节气和年份。
        //寿星通用公式  num =[Y * D + C] - L
        int day = (int) (year * CalendaristConstants.SOLAR_TERM_RATIO + ratio - leapCount);

        //寿星公式，特殊情况处理。https://www.jianshu.com/p/1f814c6bb475
        if (termType == TermType.YUSHUI && solarYear == 2026) {
            day--;
        } else if (termType == TermType.LIXIA && solarYear == 1911) {
            day++;
        } else if (termType == TermType.XIAOMAN && solarYear == 2008) {
            day++;
        } else if (termType == TermType.MANGZHONG && solarYear == 1902) {
            day++;
        } else if (termType == TermType.XIAOSHU && (solarYear == 1925 || solarYear == 2016)) {
            day++;
        } else if (termType == TermType.DASHU && solarYear == 1922) {
            day++;
        } else if (termType == TermType.LIQIU && solarYear == 2002) {
            day++;
        } else if (termType == TermType.QIUFEN && solarYear == 1942) {
            day++;
        } else if (termType == TermType.SHUANGJIANG && solarYear == 2089) {
            day++;
        } else if (termType == TermType.LIDONG && solarYear == 2089) {
            day++;
        } else if (termType == TermType.XIAOXUE && solarYear == 1978) {
            day++;
        } else if (termType == TermType.DONGZHI && solarYear == 2021) {
            day--;
        } else if (termType == TermType.XIAOHAN && solarYear == 1982) {
            day++;
        } else if (termType == TermType.XIAOHAN && solarYear == 2019) {
            day--;
        } else if (termType == TermType.DAHAN && (solarYear == 2000 || solarYear == 2082)) {
            day++;
        }

        return day;
    }



    /**
     * 获取某年、某月，第一个节气在哪一天
     *
     * @param solarYear 阳历年份
     * @param solarMonth 阳历月份
     * @return int
     */
    public static int getFirstTerm(int solarYear, int solarMonth) {
        if (solarYear < 1900 || solarYear > 2100) {
            throw new IllegalArgumentException("the argument 'solarYear' must between 1900 and 2100");
        }

        if (solarMonth < 1 || solarMonth > 12) {
            throw new IllegalArgumentException("the argument 'solarMonth' must between 1 and 12");
        }

        //该月第一个节气对应下标
        int termIndex = (solarMonth - 1) * 2;

        return getTermDay(solarYear, TermType.getType(termIndex));
    }



    /**
     * 根据阳历年份和节气，反查该节气对应的阳历日期
     *
     * @param solarYear 阳历年份
     * @param termType 节气 {@link TermType}
     * @return Long 毫秒时间戳
     */
    public static Long getTermTime(int solarYear, TermType termType) {
        if (solarYear < 1900 || solarYear > 2100) {
            throw new IllegalArgumentException("the argument 'solarYear' must between 1900 and 2100");
        }

        if (termType == null) {
            throw new IllegalArgumentException("the argument 'termType' must not be null");
        }

        int day = getTermDay(solarYear, termType);

        Calendar calendar = CalendaristUtils.getCalendarInstance();
        calendar.set(Calendar.YEAR, solarYear);
        calendar.set(Calendar.MONTH, termType.ordinal() / 2);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        //取当天0时0分0秒的时间戳
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

}
