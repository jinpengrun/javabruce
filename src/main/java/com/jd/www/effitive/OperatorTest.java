package com.jd.www.effitive;


/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/12 下午8:22</li>
 * <li>function:</li>
 * </ul>
 */
public class OperatorTest {

    // | 或运算符 二进制 一个为1 就为1
    private static void testOr(){
        //1 的 二进制为 00001
        int a = 1;
        //5 的 二进制位 00101
        int b = 5;
        //或的结果就 00101 即就是5
        System.out.println(a|b);

    }
    // & 与运算符 二进制 两个都为1 才为1
    private static void testYu(){
        //1 的 二进制为 00001
        int a = 1;
        //5 的 二进制为 00101
        int b = 5;
        //与的结果就是
        System.out.println(a&b);
    }

    // ~ 非运算符 二进制 为0则为1 1 则 为0

    private static void testFei(){
        //5 的 二进制为 0000 0000 0000 0000 0000 0000 0000 0101
        int a = 5;
        // ~5 就是     1111 1111 1111 1111 1111 1111 1111 1010
        // 源码就是  -1 取反    1000 0000 0000 0000 0000 0000 0110
        System.out.print("testFei:");
        System.out.print(Integer.toBinaryString(-6));
        System.out.println(+(~a));

    }

    // ^异或 二进制 不同为 1  相同为 0
    private static void testYihuo(){
        //5的二进制为00101
        int a = 5;
        //1 的二进制为00001
        int b = 1;

        System.out.println(a^b);

    }

    //>> 右移 右移几位
    private static void testYouyi(){
        //5的二进制为00101
        int a = 4;
        System.out.println("a = 4 右移一位，值为："+(a>>1));//右移1位 表示 除以2
    }

    //<< 左移几位
    private static void testZuoyi(){
        //5的二进制为00101
        int a = 5;
        System.out.println("a = 5 左移1位 ，值为："+(a<<1)); //左移1位，表示乘以2
    }

    private static void testYouyiBu(){
        //5 的二进制为00101
        int a = 5;
        System.out.println(a>>>1);
    }

    private static void testFuza(){

        int a = 100;//二进制为 1100100
        //右移 或   找出 这个位的最大 值
        a |= (a>>>1);
        System.out.println(a);
        a |= (a>>>2);
        System.out.println(a);
        a |= (a>>>4);
        System.out.println(a);
        a |= (a>>>8);
        System.out.println(a);
        a |= (a>>>16);
        System.out.println(a);


    }



    public static void main(String[] args) {
        testOr();
        testYu();
        testFei();
        testYihuo();
        testYouyi();
        testZuoyi();
        testYouyiBu();
        testFuza();
    }
}
