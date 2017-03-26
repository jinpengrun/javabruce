package com.jd.www.book.effective_java.third.twentysixth;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/18 下午2:10</li>
 * <li>function:</li>
 * </ul>
 */
public class StackUpdate<E> {
   private int size;
    //private E[] elements;
    private Object[] elements;
    private static final int DEFAULT_INIT_CAPACITY = 16;

    public StackUpdate(){
        //不可实例化 不可具体化得对象
        //elements = new E[DEFAULT_INIT_CAPACITY];
        //第一种改法
        //elements = (E[])new Object[DEFAULT_INIT_CAPACITY];
        //第二种
        elements = new Object[DEFAULT_INIT_CAPACITY];
    }

    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
      if(size == 0 )
          throw new EmptyStackException();
        @SuppressWarnings("unchecked")
        E e = (E)elements[--size];
        elements[size] = null;
        return e;
    }

    private void ensureCapacity(){
        if(size == elements.length){
           elements =  Arrays.copyOf(elements,size*2+1);
        }
    }


    public static void main(String[] args) {
        StackUpdate<String> stackUpdate = new StackUpdate();
        stackUpdate.push("cengjingcanghai");
        System.out.println(stackUpdate.pop());
    }
}
