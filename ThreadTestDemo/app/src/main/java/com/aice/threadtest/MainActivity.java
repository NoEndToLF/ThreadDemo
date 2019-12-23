package com.aice.threadtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aice.threadtest.test1.model.TestModelOne;
import com.aice.threadtest.test1.thread.ReentrantLockRunnable;
import com.aice.threadtest.test1.thread.WaitNotifyRunnable;
import com.aice.threadtest.test2.TestModelBlockQueue;
import com.aice.threadtest.test2.TestModelLock;
import com.aice.threadtest.test2.model.TestModelTwo;
import com.aice.threadtest.test2.runnable.AddRunnable;
import com.aice.threadtest.test2.runnable.GetRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private List<Condition> conditionsList = new ArrayList<>();
    private Condition conditionA = reentrantLock.newCondition();
    private Condition conditionB = reentrantLock.newCondition();
    private Condition conditionC = reentrantLock.newCondition();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    private void methodTwo() {
        TestModelOne testModel = new TestModelOne();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            integerList.add(i);
        }
        testModel.setIntegerList(integerList);
        conditionsList.add(conditionA);
        conditionsList.add(conditionB);
        conditionsList.add(conditionC);
        for (int i = 0; i < conditionsList.size(); i++) {
            if (i < conditionsList.size() - 1) {
                new Thread(new ReentrantLockRunnable(i, conditionsList.size(), reentrantLock, conditionsList.get(i + 1), conditionsList.get(i), testModel)).start();
            } else {
                new Thread(new ReentrantLockRunnable(i, conditionsList.size(), reentrantLock, conditionsList.get(0), conditionsList.get(i), testModel)).start();
            }
        }
    }

    private void methodOne() {
        TestModelOne testModel = new TestModelOne();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            integerList.add(i);
        }
        testModel.setIntegerList(integerList);
        new Thread(new WaitNotifyRunnable(testModel)).start();
        new Thread(new WaitNotifyRunnable(testModel)).start();
        new Thread(new WaitNotifyRunnable(testModel)).start();
        new Thread(new WaitNotifyRunnable(testModel)).start();
        new Thread(new WaitNotifyRunnable(testModel)).start();
    }

    @OnClick({R.id.btn_one, R.id.btn_two,R.id.btn_three,R.id.btn_four,R.id.btn_five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                //两个线程交替输出：wait,notify
                methodOne();
                break;
            case R.id.btn_two:
                //多线程交替输出：reentrantLock，Condition
                methodTwo();
                break;
            case R.id.btn_three:
                //生产者消费者：wait,notify
                TestModelTwo testModelTwo=new TestModelTwo(5);
                AddRunnable addRunnable=new AddRunnable(testModelTwo);
                GetRunnable getRunnable=new GetRunnable(testModelTwo);
                new Thread(addRunnable).start();
                new Thread(getRunnable).start();
                break;
            case R.id.btn_four:
                TestModelLock testModelLock=new TestModelLock(5);
                AddRunnable addRunnable1=new AddRunnable(testModelLock);
                GetRunnable getRunnable1=new GetRunnable(testModelLock);
                new Thread(addRunnable1).start();
                new Thread(getRunnable1).start();
                break;
            case R.id.btn_five:
                TestModelBlockQueue testModelBlockQueue=new TestModelBlockQueue(5);
                AddRunnable addRunnable2=new AddRunnable(testModelBlockQueue);
                GetRunnable getRunnable2=new GetRunnable(testModelBlockQueue);
                new Thread(addRunnable2).start();
                new Thread(getRunnable2).start();
                break;
        }
    }
}
