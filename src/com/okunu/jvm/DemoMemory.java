package com.okunu.jvm;

import java.util.ArrayList;
import java.util.List;

public class DemoMemory {

    static class OOMObject{
    };
    
    /*
     * 测试堆内存溢出
     * 测试参数为：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    private static void testHeapOOM(){
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
    
    private int stackLength = 1;
    public void stackLeak(){
        stackLength ++;
        stackLeak();
    }
    
    /**
     * 测试栈溢出
     * 测试参数为：-verbose:gc -Xms20M -Xmx20M -Xmn10M -Xss128K -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testStackOOM(){
        DemoMemory memory = new DemoMemory();
        memory.stackLeak();
    }
    
    /**
     * 测试运行时常量池内存溢出
     * -verbose:gc -Xms40M -Xmx40M -Xmn20M -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testConstantOOM(){
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
    
    public static void main(String[] args) {
        //testHeapOOM();
        testStackOOM();
        //testConstantOOM();
    }
}
