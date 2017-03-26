package com.jd.www.base.study.clone;

import java.util.ArrayList;

/**
 * Created by zhujinpeng on 15/12/11.
 */
public class SimpleObject implements Cloneable{
    private String str;
    private int y = 10;

    private Test test;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SimpleObject(){
        System.out.println("Enter simpleObject.contructor()");
    }

    public String getStr(){
        return str;
    }

    public void setStr(String str){
        this.str = str;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

     public class Test{
        int i=19;
        int z=10;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
        }
    }

    public static void main(String[]args){
        //深克隆  浅克隆
        //深克隆 查看 ArrayList 的 clone 方法
        SimpleObject so0 = new SimpleObject();
        so0.setStr("111");
        System.out.println("so0.getstr():" + so0.getStr());
        SimpleObject so1 = so0;
        so1.setTest(so1.new Test());

        so1.setStr("222");

        System.out.println("so0.getStr():" + so0.getStr());
        System.out.println("so1.getStr():" + so1.getStr());

        try {
            SimpleObject so2 = (SimpleObject)so1.clone();
            System.out.println("so2.getStr():"+so2.getStr());
            System.out.println("so2.gety():"+so2.getY());
            System.out.println("so2.gettest:"+so2.getTest().getI()+"----"+so2.getTest().getZ());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }




    }
}
