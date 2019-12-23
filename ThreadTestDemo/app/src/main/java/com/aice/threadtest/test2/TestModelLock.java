package com.aice.threadtest.test2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestModelLock {
    private int max;
    private int size;
    private ReentrantLock reentrantLock;
    private Condition put;
    private Condition get;
    public TestModelLock(int max) {
        this.max = max;
        reentrantLock=new ReentrantLock();
        put=reentrantLock.newCondition();
        get=reentrantLock.newCondition();
    }
    public void put(){
        while (true){
        reentrantLock.lock();
            while (size==max) {
                try {
                    get.signal();
                    put.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            size++;
            System.out.println("生产了一个，总数为："+size);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
    }
    public void get(){
        while (true){
        reentrantLock.lock();
            while (size==0){
                try {
                    put.signal();
                    get.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            size--;
            System.out.println("消费了一个，总数为："+size);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }

        }
    }
}
