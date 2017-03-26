package com.jd.www.base.study.concurrentpackage;

import java.util.concurrent.Semaphore;

/**
 * Created by zhujinpeng on 16/2/22.
 */
public class SemaphoreTest {

    public static void main(String[]args){
        //信号量的使用 true 表示 公平  等待越久 越容易获取 许可
        Semaphore semaphore = new Semaphore(10,true);


        //5台机器，8个工人
        int i = 8 ;//8个工人
        Semaphore semaphore1 = new Semaphore(5,true);


        for(int n = 0;n<8;n++){
            new Worker(n,semaphore1).start();
        }
    }


    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore ;

        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore= semaphore;
        }


        @Override
        public void run(){
            try{
                semaphore.acquire();
                System.out.println("工人"+this.num+"占用一个机器生产");
                Thread.sleep(2000);
                System.out.println("工人"+this.num+"释放机器");
                semaphore.release();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
