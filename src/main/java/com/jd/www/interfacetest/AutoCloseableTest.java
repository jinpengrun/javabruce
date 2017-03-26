package com.jd.www.interfacetest;


import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/**
 * Created by zhujinpeng on 16/3/3.
 */
public class AutoCloseableTest implements Closeable,Flushable {


    @Override
    public void close() throws IOException {
        System.out.println("hello i'am closed");
    }

    public void nihao(){
        System.out.println("hello nihao ");
    }

    @Override
    public void flush() throws IOException {
        System.out.println("hello，滚蛋");
    }

    public static void main(String[]args){
        //完事后 直接调用close  否则 手动调用
//         try(final AutoCloseableTest closeableTest = new AutoCloseableTest();){
//
//             closeableTest.nihao();
//         }catch(Exception e){
//
//         }
    }


}
