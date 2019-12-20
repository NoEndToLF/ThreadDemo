package com.aice.threadtest.test1.thread;

import android.util.Log;

import com.aice.threadtest.test1.model.TestModel;

public class WaitNotifyRunnable implements Runnable{
    private TestModel testModel;

    public WaitNotifyRunnable(TestModel testModel) {
        this.testModel = testModel;
    }

    @Override
    public void run() {
        while (testModel.getNum()<testModel.getIntegerList().size()){
            Log.v("xixi=",Thread.currentThread().getName());
            synchronized (testModel){
                testModel.notify();
                Log.v(Thread.currentThread().getName()+"=",testModel.getIntegerList().get(testModel.getNum())+"");
                testModel.setNum(testModel.getNum()+1);
                try {
                    testModel.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
