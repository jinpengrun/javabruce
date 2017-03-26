package com.jd.www.study.teslastudy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zhujinpeng on 16/2/27.
 */
public class SmallTest {
    public static void main(String[]args){
        mapCopy();
        testname();
    }


    /**
     * 测试map 这样不会  对 原来的 值进行修改
     *
     */
    public static void mapCopy(){
        //用到 concurrentHashMap
        ConcurrentMap<String, String> cacheMap =  new ConcurrentHashMap<>();
        cacheMap.putIfAbsent("1","2");

        Map<String,String> map = new HashMap<>(cacheMap);

        //对map的更新 不会影响到 cachemap


        map.put("1","3");

        System.out.println(cacheMap.get("1"));

        System.out.println(map.get("1"));



    }


    public static void testname(){
        //显示 全路径包名
        System.out.println(SmallTest.class.getName());

        System.out.println(String[].class.getCanonicalName());//java.lang.String[]
        System.out.println(String[].class.getName());//[Ljava.lang.String;
    }



}
