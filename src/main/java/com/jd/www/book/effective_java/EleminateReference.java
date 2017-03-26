package com.jd.www.book.effective_java;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/3 下午4:33</li>
 * <li>function:</li>
 * </ul>
 */
//内存泄露常见来源1 stack 2 自己实现的缓存  weakhashmap 代替，3 监听器和其他回调
    //确保回调立即被当做垃圾回收的最佳方法是只保存弱引用，例如只保存为weakhashmap中的键
    // 借助于heap剖析工具 heap profiler 发现内存泄露
public class EleminateReference {
    public static class Stack{
        private Object[] elements;
        private int size;
        private static final int DEFAULT_INITIONAL_CAPACITY = 16;

        public Stack(){
            elements = new Object[DEFAULT_INITIONAL_CAPACITY];
        }

        public void push(Object object){
            ensureCapacity();
            elements[size++] = object;
        }



        public Object pop(){
            if(size == 0){
                throw new EmptyStackException();
            }
            //memory leak
            //the right way
            Object object = elements[--size];

            //the right way
            elements[size] = null;
            //太多失效的对象存储
            return elements[--size];
        }

        private void ensureCapacity(){
            if(elements.length == size){
                elements = Arrays.copyOf(elements,2*size + 1 );
            }

        }
    }
}
