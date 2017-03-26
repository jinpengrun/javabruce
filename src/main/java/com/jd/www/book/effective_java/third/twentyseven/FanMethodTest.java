package com.jd.www.book.effective_java.third.twentyseven;

import java.util.*;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/18 下午3:10</li>
 * <li>function:</li>
 * </ul>
 */
public class FanMethodTest {

    //局限 三个集合的类型必须完全相同    使用有限制的 通配符类型 ， 可以使这个更灵活
    public static <E> Set<E> get(Set<E> s1,Set<E> s2){
        Set<E> se = new HashSet<>(s1);
        se.addAll(s2);
        return se;
    }

    //泛型工厂
    //现在1.7已经支持
    public  static <K,V> HashMap<K,V> newhashMap(){
        return new HashMap<K,V>();
    }


    //每次创建一个 有些浪费
    public interface UnaryFunction<T>{
        T  apply(T arg);
    }

    private static UnaryFunction<Object> IDENTITY_FUNCTION =
            new UnaryFunction<Object>() {
                @Override
                public Object apply(Object arg) {
                    return arg;
                }
            };

    //未受检警告
    public static <T> UnaryFunction<T> indentityFunction(){
        return (UnaryFunction<T>)IDENTITY_FUNCTION;
    }



    //类型参数T定义类型，可以与实现Comparable<T>的类型元素进行比较
    //实际上，几乎所有的类型都只能与自身的类型的元素进行比较
    //例如Integer 实现Comparable<Integer>
    public interface Comparable<T>{
        int compareTo(T t);
    }

    //列表的元素可以互相比较 下面是个示例
    //类型限制<T extends Comparable<T>>
    //可以读作 针对可以与自身进行比较的每个类型T
    public static <T extends Comparable<T>> T max(List<T> list){
        return null;
    }


    public static <T extends Comparable<T>> T maxtwo(List<T> list){
       Iterator<T> i = list.iterator();
        T result = i.next();
        while(i.hasNext()){
            T t = i.next();
            if(t.compareTo(result)>0){
               result = t;
            }
        }
        return result;
    }



    public static void main(String[] args) {
        String[] strings = {"jute","hemp","nylon"};
        UnaryFunction<String> sameString = indentityFunction();

        for(String s:strings){
            System.out.println(sameString.apply(s));
        }

        Number[] numbers = {1,2.0,3L};
        UnaryFunction<Number> sameNumber = indentityFunction();
        for(Number n : numbers)
            System.out.println(sameNumber.apply(n));
    }



}
