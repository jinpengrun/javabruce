package com.jd.www.base.study.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午12:22</li>
 * <li>function:</li>
 * </ul>
 */
public class ForkJoinTest {
    private double[] d;
    private class ForkJoinTask extends RecursiveTask<Integer>{


        private int first;
        private int last;

        public ForkJoinTask(int first,int last){
            this.first = first;
            this.last = last;
        }


        //递归分治算法 时候 比较适用
        @Override
        protected Integer compute() {
            int subCount;

            //任务切分粒度  只要 在10 以内 就 继续
            //就在这个任务执行   如果不在继续切分任务

            //任务阀值的粒度对 性能 影响较大

            if(last - first < 10){
                subCount = 0;
                for(int i = first;i<=last;i++){
                    if(d[i]<0.5){
                        subCount++;
                    }
                }
                //继续切割任务
            }else{
                int mid = (first+last) >>>1;
                ForkJoinTask left = new ForkJoinTask(first,mid);
                left.fork();
                ForkJoinTask right = new ForkJoinTask(mid+1,last);
                right.fork();

                subCount = left.join();
                subCount+=right.join();

            }

            return subCount;
        }
    }
}
