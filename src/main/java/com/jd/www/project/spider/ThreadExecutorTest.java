package com.jd.www.project.spider;

import java.util.concurrent.*;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/1 上午4:42</li>
 * <li>function:</li>
 * </ul>
 */
public class ThreadExecutorTest extends ThreadPoolExecutor{





    public ThreadExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {


    }

    public static void main(String[] args) {
        ThreadExecutorTest threadExecutorTest = new ThreadExecutorTest(2, 2, 6000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                executor.getQueue().poll();
                executor.execute(r);
            }
        });


        //如果runnable 里抛出了 异常如何处理
        Runnable ad = new Runnable() {
            @Override
            public void run() {
                throw new IllegalArgumentException("the set must not be null");
            }
        };


        //通常java.lang.Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler) 默认异常处理

        //这个默认静态全局的异常捕获方法时候输出堆栈

        //我们亦可以实现 接口自定义 UncaughtExceptionHandler

        //线程池比较特殊 默认 ThreadpoolExecutor 会catch 所有异常，
        // submit(callable) 取结果会 java.util.concurrent.Future.get() 抛出RuntimeException




    }




}
