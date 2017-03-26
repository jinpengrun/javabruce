package com.jd.www.base.study.thread.forkjoin;

import java.util.concurrent.*;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午2:49</li>
 * <li>function:</li>
 * </ul>
 */
public class ThreadExecutorServiceTest {
    private double[] d;

    private  class ThreadPoolExecutorTask implements Callable<Integer>{

        private int first;
        private int last;
        public ThreadPoolExecutorTask(int first,int last){
            this.first = first;
            this.last = last;
        }
        @Override
        public Integer call() throws Exception {
            int subCount  = 0 ;
            for(int i=first;i<=last;i++){
                if(d[i]<0.5){
                    subCount++;
                }
            }
            return subCount;
        }
    }

    public  void test() throws ExecutionException, InterruptedException {
        double[] d = new double[34];
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4,4,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Future<Integer>[] f =new Future[4];

        int size = d.length/4;

        for(int i =0;i<3;i++){
            f[i] = tpe.submit(new ThreadPoolExecutorTask(i*size,(i+1)*size -1));
        }

        f[3] = tpe.submit(new ThreadPoolExecutorTask(3*size,d.length -1));

        int n = 0;
        for(int i=0;i<4;i++){
             n+=f[i].get();
        }

        System.out.println("found"+n+" values");
    }
}
