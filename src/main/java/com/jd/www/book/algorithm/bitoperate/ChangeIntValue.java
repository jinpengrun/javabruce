package com.jd.www.book.algorithm.bitoperate;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/9 下午8:03</li>
 * <li>function:</li>
 * </ul>
 */
public class ChangeIntValue {


    //不用额外变量交换两个整数的值
    public static void change(){
        int a = 2 ; //a = 10
        int b = 3 ; // b = 11
        int c = a^b;
        System.out.println(a^c);
        System.out.println(a^b);
    }

    //不用任何比较判断找出两个数中较大的值
    public static int getMax1(int a,int b){
        //如果a-b出现溢出
        int c = a-b;
        int scA = sign(c);
        int scb = flip(scA);
        return a*scA + b*scb;
    }

    public static int flip(int n){
        return n^1;
    }

    //返回整数n的符号 ， 正数和0 返回1 ，负数返回0 ，
    public static int sign(int n){
        return flip((n >> 31) & 1);
    }



    public static int getMax2(int a,int b){
        int c = a-b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);


        //
        int difsab = sa ^ sb;
        int sameSab = flip(difsab);
        int returna = difsab * sa + sameSab * sc;
        int returnb = flip(returna);
        return a* returna + b*returnb;


    }


    //统计整数的二进制有多少个1
    public static int count1(int n){
       int res = 0;
        while(n!=0){
            //n&1 判定n 的为是否 为1  同为1 则为1
            res += n&1;

            //无符号右移一位
            n>>>=1;
        }
        return res;
    }

    public static int count2(int n){
        int res = 0;
        while(n!=0){
            n &=(n-1);
            res++;
        }
        return res;
    }

    //数组中出现奇数的数







    public static void main(String[] args) {
        change();
        System.out.println(getMax1(1,10));
        System.out.println(getMax2(1,2));
        System.out.println(count1(23232323));
        System.out.println(count2(23232323));
    }
}
