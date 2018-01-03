package com.okunu.jvm.init;

public class NotInit {

    /*
     * 被动引用父类静态变量，不会初始化子类
     */
    public static void invokeSuperStatic(){
        System.out.println(SubClass.value);
    }
    
    /*
     * 通过数组定义来引用类，不会触发类的初始化
     */
    public static void accessByArray(){
        SuperClass[] array = new SuperClass[10];
    }
    
    /*
     * 访问final static变量，不会初始化类
     */
    public static void accessFinalField(){
//        System.out.println(ConstClass.HELLO);
    }
    
    public static void main(String[] args) {
        //invokeSuperStatic();
        //accessByArray();
        accessByArray();
    }
}
