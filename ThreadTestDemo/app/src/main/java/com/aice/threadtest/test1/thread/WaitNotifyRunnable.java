package com.aice.threadtest.test1.thread;

import android.util.Log;

import com.aice.threadtest.test1.model.TestModelOne;

public class WaitNotifyRunnable implements Runnable{
    private TestModelOne testModel;

    public WaitNotifyRunnable(TestModelOne testModel) {
        this.testModel = testModel;
    }

    @Override
    public void run() {

        while (testModel.getNum()<testModel.getIntegerList().size()){
            Log.v("xixi=",Thread.currentThread().getName());
            synchronized (testModel){
                testModel.notify();
                if (testModel.getNum()<testModel.getIntegerList().size()){
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
}
