package com.aice.threadtest.test1.thread;

import android.util.Log;

import com.aice.threadtest.test1.model.TestModel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockRunnable implements Runnable{
    private Condition conditionNext;
    private Condition conditionSelf;
    private ReentrantLock reentrantLock;
    private TestModel testModel;
    private int conditionLength;
    private int index;
    public ReentrantLockRunnable(int index,int conditionLength,ReentrantLock reentrantLock,Condition conditionNext, Condition conditionSelf, TestModel testModel) {
        this.reentrantLock=reentrantLock;
        this.conditionNext = conditionNext;
        this.conditionSelf = conditionSelf;
        this.testModel = testModel;
        this.conditionLength=conditionLength;
        this.index=index;
    }

    @Override
    public void run() {
        while (testModel.getNum()<testModel.getIntegerList().size()){
            Log.v(Thread.currentThread().getName()+"==",""+testModel.getNum());
            reentrantLock.lock();
            try {
            while (testModel.getNum()%conditionLength!=index){
                conditionNext.signal();
                conditionSelf.await();
            }
            Log.v(Thread.currentThread().getName()+"=",testModel.getIntegerList().get(testModel.getNum())+"");
            testModel.setNum(testModel.getNum()+1);
                conditionNext.signal();
                conditionSelf.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }

    }
}
