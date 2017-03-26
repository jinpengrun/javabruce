package com.jd.www.book.concurrent.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午7:52</li>
 * <li>function:</li>
 * </ul>
 * 设计一个同步工具，该工具同一时刻 ，只允许至多两个线程同时访问， 超过两个线程将会被阻塞
 *
 */
public class TwinsLock {
    //确定访问模式，同一时刻支持多个 线程访问，显然是共享式访问， 需要使用同步器提供的acquireShared 方法，
    //这就需要twinslock 必须重写tryAcquireShared 方法 和 tryReleaseShared（int args）方法

    //定义资源数，twinslock 同一时刻允许至多两个线程同时访问，表名同步数为2，初始status 为2 ，当一个线程进行获取
    //status减1， 状态合法范围为0 ， 1 ，2 ， 同步状态变更时，需要使用 compareAndSet(int expect,int update)方法做原子性保障


    //最后组合自定义同步器， 一般情况下自定义同步器会被定义为自定义同步组件内部类



    //同步器作为一个桥梁 连接线程访问以及同步状态控制等底层技术与不同并发组件（lock countDownLatch） 等接口语义
    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count <= 0){
                throw  new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }


        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;){
                int current = getState();
                int newCount = current + arg;

                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }


        // 当方法返回值大于等于0时，当前线程才获取同步状态，
        @Override
        protected int tryAcquireShared(int arg) {
            for(;;){
                int current = getState();
                int newCount = current - arg;
                if(newCount < 0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

    }
}
