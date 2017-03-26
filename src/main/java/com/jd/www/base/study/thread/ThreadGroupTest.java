package com.jd.www.base.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhujinpeng on 16/3/2.
 */
public class ThreadGroupTest implements Runnable {


    Result result ;
    public ThreadGroupTest(Result result    ){
        this.result= result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        System.out.println(name);

        System.out.println("Thread start "+ name);

        try {
            Thread.sleep(1000);
            this.result.setName(name);
        } catch (InterruptedException e) {
            throw new RuntimeException("main thread not wait the sonthread ...");
        }
    }


    public static void main(String[]rgs) throws InterruptedException {
        //create 5 thread put it in threadgroup
        ThreadGroup threadGroup = new ThreadGroup("search");

        Result result = new Result();

        ThreadGroupTest threadGroupTest = new ThreadGroupTest(result);




        //这个group 里有多少 thread
        for(int i=0;i<50;i++){
            Thread thread = new Thread(threadGroup, threadGroupTest);
            thread.setName("hello-nihao"+i);
            thread.start();
        }











        //所有线程 都以 运行 结束
        //Thread.sleep(2000);
        //所有线程 都以 运行 结束
        Thread.sleep(900);


        //查看group 里所有信息  这里将是 0
        System.out.println("number of thread: "+threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        //拷贝thread 到 threads
        threadGroup.enumerate(threads);

        for(Thread thread1 : threads){
            System.out.println(thread1.getName()+"---"+thread1.getState());
        }

        //等待线程运行结束  直到所有线程执行完毕
//        while(threadGroup.activeCount()>0){
//            TimeUnit.SECONDS.sleep(1);
//        }

        threadGroup.interrupt();



        System.out.println(result.getAddress()+"--"+result.getName());

    }



    static class Result{
        public String name;
        public String address;
        public Result(){
            this.name = "bruce";
            this.address="shaan";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
