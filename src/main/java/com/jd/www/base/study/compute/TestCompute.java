package com.jd.www.base.study.compute;

/**
 * Created by zhujinpeng on 16/1/21.
 */
public class TestCompute {

    public static void main(String[]args){
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(-7));
        //右边移动2位 其他用0 补充
        System.out.println(Integer.toBinaryString(7>>2));
        System.out.println(7>>2);
        //右边移动2位
        System.out.println(Integer.toBinaryString(-7>>2));
        System.out.println(Integer.toBinaryString(7>>>2));
        System.out.println(Integer.toBinaryString(-7>>>2));
        System.out.println(Integer.toBinaryString(7<<2));
        System.out.println(Integer.toBinaryString(-7<<2));


        //负数用补码表示  =  反码+1  负数   原码 10000001
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-128));
        System.out.println(Integer.toBinaryString(0));

    }
}
