package com.jd.www.book.youhua.designoptimize.singleton;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class StaticSingleton {
    private StaticSingleton(){
        System.out.println("staticSingleton was created");
    }

    private static class StaticSingletonHelper{
        private static StaticSingleton staticSingleton = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return StaticSingletonHelper.staticSingleton;
    }


    public static void printString(){
        System.out.println("yes,i print something");
    }

    public static void main(String[] args) {
        printString();
    }
}
