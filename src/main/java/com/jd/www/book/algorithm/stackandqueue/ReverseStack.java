package com.jd.www.book.algorithm.stackandqueue;

import java.util.Stack;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 下午12:18</li>
 * <li>function:</li>
 * </ul>
 * 用递归函数和  栈 操作 来逆序一个栈
 */


public class ReverseStack<T extends  Comparable<T>> {
   //todo
    private Stack<T> stack;

    public void push(T t){
        this.stack.push(t);
    }

    public T peek(){
        return stack.peek();
    }


    public T pop(){
        return stack.pop();
    }






}
