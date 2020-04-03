package org.hothub.calendarist.core.convert;

import org.hothub.calendarist.pojo.SolarDate;

public interface ISolarConvert {


    /**
     * 将某日期转为阳历日期
     *
     * @return {@link SolarDate}
     */
    SolarDate toSolar();



}
