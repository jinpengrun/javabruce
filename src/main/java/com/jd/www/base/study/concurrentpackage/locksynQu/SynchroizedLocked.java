package com.jd.www.base.study.concurrentpackage.locksynQu;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by zhujinpeng on 16/2/23.
 * <p>
 * synchronized 机制， 依赖jvm
 * jvm 如何实现 synchronized
 * <p>
 * lock 硬件曾名依赖特殊的cpu 指令，
 */
public class SynchroizedLocked {
    /**
     * 下面是一个用aqs 实现的最简单的一个非重入互斥锁的功能
     *
     * aqs是基于一个叫clh lock queue 的一个变种来实现线程阻塞阻塞队列，
     */

    /**
     * 子类实现的方法
     * 1 tryAcquire 获取锁
     * 2 tryRelease  释放锁
     * 3 tryAcquireShared
     * 4 tryReleaseShared
     */
    static class SimpleLock extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -7316320116933187634L;

        public SimpleLock() {

        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }



    }


    public static void main(String[]args) throws  Exception{
        final SimpleLock lock = new SimpleLock();
        lock.lock();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                    lock.unlock();
                }
            }).start();
            Thread.sleep(100);
        }

        System.out.println("main thread unlock!");
        lock.unlock();



    }

}
