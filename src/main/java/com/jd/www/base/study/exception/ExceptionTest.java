package com.jd.www.base.study.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujinpeng on 15/12/12.
 */
public class ExceptionTest {

    private int stackLength = 1 ;

    public void stacctest(){
        stackLength++;
        stacctest();
    }

    public static void main(String[]args){
        //虚拟机参数 -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
//        List<ExceptionTest> list = new ArrayList<ExceptionTest>();
//        while(true){
//            list.add(new ExceptionTest());
//        }

        //栈溢出
        //虚拟机参数 -Xss128k
//        ExceptionTest test = new ExceptionTest();
//        try {
//            test.stacctest();
//        }catch (Throwable e){
//            System.out.println("the stacklength is "+test.stackLength);
//        }

       // 测试内容：常量池溢出（这个例子也可以说明运行时常量池为方法区的一部分）
        //虚拟机参数-XX:PermSize=10M -XX:MaxPermSize=10M
        System.out.println(System.getProperty("java.vm.name"));
        int i = 0;
        while(true){
            //一直存放到 方法区 常量池  1.7之后 就不会出现了  jdk 改版
            String.valueOf(i++).intern();
        }
    }
}
