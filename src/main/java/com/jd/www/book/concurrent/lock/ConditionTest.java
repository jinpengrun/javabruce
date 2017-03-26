package com.jd.www.book.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午9:55</li>
 * <li>function:</li>
 * </ul>
 */
public class ConditionTest {
    Lock lock = new ReentrantLock();
    //一般将此对象作为成员变量
    Condition condition = lock.newCondition();
    public void conditionWait() throws InterruptedException {
        lock.lock();
        try{
            condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal(){
        lock.lock();
        try{
            //其他线程 调用signal  通知线程后，当前线程从await 方法返回，并且返回前获得了锁
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
