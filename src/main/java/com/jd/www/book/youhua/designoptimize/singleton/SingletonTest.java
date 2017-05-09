package com.jd.www.book.youhua.designoptimize.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class SingletonTest {

    public static void simpleSingletonTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        for(int i=0;i<5;i++) {
            executorService.execute(new TestSimpleSingleton(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("simpleSingletonTest time is "+(System.currentTimeMillis()-start)+"ms");

        executorService.shutdown();

    }


    public static void lazySingletonTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        for(int i=0;i<5;i++) {
            executorService.execute(new TestLazySingleton(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("lazySingletonTest time is "+(System.currentTimeMillis()-start)+"ms");

        executorService.shutdown();
    }



    public static void staticSingletonTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        for(int i=0;i<5;i++) {
            executorService.execute(new TestStaticSingleton(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("staticSingletonTest time is "+(System.currentTimeMillis()-start)+"ms");

        executorService.shutdown();

    }





    static class TestStaticSingleton implements Runnable{

        CountDownLatch countDownLatch;

        TestStaticSingleton(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            for(int i =0;i<10000000;i++){
                StaticSingleton.getInstance();
            }

            countDownLatch.countDown();
        }


    }


    static class TestSimpleSingleton implements Runnable{

        CountDownLatch countDownLatch;

        TestSimpleSingleton(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            for(int i =0;i<10000000;i++){
                SimpleSinglton.getSimpleSingltonInstance();
            }

            countDownLatch.countDown();
        }


    }

    static class TestLazySingleton implements Runnable{

        CountDownLatch countDownLatch;

        TestLazySingleton(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            for(int i =0;i<10000000;i++){
                LazySingleton.getInstance();
            }

            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) {
        simpleSingletonTest();
        lazySingletonTest();
        staticSingletonTest();
    }


}
