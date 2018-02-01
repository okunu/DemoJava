package com.okunu.Lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncProduceQueue<T> {

    private T[] items;
    private int tail,head,count;
    
    private Object lock = new Object();
    
    private int mMaxSize;
    
    public SyncProduceQueue(int maxSize){
        items = (T[]) new Object[maxSize];
        mMaxSize = maxSize;
    }
    
    public SyncProduceQueue(){
        this(10);
    }
    
    public void put(T t) throws InterruptedException{
        synchronized (lock) {
            if (count == mMaxSize) {
                System.out.println("tname = " + Thread.currentThread().getName() + "  已满， 待会再生产");
                lock.wait();
            }
            items[tail] = t;
            if (++tail == mMaxSize) {
                tail = 0;
            }
            count++;
            System.out.println("tname = " + Thread.currentThread().getName() + " 生产1个， 当前个数为： " + count);
        }
    }
    
    public T take() throws InterruptedException{
        synchronized (lock) {
            if (count == 0) {
                System.out.println("tname = " + Thread.currentThread().getName() + "  空了，等会再取");
                lock.wait();
            }
            T r = items[head];
            items[head] = null;
            if (++head == mMaxSize) {
                head = 0;
            }
            count--;
            System.out.println("tname = " + Thread.currentThread().getName() + " 消费1个，  当前个数为： " + count);
            return r;
        }
    }
    
    public static void main(String[] args) {
        SyncProduceQueue<Integer> queue = new SyncProduceQueue<>();
        for (int i = 0; i < 10; i++) {
            int type = (i % 2 == 0 ? 1 : 0);
            SyncThread thread = new SyncThread(type, queue);
            thread.start();
        }
        while (Thread.activeCount() > 1) {
            
        }
    }
}


class SyncThread extends Thread{
    
    private SyncProduceQueue<Integer> queue;
    private int type;//0:put   1:take
    public SyncThread(int e, SyncProduceQueue<Integer> q){
        queue = q;
        type = e;
    }
    
    @Override
    public void run() {
        try {
            if (type == 1) {
                queue.take();
            }else {
                queue.put(new Random().nextInt(100));
            }
        } catch (Exception e) {
        }
    }
}