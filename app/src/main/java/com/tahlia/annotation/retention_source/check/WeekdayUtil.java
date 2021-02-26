package com.tahlia.annotation.retention_source.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import androidx.annotation.IntDef;

public class WeekdayUtil {

//    // 1.枚举方法
//    private WEEKDAY weekDayFromEnum;
//
//    public enum WEEKDAY {
//        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
//    }
//
//    public void setDay(WEEKDAY weekday) {
//        weekDayFromEnum = weekday;
//    }
//

    // 2. 静态常量方法
//    private int weekDayFromStaticConstant;
//
//    public static final int MONDAY = 1;
//    public static final int TUESDAY = 2;
//    public static final int WEDNESDAY = 3;
//    public static final int THURSDAY = 4;
//    public static final int FRIDAY = 5;
//    public static final int SATURDAY = 6;
//    public static final int SUNDAY = 7;
////
//    public static final int MAN = 1;
//    public static final int WOMAN = 2;
//
//    public void setDay(int weekday) {
//        weekDayFromStaticConstant = weekday;
//    }

//    // 3.注解
    private @WEEKDAY int weekDayFromAnnotation;

    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final int SUNDAY = 7;

    public static final int MAN = 1;
    public static final int WOMAN = 2;

    /**
     * IntDef限制：用WEEKDAY注解修饰的元素，仅可传入IntDef中声明的值（类型为int）。
     * @Target 声明WEEKDAY注解可以修饰的元素：成员变量和形参
     * @Rentention 声明WEEKDAY注解仅需要保留在源码级别即可
     */
    @IntDef({MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WEEKDAY{
    }

    public void setDay(@WEEKDAY int weekday) {
        this.weekDayFromAnnotation = weekday;
    }

}
