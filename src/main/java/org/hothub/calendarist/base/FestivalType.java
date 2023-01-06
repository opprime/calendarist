package org.hothub.calendarist.base;

/**
 * 中国传统节日
 */
public enum FestivalType {

    CHUNJIE(1, "春节"),

    YUANXIAO(2, "元宵节"),

    LONGTAITOU(3, "龙抬头"),

    SHERI(4, "社日节"),

    SHANGSI(5, "上巳节"),

    HANSHI(6, "寒食节"),

    QINGMING(7, "清明节"),

    DUNAWU(8, "端午节"),

    QIXI(9, "七夕节"),

    ZHONGYUAN(10, "中元节"),

    ZHONGQIU(11, "重阳节"),

    HANYI(12, "寒衣节"),

    DONGZHI(13, "冬至"),

    LABA(14, "腊八节"),

    XIAONIAN(15, "小年"),

    CHUXI(16, "除夕")

    ;


    private final Integer typeId;
    private final String typeName;

    FestivalType(Integer typeId, String typeName) {
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
