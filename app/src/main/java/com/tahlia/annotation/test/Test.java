package com.tahlia.annotation.test;

import com.tahlia.annotation.HelloWorld;

@TestAnnotation("123")
public class Test {
    @TestAnnotation(id = 1, value = "") int i;


    @TestAnnotation(value="test")
    public void test() {}
}