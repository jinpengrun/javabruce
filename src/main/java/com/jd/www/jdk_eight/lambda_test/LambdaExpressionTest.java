package com.jd.www.jdk_eight.lambda_test;

import java.util.Arrays;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 上午11:58</li>
 * <li>function:</li>
 * </ul>
 */
public class LambdaExpressionTest {
    interface IntegerMath{
        int operation(int a,int b);
    }

    public int operateBinary(int a,int b,IntegerMath op){
        return op.operation(a,b);
    }


    public static void main(String[] args) {
        LambdaExpressionTest lambdaExpressionTest = new LambdaExpressionTest();
        IntegerMath addition = (int a,int b) -> {return a+b;};
        IntegerMath subtraction = (int a,int b) -> {return a-b;};

        System.out.println("4+2="+lambdaExpressionTest.operateBinary(40,2,addition));
        System.out.println("4-2="+lambdaExpressionTest.operateBinary(40,2,subtraction));


        Arrays.asList("a","b","c").forEach( p -> System.out.println(p));
        Arrays.asList("a","b","c").sort((String a,String b)-> {return Integer.valueOf(a)-Integer.valueOf(b);} );
    }
}
