package com.jd.www.base.study.new1dot8;

import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午5:07</li>
 * <li>function:</li>
 * </ul>
 */



public class LambdaTest {
    //lambda 和 匿名内部类

    private static void  oldSortTest(){
       List<String> names = Arrays.asList("peter","anna","mike","linying");
        //匿名内部类
          //1 代码层级复杂
          //2 不能访问封装类的final类型
          //3 this的迷惑性
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(names);
    }


    //使用lambda表达式  一个接口如果被 @FunctionalInterface 修饰 那么可以使用lambda表达式
    //格式是这样 参数 -> 方法体
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


    //自己的接口 使用 @FunctionalInterface 使其可以使用lambda接口
    @FunctionalInterface
    public interface LambdaClassTest{
        String yourName(String b);
    }




    public static void main(String[] args) {
        oldSortTest();
        newSortLamdaTest();
        LambdaClassTest lambdaClassTest = (a) -> {
            return a+" cao ni ma ";
        };

        System.out.println(lambdaClassTest.yourName("shabi"));


        //1.7已经有的使用了@FunctionalInterface
        //1java.lang.runnable
        Runnable runnable = () -> {
            System.out.println("laji laji laji ");
        };

        new Thread(runnable).start();
        //2java.util.concurrent.Callable  使用lambda 后的简化
        Callable<String> callable = () -> {
          return "laji";
        };
        //2java.security.PriviligedAction
        //4java.util.comparator
        Comparator<String> comparator = (a,b) -> {
            return a.compareTo(b);
        };

        //5java.io.FileFilter
        FileFilter fileFilter = a ->{
            return !a.canExecute();
        };








    }



    /**1.8新增**/
    //Predicate<T>——接收T对象并返回boolean   谓语
    //Consumer<T>——接收T对象，不返回值        消费者
    //Function<T, R>——接收T对象，返回R对象    方法
    //Supplier<T>——提供T对象（例如工厂），不接收值  供应商
    //UnaryOperator<T>——接收T对象，返回T对象
    //BinaryOperator<T>——接收两个T对象，返回T对象

    public static <T> void testPrint(T t){
        System.out.println(Thread.currentThread().getName()+" start run and the n is "+t);
    }
}
