package com.jd.www.base.study.conllection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/11 下午6:32</li>
 * <li>function:</li>
 * </ul>
 */
public class ConllectionTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("1","2","3");
        list.add(1,"2323");
        System.out.println(list
        );

        HashMap<String,String> hashMap = Maps.newHashMap();

        System.out.println(Integer.toBinaryString(-1));

        System.out.println(Byte.MAX_VALUE+"|-----|"+Byte.MIN_VALUE);
    }
}
