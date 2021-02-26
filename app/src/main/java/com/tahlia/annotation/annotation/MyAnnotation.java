package com.tahlia.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public @interface MyAnnotation {
    int id() default 0;       // 需要传入一个类型为int，名字为id的参数。
    String value(); // 需要传入一个类型为String，名字为value的参数。
}
