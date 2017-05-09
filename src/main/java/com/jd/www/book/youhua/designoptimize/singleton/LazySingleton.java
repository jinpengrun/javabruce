package com.jd.www.book.youhua.designoptimize.singleton;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class LazySingleton {

    private LazySingleton(){
        System.out.println("LazySingleton was created");
    }

    private static LazySingleton instance = null;


    public static synchronized LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();

        }

        return instance;
    }

    public static void printString(){
        System.out.println("yes,i print something");
    }

    public static void main(String[] args) {
        LazySingleton.printString();
    }

}
