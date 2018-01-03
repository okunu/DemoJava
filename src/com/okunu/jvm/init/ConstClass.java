package com.okunu.jvm.init;

public class ConstClass {
    static{
        System.out.println("const class init");
    }
    public static final String HELLO = "hello world";
}
