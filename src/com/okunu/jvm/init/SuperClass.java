package com.okunu.jvm.init;

public class SuperClass {
    /*
     * 被动引用父类静态变量，不会初始化子类
     */
    static{
        System.out.println("super class init");
    }
    public static int value = 123;
}
