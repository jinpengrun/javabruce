package com.jd.www.book.effective_java.third.twentysixth;

import com.jd.www.book.effective_java.third.apiread.S;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/18 下午1:56</li>
 * <li>function:</li>
 * </ul>
 */
public class Stack {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack(){
       elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object obj){
        ensureCapcity();
        elements[size++] = obj;

    }

    public Object pop(){
        if(size==0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    public void ensureCapcity(){
        if(size == elements.length){
            elements = Arrays.copyOf(elements,2*size + 1);
        }
    }
}
