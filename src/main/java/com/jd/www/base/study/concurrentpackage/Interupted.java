package com.jd.www.base.study.concurrentpackage;

/**
 * Created by zhujinpeng on 16/2/23.
 * java中断机制
 * 1 捕获 ，检测到中断后的三种处理方案
 * 1 重新设置中断状态，
 * 2 吞掉中断状态的后果
 * Thread.stop 与中断相比有哪些异同， 什么情况下使用中断。
 *
 * 中断机制是一种协作机制，通过中断不能直接终止另一个线程，需要被中断的线程自己处理中断
 * 比喻， 父母叮嘱在外子女注意身体，子女是否取决自己
 *
 *  不抛出interrupted 线程阻塞操作
 *  1  socket io
 *  2 selector io
 *  3  object wait  锁等待 ， 但是  Lock 可以 提供 锁 中断操作
 *
 *
 *  原则，任务代码不该猜测中断对执行线程的 含义 。
 */
public class Interupted {
    public static void main(String[] args) {
        Thread t = new MyThread();
        t.start();
        t.interrupt();
        System.out.println("已调用线程的interrupt方法");
    }
    static class MyThread extends Thread {
        public void run() {
            int num = 0;
            try {
                num = longTimeRunningNonInterruptMethod(2, 0);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //继续执行  不能中断线程 仅仅将 中断状态设置为 true
            System.out.println("长时间任务运行结束,num=" + num);
            System.out.println("线程的中断状态:" + Thread.currentThread().interrupted());
            System.out.println("线程的中断状态:" + Thread.currentThread().interrupted());
        }

        //该方法是一个可中断方法
        private static int longTimeRunningNonInterruptMethod(int count, int initNum) throws Exception{
//            for(int i=0; i<count; i++) {
//                for(int j=0; j<Integer.MAX_VALUE; j++) {
//                    initNum ++;
//                }
//            }

            if(interrupted()){
                throw new InterruptedException("正式处理前线程已经被请求中断");
            }

            System.out.println("线程正在处理过程中被中断---------kdkdkdkdk");

            if(interrupted()){
                throw new InterruptedException("线程正在处理过程中被中断");
            }

            return initNum;
        }
    }
}
