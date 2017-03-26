package com.jd.www.base.study.concurrentpackage;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujinpeng on 16/2/22.
 */
public class CyclicBarrierTest {

    public static void main(String[]args){
        int n = 4;

        //cylicbarrier 的 重用机制
        CyclicBarrier barrier = new CyclicBarrier(n, new Runnable() {
            @Override
            public void run() {
                //下面的线程中 随机 挑选一个 线程 进行 执行下面 这个
                System.out.println("执行完后 执行这个 。。。"+Thread.currentThread().getName()+"执行完毕了。。。。");
            }
        });
        for(int i=0;i<4;i++){
            new Writer(barrier).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("barrier重用");

        for(int i=0;i<4;i++){
            new Writer(barrier).start();
        }
    }


    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run(){
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据");
            try{
                Thread.sleep(5000);
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                //等待其他线程到达  线程都在此等候
                cyclicBarrier.await(5, TimeUnit.SECONDS);
            }catch(Exception e){
                //等待5秒后 抛出异常
                e.printStackTrace();
            }

            System.out.println("所有线程写入完毕，继续处理其他任务。。。。");
        }
    }
}
