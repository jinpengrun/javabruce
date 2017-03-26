package com.jd.www.base.study.specialf;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;

/**
 * Created by zhujinpeng on 16/2/18.
 * unsafe 的 一些特性 不是j2se 真正的一部分，没有任何的官方文档，也没有 比较好的代码文档
 *
 * 1 unsafe 类的构造函数 私有
 * 2 有静态的 getUnsafe() 方法，如果尝试调用 getUnsafe得到安全错误，只有被 jdk信任的类来调用
 *
 * 变通方法， 1 反射
 */


public class UnsafeTest {

    public static  Unsafe  getUnsafe()throws  Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        Unsafe unsafe = (Unsafe)field.get(null);
        //System.out.println(unsafe);
        return unsafe;
    }

    public static void main(String[]args){
        try {
            //创建 unsafe
            Unsafe unsafe = getUnsafe();

            //使用unsafe
            //创建私有类
            Player testCompute = (Player)unsafe.allocateInstance(Player.class);

            System.out.println(testCompute);

            testCompute.setAge(20);

            System.out.println(testCompute.getAge());


            //直接获取内存的 方式来实现 浅克隆

            //来自黑客的密码安全  密码存放建议存放在 char数组中， string的话 完后 制null 被回收
            //
            String password = new String("l00k@myHor$e");
            String fake = new String(password.replaceAll(".", "?"));
            System.out.println(password); // l00k@myHor$e
            System.out.println(fake); // ????????????

            //getUnsafe().copyMemory(fake, 0L, null, toAddress(password), password.length());

            System.out.println(password); // ????????????
            System.out.println(fake); // ????????????



            //利用unsafe 运行时创建类
            byte[] classContents = null;//getClassContent();
            //增加 了 属性
            //Class c = getUnsafe().defineClass()




            //超大数组 会导致jvm 挂掉。。。
            System.out.println(Integer.MAX_VALUE); //java 中数组 最大值
            System.out.println(Long.MAX_VALUE);



            //数组长度 1000 一个数组 一个字节
            long supur_size = Integer.MAX_VALUE;
            SuperArray superArray = new SuperArray(supur_size);

            System.out.println(superArray.address);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static byte[] getClassContent() throws Exception{
        File f = new File("/home/mishadoff/tmp/A.class");
        FileInputStream input = new FileInputStream(f);
        byte[] content = new byte[(int)f.length()];
        input.read(content);
        input.close();
        return content;
    }

    class Player{
        private int age = 12;
        private Player(){
            this.age = 23;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


    static class SuperArray{
        private final static int BYTE = 1;
        private long size;
        private long address;

        public SuperArray(long size){
            this.size = size;
            try {
                address = getUnsafe().allocateMemory(size*BYTE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void set(long i,byte value){
            try {
                getUnsafe().putByte(address+i*BYTE,value);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int get(long idx){
            try {
                return getUnsafe().getByte(address+idx*BYTE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return -1;
        }
    }

}
