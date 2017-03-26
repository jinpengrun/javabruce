package com.jd.www.book.effective_java.third.twentythird;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/18 上午11:40</li>
 * <li>function:</li>
 * </ul>
 */
public class FanTest {

    static int get(Set<?> setoen,Set<?> settwo){
        int result = 0 ;
        for(Object obj : setoen){
            if(settwo.contains(obj))
                result++;
        }
        return result;
    }

    static int test(Set<?> set1,Set<?> set2){
        if(set1  instanceof Set){
            //受检检查 不会导致编译时警告
            Set<?> set = (Set<?>)set1;
        }
        return 1;
    }

    public static void main(String[] args) {
        Set<String> set = Sets.newHashSet("1","2");
        Set<String> set1 = Sets.newHashSet("2","3");
        System.out.println(get(set, set1));
    }
}
