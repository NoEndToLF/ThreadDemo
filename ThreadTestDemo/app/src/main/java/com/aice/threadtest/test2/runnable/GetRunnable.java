package com.aice.threadtest.test2.runnable;

import com.aice.threadtest.test2.TestModelBlockQueue;
import com.aice.threadtest.test2.TestModelLock;
import com.aice.threadtest.test2.model.TestModelTwo;

public class GetRunnable implements Runnable {
    private TestModelTwo testModelTwo;
    private TestModelLock testModelLock;
    private TestModelBlockQueue testModelBlockQueue;

    public GetRunnable(TestModelTwo testModelTwo) {
        this.testModelTwo = testModelTwo;
    }
    public GetRunnable(TestModelLock testModelLock) {
        this.testModelLock = testModelLock;
    }
    public GetRunnable(TestModelBlockQueue testModelBlockQueue) {
        this.testModelBlockQueue = testModelBlockQueue;
    }
    @Override
    public void run() {
        if (testModelBlockQueue!=null){
            testModelBlockQueue.get();
        }
        if (testModelLock!=null){
            testModelLock.get();
        }
        if (testModelTwo!=null) {
            testModelTwo.get();
        }
    }
}
