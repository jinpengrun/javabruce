package com.jd.www.base.study.specialf;

/**
 * Created by zhujinpeng on 16/3/4.
 */
public class StackElementTest {
    public static void main(String[]rags){
        new TestM().OuterMethod();
    }

    public void methodA(){
        methodB();
    }

    public void methodB(){
       methodC();
    }


    public void methodC(){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for(StackTraceElement s: stacks){
            System.out.println("-------"+s.getMethodName()+" : "+s);
        }
    }


    static class TestM {
        public void OuterMethod(){
            new StackElementTest().methodA();
        }
    }




}
