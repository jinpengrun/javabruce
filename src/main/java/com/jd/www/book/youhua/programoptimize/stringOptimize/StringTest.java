package com.jd.www.book.youhua.programoptimize.stringOptimize;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public class StringTest {

    public static void main(String[] args) {
        String s = "1";
        String b = "1";
        String c = new String("1");
        System.out.println(s==b);//true
        System.out.println(b==c);//false
        System.out.println(b==c.intern());//true
    }

}
