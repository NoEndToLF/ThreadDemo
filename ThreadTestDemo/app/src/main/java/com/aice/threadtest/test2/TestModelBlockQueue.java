package com.aice.threadtest.test2;

import java.util.concurrent.LinkedBlockingQueue;

public class TestModelBlockQueue {
    private int max;
    private LinkedBlockingQueue<Object> linkedBlockingQueue;
    private int size;
    public TestModelBlockQueue(int max) {
        this.max = max;
        linkedBlockingQueue=new LinkedBlockingQueue(max);
    }
    public void put(){
        while (true){
                try {
                    linkedBlockingQueue.put(new Object());
                    System.out.println("生产了一个，总数为："+linkedBlockingQueue.size());
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
    public void get() {
        while (true) {

            try {
                linkedBlockingQueue.take();
                System.out.println("消费了一个，总数为：" + linkedBlockingQueue.size());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
