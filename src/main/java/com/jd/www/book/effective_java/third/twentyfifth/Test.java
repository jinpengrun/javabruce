package com.jd.www.book.effective_java.third.twentyfifth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/18 下午1:31</li>
 * <li>function:</li>
 * </ul>
 */
public class Test {
    public static void testAnquan(){
        Object[] objects = new Long[1];
        objects[0] = "hello world";
        //错误
        //List<Object> ol = new ArrayList<Long>();
        //ol.add("i don't fit in");
    }

    public static void main(String[] args) {
        testAnquan();
    }
}
