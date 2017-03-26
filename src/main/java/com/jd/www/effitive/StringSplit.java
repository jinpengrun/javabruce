package com.jd.www.effitive;

import java.util.StringTokenizer;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/15 下午11:03</li>
 * <li>function:</li>
 * </ul>
 */
public class StringSplit {

    //split
    private static void testSplit(){
        long start = System.currentTimeMillis();
        String ss = "sjdfklsajdf,skdjflkasjdf";
        for(int i=0;i<3000000;i++){
            String[] s = ss.split(",");
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    private static void testStringTo(){
        long start = System.currentTimeMillis();
        String ss = "sjdfklsajdf,skdjflkasjdf";
        StringTokenizer sss = new StringTokenizer(ss,",");
        for(int i=0;i<3000000;i++){
           sss.countTokens();

        }
        System.out.println(sss.nextToken());
        System.out.println(System.currentTimeMillis()-start);
    }

    public static void main(String[] args) {
        testSplit();
        testStringTo();
    }
}
