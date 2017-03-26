package com.jd.www.book.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午3:55</li>
 * <li>function:</li>
 * </ul>
 * lock 接口  通过聚合了一个同步器的子类来完成 线程访问控制的
 * 同步器
 * AbstractQueuedSynchronizer 同步器
 *
 */
public class LockTest {
    Lock lock = new ReentrantLock();

    public void getString() throws InterruptedException {
        lock.lock();
        lock.tryLock(12, TimeUnit.MINUTES); //可超时
        lock.lockInterruptibly(); //可中断
        try{

        }finally {
            lock.unlock();
        }
    }



}
