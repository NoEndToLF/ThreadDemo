package com.aice.threadtest.test2.runnable;


import com.aice.threadtest.test1.model.TestModelOne;
import com.aice.threadtest.test2.TestModelBlockQueue;
import com.aice.threadtest.test2.TestModelLock;
import com.aice.threadtest.test2.model.TestModelTwo;

public class AddRunnable implements Runnable {
    private TestModelTwo testModelTwo;
    private TestModelLock testModelLock;
    private TestModelBlockQueue testModelBlockQueue;
    public AddRunnable(TestModelTwo testModelTwo) {
        this.testModelTwo = testModelTwo;
    }
    public AddRunnable(TestModelLock testModelLock) {
        this.testModelLock = testModelLock;
    }
    public AddRunnable(TestModelBlockQueue testModelBlockQueue) {
        this.testModelBlockQueue = testModelBlockQueue;
    }
    @Override
    public void run() {
        if (testModelBlockQueue!=null){
            testModelBlockQueue.put();
        }
        if (testModelLock!=null){
            testModelLock.put();
        }
        if (testModelTwo!=null) {
            testModelTwo.put();
        }
    }
}
