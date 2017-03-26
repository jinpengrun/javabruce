package com.jd.www.book.concurrent.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午4:20</li>
 * <li>function:</li>
 * </ul>
 * 队列同步器 用来构建锁或者其他同步组件的基础框架，
 * 使用int成员变量表示同步状态，通过内置的fifo队列来完成资源获取线程的排队工作，
 *
 *
 * 实现一个独占锁
 */
public class SynchronizerTest implements Lock{

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
       return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
       return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }



    //独占的获取 锁 和 释放锁
    private static class Sync extends AbstractQueuedSynchronizer{

        //可重写的方法
        //tryAcquire 独占式获取同步状态，实现前查询状态，再进行cas设置同步状态
        //tryRelease 独占式释放同步   等待获取同步状态的线程酱油机会获取同步状态



        //状态为0 的时候获取锁
        @Override
        protected boolean tryAcquire(int arg) {
         if(compareAndSetState(0,1)){
             setExclusiveOwnerThread(Thread.currentThread());
             return true;
         }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==0)
                throw  new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition(){return new ConditionObject();}


    }
}
