package org.hothub.calendarist.core.feature;

import org.hothub.calendarist.base.FestivalType;
import org.hothub.calendarist.base.TermType;

import java.util.List;

public interface IFestivalFeature {

    /**
     * 获取某日期对应的节日
     * 若该日期无对应节日，则返回null
     *
     * @return {@link TermType}
     */
    List<FestivalType> getFestival();

}
