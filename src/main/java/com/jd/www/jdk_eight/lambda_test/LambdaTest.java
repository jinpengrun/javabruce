package com.jd.www.jdk_eight.lambda_test;

import java.util.Arrays;
import java.util.List;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/13 下午12:17</li>
 * <li>function:</li>
 * </ul>
 */
public class LambdaTest {
    private static void lambdaTest(){
        //e为编译器推测 可以自行指定
        Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
        //
        Arrays.asList("a","b","c").forEach((String e) -> System.out.println(e));
        //lambda包含复杂体用{} 括起来
        Arrays.asList("a","b","c").forEach((String e) ->
        {
            System.out.println("复杂情况用{}括起来:"+e);
        });

        //lambda表达式可以引用类成员和 局部变量（隐式转换成final）

        String separator = ",";
        Arrays.asList("a","b","c").forEach((String e) ->
        {
            System.out.print("隐式转换成final:" + e + separator);
        });

        //lambda表达式 有返回值 ，只有一行 可省略返回值

        Arrays.asList("a","c","e","b").sort((String e1,String e2) -> e1.compareTo(e2));

        Arrays.asList("a","c","e","b").sort((String e1,String e2) ->
                {
                   int result =  e1.compareTo(e2);
                    System.out.println("compare:"+e1+"--"+e2);
                    return result;
                }
        );
    }

    //只有一个函数的接口 可以隐式转换成lambda表达式
    //java8提供了一个特殊的注解@FunctionalInterface 标注这个是一个函数式接口
    //默认方法和静态方法不会破坏函数式 接口的定义
    private static void functionInterfaceTest(){

    }
    @FunctionalInterface
    public interface FunctionalDefaultMethods{
        void method();
        default void defaultMethod(){
            System.out.println("hello world");
        }
    }


    //approach 1


    private static void printPersonsOlderThan(List<Person> roster,int age){
        for(Person p:roster){
            if(p.getAge()>=age){
                System.out.println(p);
            }
        }
    }

    class Person{
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        lambdaTest();
    }
}
