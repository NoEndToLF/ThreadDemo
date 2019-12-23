package com.aice.threadtest.test2.model;

public class TestModelTwo {
    private int max;
    private int size;
    public TestModelTwo(int max) {
        this.max = max;
    }
    public void put(){
        while (true){
        synchronized (this){
            System.out.println("生产者抢锁");
            while (size==max){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
            size++;
            System.out.println("生产了一个，总数为："+size);
            notifyAll();
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        }
    }
    public void get(){
        while (true){
        synchronized (this){
            System.out.println("消费者抢锁");
            while (size==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            size--;
            System.out.println("消费了一个，总数为："+size);
            notifyAll();
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    }
}
