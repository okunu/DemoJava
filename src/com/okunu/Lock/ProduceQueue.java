package com.okunu.Lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceQueue<T> {

    private T[] items;
    private int tail,head,count;
    
    private final Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    
    private int mMaxSize;
    
    public ProduceQueue(int maxSize){
        items = (T[]) new Object[maxSize];
        mMaxSize = maxSize;
    }
    
    public ProduceQueue(){
        this(10);
    }
    
    public int getSize(){
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
    
    public void put(T t) throws InterruptedException{
        lock.lock();
        try {
            if (count == mMaxSize) {
                System.out.println("tname = " + Thread.currentThread().getName() + "  已满， 待会再生产");
                full.await();
            }
            items[tail] = t;
            if (++tail == mMaxSize) {
                tail = 0;
            }
            count++;
            System.out.println("tname = " + Thread.currentThread().getName() + " 生产1个， 当前个数为： " + count);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public T take() throws InterruptedException{
        lock.lock();
        try{
            if (count == 0) {
                System.out.println("tname = " + Thread.currentThread().getName() + "  空了，等会再取");
                empty.await();
            }
            T r = items[head];
            items[head] = null;
            if (++head == mMaxSize) {
                head = 0;
            }
            count--;
            System.out.println("tname = " + Thread.currentThread().getName() + " 消费1个，  当前个数为： " + count);
            full.signal();
            return r;
        }finally{
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ProduceQueue<Integer> queue = new ProduceQueue<>();
        for (int i = 0; i < 10; i++) {
            int type = (i % 2 == 0 ? 1 : 0);
            TThread thread = new TThread(type, queue);
            thread.start();
        }
        while (Thread.activeCount() > 1) {
            
        }
    }
}


class TThread extends Thread{
    
    private ProduceQueue<Integer> queue;
    private int type;//0:put   1:take
    public TThread(int e, ProduceQueue<Integer> q){
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