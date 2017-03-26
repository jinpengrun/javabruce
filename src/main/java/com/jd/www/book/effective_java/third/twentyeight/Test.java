package com.jd.www.book.effective_java.third.twentyeight;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/21 上午11:57</li>
 * <li>function:</li>
 * </ul>
 * 利用有限制通配符来提升api灵活性
 * List<Type1> 既不是List<type2>的子类型，也不是超类型
 * 虽然list《String》 不是list《object》子类型，这与直觉相悖，但实际有意义
 * 你可以将任何对象放进一个list object 中， 但是只能讲字符串 放进list string中
 * 为了获得最大限度的灵活性，要在表示生产者或者消费者的输入参数上使用通配符类型
 *
 * PECS 表示 producer-extends ， consumer-super
 *
 * 记住pecs 原则 ， 并且记住所有comparable和 comparator 都是consumer
 *
 */
public class Test {

    public  abstract class  Stack<E>{
        public abstract void push(E e);
        public abstract E pop();
        public abstract boolean isEmpty();

        //这个程序编译时 正确，但并非尽如人意
        //如果iterable src 元素类型与堆栈完全匹配就没问题
        //如果有个Stack<Number>并调用了  push<intVal>
        //这是可以 因为Integer 是number的一个子类型
        //因此从逻辑上说 下面这个也行
        //这个是错误的
       // Stack<Number> numberStack = new Stack<Number>();
        //Iterable<Integer> integers = ...;
        //numberStack.pushAll(integers);


        //解决办法  有限制的的通配符类型

        //pushAll 的输入参数不应该为E 的Iterable接口
        //应该是E 的某个子类型的Iterable 的接口
        //有个通配符整合此意
        //Iterable<? Exends E>


        //修改前
        public void pushAll(Iterable<E> src){
            for(E e:src){
                push(e);
            }
        }

        //修改后   假设  Stack<Number> stack  stack 可以 添加 Iterable<Integer>
        public void pushAllAfter(Iterable<? extends E> src){
           for(E e:src){
               push(e);
           }
        }

                                    //E 的某种超类的集合 比如 Stack<Number>
        public void popAll(Collection<? super E> dest){
            while(!isEmpty()){
                dest.add(pop());
            }
        }




    }


    //有区别吗
//    static <T> T reduceold(List<T> list,Function<T> f,T initVal){
//        return null;
//    }
        //有区别 如果有一个List<Integer> 想通过Function<Number>
    // 简化，它不能通过初始声明进行编译，但是一旦加入了有限通配符类型，就可以了
//    static <T> T reducenew(List<? extends T> list,Function<T> f,T initVal){
//        return null;
//    }


    //27条 union方法
    public static <T> Set<T> union(Set<T> s1,Set<T> s2){
        return null;
    }


    //根据pecs  注意返回的是 Set<E>
    public static <T> Set<T> uniontwo(Set<? extends  T> s1,Set<? extends  T> s2){
        return null;
    }

    //max 方法 27条
    public static <T extends Comparable<T>> T max(List<T> list){
        return null;
    }

    //再看看 使用pecs 转换了两次  comparable 始终是消费者 所以 用? super T comparable 始终是消费者 ？ super T 优于 T
    public static <T extends Comparable<? super T>> T maxtwo(List<? extends  T> list){
        //这个有编译错误，意味着list 不是一个list《T》
        //因此它的iterator方法没有回去iterator<T>,

        //Iterator<T> i = list.iterator();

        //修改如下 ，
        Iterator<? extends  T>  i = list.iterator();

        T result = i.next();
        while(i.hasNext()){
            T t = i.next();
            if(t.compareTo(result)>0){
                result = t;
            }
        }
        return result;
    }

    // 使用无限制的类型参数
    public static <E> void swap(List<E> list,int i,int j){

    }
    // 使用无限制的通配符
    public static void swap1(List<?> list ,int i,int j){
        //不能编译，不能讲元素放回到刚刚从中取出的列表中，这似乎不对劲。
        //问题在于list的类型为list《？》 你不能把null之外的任何值放到
        //list<?>中


        //list.set(i,list.get(i));

        //解决办法
        swap(list,i,j);

    }


    private static <T> void swapHelper(List<T> list,int i,int j){
        list.set(i,list.set(j,list.get(i)));
    }


    public static void main(String[] args) {
        Set<Integer> integers = Sets.newHashSet(1,2,3);
        Set<Double>  doubles = Sets.newHashSet(1.2d,1.3d,1.4d);
        // 为什么 会有错  因为 有 comparable  这个返回的T 是 number  不是 comparable
        //Set<Number> numbers =  uniontwo(integers,doubles);  显式定义参数类型

        Set<Number> numbers =  Test.<Number>uniontwo(integers, doubles);





    }



}
