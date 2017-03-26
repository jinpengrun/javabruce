package com.jd.www.book.algorithm.stackandqueue;

import com.google.common.collect.Lists;

import java.util.Deque;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/5 下午4:48</li>
 * <li>function:</li>
 * </ul>
 * 实现一个函数
 * 1 输入：整形数组arr，窗口大小为w
 * 2 输出：一个长度 n-w+1 的数组res res[i] 表示每一种窗口状态下的最大值
 * 一个数组有多少个 数组窗口
 * o(n*w)   实现 o（n）算法
 *
 * 解法：采用双端队列来实现窗口最大值的更新
 *
 */
public class MaxWindow {


    public static class DequeTest{
        public static void main(String[] args) {
            //双端队列
            Deque<Integer> deque = Lists.newLinkedList();
            System.out.println("####################正常queue#########################");
            //queue 的实现
            deque.offer(12);
            deque.offer(11);

            System.out.println(deque.poll());
            System.out.println(deque.poll());
            System.out.println("################正常queue的另外一种实现#####################");
            deque.offerFirst(12);
            deque.offerFirst(13);

            System.out.println(deque.pollLast());
            System.out.println(deque.pollLast());

            //优先使用该接口 而不是遗留的stack接口
            System.out.println("################栈的实现#################");
            deque.push(12);
            deque.push(13);

            System.out.println(deque.pop());
            System.out.println(deque.pop());

            System.out.println("##############栈的另外一种实现###############");
            deque.offerFirst(12);
            deque.offerFirst(13);

            System.out.println(deque.pollFirst());
            System.out.println(deque.pollFirst());

        }
    }

    /**
     * ins 为数组列表
     * w 为窗口大小
     * @param ins
     * @param w
     * @return
     * 使用双端队列 来 选出最大值
     */
    public static int[] getMaxWindow(int[] ins,int w){
        if(ins == null || w < 1 || ins.length<w ){
            throw new IllegalArgumentException("parameter not right");
        }

        Deque<Integer> qmax = Lists.newLinkedList();
        //定义返回的数组的大小
        int[] res = new int[ins.length - w + 1];

        int index = 0;

        //一次循环  只进入一次队列  选出 最大值
        for(int i=0;i<ins.length;i++){

            while(!qmax.isEmpty()&&ins[qmax.peekLast()] <= ins[i]){
                //弹出
                qmax.pollLast();
            }
            //将i加入 到 双端队列
            qmax.addLast(i);
               // -2 -1 0 1

            // 如果队列第一个 数组下标为 检查 队列里 这个first 下标是否过期
            if(qmax.peekFirst() == i - w){
                qmax.pollFirst();
            }
            //当数组循环到  w-1 下标的时候 就可以 选出最大值了
            if(i >= w - 1 ){

                res[index++] = ins[qmax.peekFirst()];
            }
        }
        return res;

    }


    public static void main(String[] args) {
         int[] rs=getMaxWindow(new int[]{1,2,3,4},2);
        for(int i : rs){
            System.out.println(i);
        }
        int x = 0;
        System.out.println(x++);
        System.out.println(++x);
    }
}
