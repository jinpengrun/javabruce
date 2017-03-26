package com.jd.www.book.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午10:01</li>
 * <li>function:</li>
 * </ul>
 * 通过有界队列 示例深入了解condition 的使用方式
 */
public class BoundedQueue<T> {
    private Object[] items;
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();


    public BoundedQueue(int size){
        items = new Object[size];
    }

    //添加一个元素，如果数组满，添加线程进入等待队列直到有空位
    public void add(T t) throws Exception{
        lock.lock();
        try{
            while(count == items.length){
                notFull.await();
            }

            items[addIndex] = t;
            if(++addIndex == items.length){
                addIndex = 0;
            }

            ++count;

            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }


    public T remove() throws Exception{
        lock.lock();
        try{
            while(count == 0){
                notEmpty.await();
            }

            Object x = items[removeIndex];
            if(++removeIndex == items.length){
                removeIndex = 0;
            }


            --count;

            notFull.signal();

            return (T)x;

        }finally {
            lock.unlock();
        }
    }





}
