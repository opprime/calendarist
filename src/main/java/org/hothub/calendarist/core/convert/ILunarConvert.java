package org.hothub.calendarist.core.convert;

import org.hothub.calendarist.pojo.LunarDate;

public interface ILunarConvert {


    /**
     * 将某日期转为阴历日期
     *
     * @return {@link LunarDate}
     */
    LunarDate toLunar();


}
