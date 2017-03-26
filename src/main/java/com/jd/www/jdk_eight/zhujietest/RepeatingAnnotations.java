package com.jd.www.jdk_eight.zhujietest;


import java.lang.annotation.*;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 上午11:49</li>
 * <li>function:</li>
 * </ul>
 */
//
public class RepeatingAnnotations {


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters{
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter{
        String value();
    }

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable{

    }

    @Repeatable(Schedules.class)
    public @interface Schedule{
        String dayOfMonth() default "first";
        String dayOfWeek() default "Mon";
        int hour() default 12;
    }


    public @interface Schedules{
        Schedule[] value();
    }

    public static void main(String[] args) {
        for(Filter filter:Filterable.class.getAnnotationsByType(Filter.class)){
            System.out.println(filter.value());
        }


    }
}
