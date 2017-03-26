package com.jd.www.base.study.new1dot8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午5:07</li>
 * <li>function:</li>
 * </ul>
 */
public class LambdaTest {

    private static void  oldSortTest(){
       List<String> names = Arrays.asList("peter","anna","mike","linying");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(names);
    }

    private static void newSortLamdaTest(){
        List<String> names = Arrays.asList("peter","anna","mike","linying");
        Collections.sort(names,(String a,String b) -> {
            return b.compareTo(a);
        });
        //简化
        Collections.sort(names,(String a,String b) -> b.compareTo(a));
        //再简化 java自动推导公式
        Collections.sort(names,(a,b) -> b.compareTo(a));

        names.forEach(n-> System.out.println(n));

        names.forEach( n ->{
            testPrint(n);
        });
    }


    public static void main(String[] args) {
        oldSortTest();
        newSortLamdaTest();
    }

    public static <T> void testPrint(T t){
        System.out.println(Thread.currentThread().getName()+" start run and the n is "+t);
    }
}
