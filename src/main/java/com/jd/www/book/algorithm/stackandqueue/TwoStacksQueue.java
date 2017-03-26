package com.jd.www.book.algorithm.stackandqueue;

import java.util.Stack;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 上午11:41</li>
 * <li>function:</li>
 * </ul>
 *
 * 用两个 stack 来实现queue
 */
public class TwoStacksQueue<T extends Comparable<T>> {
    public Stack<T> stackPush;
    public Stack<T> stackPop;


    public TwoStacksQueue(){
        this.stackPop = new Stack<>();
        this.stackPush = new Stack<>();
    }


    public void add(T pushT){
        stackPush.push(pushT);
    }

    public T poll(){
        if(stackPop.empty() && stackPush.empty()){
            throw  new RuntimeException("Queue is empty");
        }else if(stackPop.empty()){
            while(!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }

        return stackPop.pop();
    }



    public T peek(){
        if(stackPop.empty()&&stackPush.empty()){
            throw new RuntimeException("your queue is empty");
        }else if(stackPop.empty()){
            while(!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }





}
