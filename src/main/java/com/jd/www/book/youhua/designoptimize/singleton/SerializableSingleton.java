package com.jd.www.book.youhua.designoptimize.singleton;

import java.io.*;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class SerializableSingleton implements Serializable{
    String name;

    private SerializableSingleton(){
        System.out.println("singleton was created");
        name = "test";
    }


    private static SerializableSingleton instance = new SerializableSingleton();

    public static SerializableSingleton getInstance(){
        return instance;
    }

    public static void printString(){
        System.out.println("yes,i printed");
    }

    //实现私有的 方法后 可以实现 为true
    private Object readResolve(){
        return instance;
    }

    public static void main(String[] args) throws Exception {

        SerializableSingleton serializableSingleton = getInstance();

        FileOutputStream fileOutputStream = new FileOutputStream("xx.txt");

        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);

        oos.writeObject(serializableSingleton);

        oos.flush();
        oos.close();

        FileInputStream fileInputStream = new FileInputStream("xx.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);


        SerializableSingleton serializableSingleton1 = (SerializableSingleton) objectInputStream.readObject();

        System.out.println(serializableSingleton == serializableSingleton1);
    }
}
