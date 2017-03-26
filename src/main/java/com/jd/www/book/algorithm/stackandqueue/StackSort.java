package com.jd.www.book.algorithm.stackandqueue;

import java.util.Stack;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 下午3:57</li>
 * <li>function:</li>
 * </ul>
 * 用一个栈实现另一个栈的排序
 * 要求
 * 一个栈 除此之外只许申请一个栈
 * 排序的栈记为 stack  辅助的为 help stack 执行pop 弹出元素记为cur
 *
 * 如果cur小于或者等于help栈顶元素，将cur直接压入help
 * 如果cur 大于 help栈顶元素，将help的元素逐一弹出，逐一压入stack，直到cur小于或者等于help 栈顶元素再将cur压入help
 */
public class StackSort<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void sortStackByStack(Stack<T> stack){
        Stack<T> help = new Stack<>();
        while(!stack.isEmpty()){
            //弹出栈顶
            T cur = stack.pop();        //help 跟 cur 比较  大于的话 压入栈顶
            while(!help.isEmpty() && help.peek().compareTo(cur)>0){
                //压入栈顶
                stack.push(help.pop());
            }
            //


            help.push(cur);
        }

        while(!help.isEmpty()){
            stack.push(help.pop());
        }
    }
}
