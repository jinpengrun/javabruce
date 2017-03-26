package com.jd.www.book.effective_java.third.eighteen;

import java.util.AbstractList;
import java.util.List;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午6:47</li>
 * <li>function:</li>
 * </ul>
 */
public class IntfaceTest {
    static List<Integer> intArrayAsList(final int[] a){
        if(a == null){
            throw  new NullPointerException("a is null");
        }
        // 骨架类 很实用
        //模拟多重继承
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index];
            }

            @Override
            public int size() {
                return a.length;
            }
        };


    }


    public static void main(String[] args) {

        List<Integer> list = intArrayAsList(new int[]{1,2,3,4,5});
        list.add(23);
        System.out.println(list);
    }
}
