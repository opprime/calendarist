package org.hothub.calendarist.base;

/**
 * 节气类型
 */
public enum CalendaristTermsType {


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
    DONGZHI(22, "冬至"),
    XIAOHAN(23, "小寒"),
    DAHAN(24, "大寒");



    private final Integer typeId;

    private final String typeName;


    CalendaristTermsType(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }


    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }




}
