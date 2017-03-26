package com.jd.www.base.study.reference;

import java.lang.ref.WeakReference;

/**
 * Created by zhujinpeng on 15/12/13.
 */
public class Reference {
    public static void main(String[]args){
        //强引用
        Object object = new Object(); //强引用
        Runtime run = Runtime.getRuntime();
        System.out.println(run.maxMemory());
        //软引用 java 类 softrefence在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行二次回收 如果回收没有足够内存 才抛出异常
        //弱引用  被弱引用关联的对象只能生存到下一次垃圾回收之前，垃圾收集器工作之后，无论当前内存是否足够，都会回收掉只被弱引用关联的对象  weakrefence
        //虚引用 唯一目的就是在这个对象被收集器回收时收到一个系统通知，被虚引用关联的对象，和生存时间没有关系
            // java 类 phantomreference 表示虚引用

        //方法区回收，类回收，判断，该类所有实例都被回收，堆中不存在该类的任何实例
        //加载该类的classloader已经被回收
        //该类对应java.lang.class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法
        //在大量使用反射，动态代理，cglib等bytecode框架，以及动态生成jsp以及osgi这类频繁定义classloader的场景
        //都需要虚拟机具备类卸载功能，以保证方法区不会溢出
        Car car = new Car(2000.0, "red");
        WeakReferenceCar wrc = new WeakReferenceCar(car);
        int i = 0;
        while (true)
        {
            if (wrc.get() != null)
            {
                i++;
                //System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            }
            else
            {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }
    }


    public static class WeakReferenceCar extends WeakReference<Car>{
        public WeakReferenceCar(Car car){
            super(car);
        }
    }
}
