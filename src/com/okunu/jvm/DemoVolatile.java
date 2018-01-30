package com.okunu.jvm;

public class DemoVolatile {

    public static volatile int race = 0;
    public static void increase(){
        race++;
    }
    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(){
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }
            };
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
