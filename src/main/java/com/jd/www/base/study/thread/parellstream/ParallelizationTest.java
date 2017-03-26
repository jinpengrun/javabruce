package com.jd.www.base.study.thread.parellstream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午3:50</li>
 * <li>function:</li>
 * </ul>
 */
public class ParallelizationTest {
    public static void main(String[] args) throws Exception{

        System.out.println(System.getProperties().getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
        List<String> stringList = Lists.newArrayList("fjds","asdfas","ASKJDFLKA","weijJKj","sdfas","2323","sdfadsf","fsdfasdf","sdfasdf","asdfasdf","90909","asdfasdf","asdfasd","asdfasdf");

        Stream<String> stream = stringList.parallelStream();

        stream.forEach(a -> {
            String symbol = a.toLowerCase();
            System.out.println(Thread.currentThread().getName()+"---"+a+"---"+Thread.currentThread().getThreadGroup().activeCount());
//            System.out.println(Thread.currentThread().getThreadGroup().activeCount());
        });
        String a = "sjdflksjdfasd";

    }
}
