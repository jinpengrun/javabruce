package com.jd.www.base.study.concurrentpackage.locksynQu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhujinpeng on 16/2/23.
 *
 * Synchroized 和 Lock  区别
 * 1  硬件上 syn 交由 jvm 底层  lock 由 java 代码实现，内部 调用 cas （内部也为 cpu指令）
 * 2 lock 可以在某个线程 在等待一个锁的控制权的这段时间需要中断， 继续执行
 * 3 需要公开处理一些wait-notify reentrantlock 里面的 condition 应用， 能够控制 notify 哪个线程
 * 4 具有公平锁功能，每个到来的线程都将排队 等候
 */
public class SynLockTest {
    //演示 3
    //notify 只能唤醒这个对象已经调用wait的线程。而下面的condition可以根据条件来控制唤醒哪个线程。
    //下面例子分别为可以控制唤醒notfull与 notempty

    final Lock lock = new ReentrantLock();
    final Condition notfull = lock.newCondition();
    final Condition notempty = lock.newCondition();

    final Object[] items = new Object[100];

    int putptr,takeptr,count;
    //一个 lock  两个条件 ，  根据条件 判断 来 唤醒哪个 condition
    public void put(Object x) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length)
                notfull.await();
            items[putptr] = x;
            if (++putptr == items.length)
                putptr = 0;
            ++count;
            notempty.signal();
        } finally {
            lock.unlock();
        }



    }



    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notempty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            notfull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }


    public static  void main(String[]args){


    }
}
