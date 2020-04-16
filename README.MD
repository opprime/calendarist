# 日期计算家，可实现阳历、阴历、干支历的相互转换


![maven](https://img.shields.io/maven-central/v/org.hothub/calendarist.svg)
![license](https://img.shields.io/github/license/opprime/calendarist.svg)


## 使用方法
>Maven引用
```
<dependency>
    <groupId>org.hothub</groupId>
    <artifactId>calendarist</artifactId>
    <version>1.0.3</version>
</dependency>
```

## 功能介绍
1. 支持1900 - 2100年份的日期转换
2. 支持阳历日期转阴历日期
3. 支持阴历日期转阳历日期
4. 支持阳历日期转干支日期
5. 支持阴历日期转干支日期

## 参数/字段说明
| 字段                | 说明           | 类型  | 取值范围 | 适用    |
| --------------------- | ---------------- | ------- | ------------ | --------- |
| YEAR                  | 年              | int     | 1900 - 2100  | 阳历&阴历 |
| MONTH                 | 月              | int     | 1 - 12       | 阳历&阴历 |
| DAY_OF_MONTH          | 日              | int     | 1 - 31       | 阳历&阴历 |
| HOUR_OF_DAY           | 时              | int     | 0 - 23       | 阳历&阴历 |
| MINUTE                | 分              | int     | 0 - 59       | 阳历&阴历 |
| SECOND                | 秒              | int     | 0 - 59       | 阳历&阴历 |
| MILLISECOND           | 毫秒           | int     | 0 - 999      | 阳历&阴历 |
| LEAP_MONTH_OF_CURRENT | 指定月是否是闰月 | boolean | true / false | 阴历    |
| LEAP_MONTH            | 指定年的闰月月份 | int     | 0 - 12       | 阴历    |

## 代码示例：

### 阳历转阴历、干支历：
```
//设置要转换的阳历日期
Calendarist calendarist = Calendarist.fromSolar(2020, 1, 1, 12, 15, 55, 58);

//转阴历
LunarDate lunarDate = calendarist.toLunar();
//转阳历
SolarDate solarDate = calendarist.toSolar();
//转干支历
CycleDate cycleDate = calendarist.toCycle();
```

### 阴历转阳历、干支历：
```
//设置要转换的阴历日期
Calendarist calendarist = Calendarist.fromLunar(2020, 1, 1, 12, 15, 55, 580, false, 4);

//转阴历
LunarDate lunarDate = calendarist.toLunar();
//转阳历
SolarDate solarDate = calendarist.toSolar();
//转干支历
CycleDate cycleDate = calendarist.toCycle();
```

## 其他用法：

### 阳历转阴历、干支历：
```
//设置要转换的阳历日期
Calendarist calendarist = Calendarist.fromSolar(2020, 1, 1);
calendarist.set(Calendarist.HOUR, 12);
calendarist.set(Calendarist.MINUTE, 15);
calendarist.set(Calendarist.SECOND, 55);
calendarist.set(Calendarist.MILLISECOND, 58);

//转阴历
LunarDate lunarDate = calendarist.toLunar();
//转阳历
SolarDate solarDate = calendarist.toSolar();
//转干支历
CycleDate cycleDate = calendarist.toCycle();
```

### 阴历转阳历、干支历：
```
//设置要转换的阴历日期
Calendarist calendarist = Calendarist.fromLunar(2020, 1, 1, 12, 15, 55, 58);
calendarist.set(Calendarist.HOUR, 12);
calendarist.set(Calendarist.MINUTE, 15);
calendarist.set(Calendarist.SECOND, 55);
calendarist.set(Calendarist.MILLISECOND, 58);
//从阴历转换时，要指定当前月是否是闰月（0：当前月不是闰月，1：当前月是闰月），默认为0
calendarist.set(Calendarist.LEAP_MONTH_OF_CURRENT, 1)
//从阴历转换时，要指定本年的闰月月份，为0时表示本年无闰月，默认为0
calendarist.set(Calendarist.LEAP_MONTH, 4);

//转阴历
LunarDate lunarDate = calendarist.toLunar();
//转阳历
SolarDate solarDate = calendarist.toSolar();
//转干支历
CycleDate cycleDate = calendarist.toCycle();
```
