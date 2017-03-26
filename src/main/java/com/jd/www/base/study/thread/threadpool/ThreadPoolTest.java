package com.jd.www.base.study.thread.threadpool;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/19 下午4:34</li>
 * <li>function:</li>
 * </ul>
 *
 *
 * 对线程池的封装 ， 及时打印出错信息 以及对 ThreadFactory进行封装
 *
 */
public class ThreadPoolTest  extends ThreadPoolExecutor {




    public static class ThreadFactoryMine implements ThreadFactory{

        private String name="Thread-mine-test";
        private  AtomicInteger atomicInteger= new AtomicInteger(1);

        public ThreadFactoryMine(String fox){
            this.name =fox;
        }

        public ThreadFactoryMine(){}

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(name+"-"+atomicInteger.getAndIncrement());
            thread.setDaemon(true);
            return thread;
        }
    }


    private Map<String,AtomicInteger> atomicInteger = new ConcurrentHashMap<>();


    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }



    //覆盖此条方法来 打印出 具体异常
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        printException(r, t);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);

        if(atomicInteger.get(t.getName())==null){
            atomicInteger.put(t.getName(),new AtomicInteger(1));
        }else
            atomicInteger.get(t.getName()).incrementAndGet();
    }

    private void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                    future.get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            System.err.println("Thread pool run exception."+t.getMessage()+"-------"+t.getCause()+"---"+t.getStackTrace()+"-----"+t.getLocalizedMessage());
        }
    }






    //run方法抛出RuntimeException 如何处理
    //通常 Thread.setDefaultUncaughtExceptionHandler(); 异常捕获时候 输出堆栈
    public static class ExeceptionTest implements Runnable{

        @Override
        public void run() {
            throw new RuntimeException("excetion test Exception ");
        }
    }



    public static class CommonTest implements Runnable{

        @Override
        public void run() {
            System.out.println("test...........");
        }
    }


    public static class UncaughtExeptinHandlerTest implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //
        Runnable ra = new ExeceptionTest();
        //正常抛出异常
        Thread thread = new Thread(ra);
        System.out.println("caughtExcupetion ----"+thread.getUncaughtExceptionHandler());
        //thread.start();
        //线程池 执行任务时候，捕获了所有异常，并将此异常计入结果中， 这样的话线程池中的所有线程都将无法捕获到抛出的异常。 从而无法通过设置线程 的默认捕获方法来拦截错误异常
        //Future future = Executors.newSingleThreadExecutor().submit(ra);
        //抛出异常  如果我们 不理会 任务结果 那么 异常就 被吃掉了  除非调用 feture.get  不调用 feture.get 就会遗漏 异常
        //future.get();

        //还有另一种 方案

        ThreadPoolTest executorService = new ThreadPoolTest(10, 10, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            private  AtomicInteger atomicInteger= new AtomicInteger(1);
            private  String name = "hello";
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(name+"-"+atomicInteger.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            }
        })  ;

        executorService.submit(ra);




        ra = new CommonTest();


        for(int i=0;i<10;i++){
            executorService.submit(ra);
        }


        Thread.sleep(5000);
        // 执行完后 开始 打印
        for(Map.Entry<String,AtomicInteger> atomicIntegerEntry : executorService.atomicInteger.entrySet()){
            System.out.println(atomicIntegerEntry.getKey()+"-------"+atomicIntegerEntry.getValue().get());
        }

        System.out.println("rrrrrrrrrrrr");
    }



}
