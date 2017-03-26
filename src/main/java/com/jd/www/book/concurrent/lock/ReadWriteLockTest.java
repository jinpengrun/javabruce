package com.jd.www.book.concurrent.lock;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/23 下午9:28</li>
 * <li>function:</li>
 * </ul>
 * readwriteLock 仅仅定义了获取读锁和 写锁的两个方法，即readLock 和writeLock 方法
 * getReadLockCount 获取当前读锁被获取的次数，
 * getReadHoldCount 返回当前线程获取读锁的次数。
 * isWriteLocked 判断写锁是否被获取
 * getWriteHoldCount 返回当前写锁被获取的次数
 */
public class ReadWriteLockTest {

    //实现一个cache
    public static  class Cache<T>{
        Map<T,Object> map = Maps.newHashMap();
        ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
        Lock r = rw1.readLock();
        Lock w = rw1.writeLock();

        public   final Object get(T key){
            //获取读锁
            r.lock();
            try{
                return map.get(key);
            }finally {
                r.unlock();
            }
        }



        public  final Object put(T key,Object value){
            w.lock();
            try{
                return map.put(key,value);
            }finally {
                w.unlock();
            }
        }


        public final void clear(){
            w.lock();
            try{
                map.clear();
            }finally {
                w.unlock();
            }
        }
    }


}
