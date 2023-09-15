package org.hothub.calendarist.base;

/**
 * 24节气
 * 每个自然年第一个节气是小寒，所以下标从小寒开始算
 */
public enum TermType {

    XIAOHAN(23, "小寒"),
    DAHAN(24, "大寒"),

    LICHUN(1, "立春"),
    YUSHUI(2, "雨水"),
    JINGZHE(3, "惊蛰"),
    CHUNFEN(4, "春分"),

    QINGMING(5, "清明"),
    GUYU(6, "谷雨"),
    LIXIA(7, "立夏"),
    XIAOMAN(8, "小满"),

    MANGZHONG(9, "芒种"),
    XIAZHI(10, "夏至"),
    XIAOSHU(11, "小暑"),
    DASHU(12, "大暑"),

    LIQIU(13, "立秋"),
    CHUSHU(14, "处暑"),
    BAILU(15, "白露"),
    QIUFEN(16, "秋分"),

    HANLU(17, "寒露"),
    SHUANGJIANG(18, "霜降"),
    LIDONG(19, "立冬"),
    XIAOXUE(20, "小雪"),

    DAXUE(21, "大雪"),
    DONGZHI(22, "冬至")


    ;



    private final Integer termId;

    private final String termName;


    TermType(Integer termId, String termName) {
        this.termId = termId;
        this.termName = termName;
    }



    public Integer getTermId() {
        return termId;
    }

    public String getTermName() {
        return termName;
    }



    /**
     * 根据节气下标，取得对应的枚举类型
     * 下标从0开始。值为0-23
     */
    public static TermType getType(Integer termIndex) {
        if (termIndex == null || termIndex < 0 || termIndex > 23) {
            return null;
        }

        for (TermType item : values()) {
            if (item.ordinal() == termIndex) {
                return item;
            }
        }

        return null;
    }

}
