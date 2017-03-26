package com.jd.www.base.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhujinpeng on 16/9/21.
 * 中断的使用范围
 *
 *点击某个桌面应用中的取消按钮时；
 某个操作超过了一定的执行时间限制需要中止时；
 多个线程做相同的事情，只要一个线程成功其它线程都可以取消时；
 一组线程中的一个或多个出现错误导致整组都无法继续时；
 当一个应用或服务需要停止时。
 *
 */
public class ThreadInteruptTest {


    private static class ThreadCon extends Thread{
        public void run(){
            for(int i=0;i <Integer.MAX_VALUE;i++){


                //System.out.println(i);

                //加入中断判断 终止线程  自己判断是否被中断
                if(isInterrupted()){
                    System.out.println("isinterupted:"+isInterrupted());
                    interrupted();
                    System.out.println("isinterupted:"+isInterrupted());
                }

                //清除状态  中断后 才能清除  不中断 返回为false
//                if(interrupted()){
//                                                //第二次调用 清除状态             false
//                    //System.out.println("double:"+interrupted()+" isInterupted:"+isInterrupted());
//
//                    System.out.println("isinterupted: "+isInterrupted()+" double:"+interrupted());
//                    System.out.println("interrupted..");
//                    return;
//                }
            }
        }
    }


    public static void main(String[] args) {
        ThreadCon threadCon = new ThreadCon();
        threadCon.start();

        System.out.println("main thread");

        try{
            TimeUnit.SECONDS.sleep(1l);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //一版本中 虽然中断 但是会一直运行下去
        threadCon.interrupt();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadCon.isAlive()+" isinterupted:"+threadCon.isInterrupted());

    }

    //Thread.interupted 重新设置状态位

}
