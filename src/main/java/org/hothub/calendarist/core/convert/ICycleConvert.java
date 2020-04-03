package org.hothub.calendarist.core.convert;

import org.hothub.calendarist.pojo.CycleDate;

public interface ICycleConvert {


    /**
     * 将某日期转为干支日期
     *
     * @return {@link CycleDate}
     */
    CycleDate toCycle();


}
