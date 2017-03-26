package com.jd.www.base.study.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhujinpeng on 16/3/2.
 */
public class ThreadLocalTest implements  Runnable{



    private static ThreadLocal<Date> stardata = new ThreadLocal<Date>(){

        protected Date initialValue(){
            return new Date();
        }
    };


    @Override
    public void run() {

        System.out.println("start thread" + Thread.currentThread().getName()+" "+stardata.get()+"----"+Thread.currentThread().getId());
        try{

            TimeUnit.SECONDS.sleep(1);
            if(Thread.currentThread().getId()==11){
                stardata.set(new Date());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("thread finish "+Thread.currentThread().getName()+" "+stardata.get());
    }


    public static void main(String[]ra) throws InterruptedException {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        for(int i=0;i<10;i++){
            Thread thread = new Thread(threadLocalTest);
            thread.start();

        }

        TimeUnit.SECONDS.sleep(2);
    }
}
