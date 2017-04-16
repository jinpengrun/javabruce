package com.jd.www.book.youhua.designoptimize.singleton;

/**
 * Created by bruce on 2017/4/15.
 */

public class SimpleSinglton {
    private static SimpleSinglton simpleSinglton = new SimpleSinglton();
    private SimpleSinglton(){
        System.out.println("SimpleSingleton created");
    }

    public  static SimpleSinglton getSimpleSingltonInstance(){
        return simpleSinglton;
    }


    //其他角色
    public static void printString(){
        System.out.println("yes,i print something");
    }

    public static void main(String[] args) {
        SimpleSinglton.printString();
    }
}
