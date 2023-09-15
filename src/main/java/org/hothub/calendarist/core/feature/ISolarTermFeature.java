package org.hothub.calendarist.core.feature;

import org.hothub.calendarist.base.TermType;

public interface ISolarTermFeature {

    /**
     * 获取某日期对应的节气
     * 若该日期无对应节气，则返回null
     *
     * @return {@link TermType}
     */
    TermType getTerm();

}
