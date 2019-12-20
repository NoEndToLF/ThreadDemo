package com.aice.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.aice.threadtest.test1.model.TestModel;
import com.aice.threadtest.test1.thread.ReentrantLockRunnable;
import com.aice.threadtest.test1.thread.WaitNotifyRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {
private ReentrantLock reentrantLock=new ReentrantLock();
private List<Condition> conditionsList=new ArrayList<>();
private Condition conditionA=reentrantLock.newCondition();
private Condition conditionB=reentrantLock.newCondition();
private Condition conditionC=reentrantLock.newCondition();
private Condition conditionD=reentrantLock.newCondition();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestModel testModel=new TestModel();
        List<Integer> integerList=new ArrayList<>();
        for (int i=0;i<30;i++){
            integerList.add(i);
        }
        testModel.setIntegerList(integerList);
        conditionsList.add(conditionA);
        conditionsList.add(conditionB);
        conditionsList.add(conditionC);
        conditionsList.add(conditionD);
        for (int i=0;i<conditionsList.size();i++){
            if (i<conditionsList.size()-1){
            new Thread(new ReentrantLockRunnable(i,conditionsList.size(),reentrantLock,conditionsList.get(i+1),conditionsList.get(i),testModel)).start();
            }else {
                new Thread(new ReentrantLockRunnable(i,conditionsList.size(),reentrantLock,conditionsList.get(0),conditionsList.get(i),testModel)).start();
            }
        }
//        new Thread(new WaitNotifyRunnable(testModel)).start();
//        new Thread(new WaitNotifyRunnable(testModel)).start();

    }
}
