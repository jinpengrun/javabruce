package com.jd.www.base.study.comparable;

/**
 * Created by zhujinpeng on 15/12/14.
 */
public class Diviner implements  Comparable<Diviner>{

    private int i;
    private String name;
    private int age;
    private String address;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "the diviner's i is "+i +" and the name is "+name+" and the age is "+age+" and the address is "+address;
    }


    @Override
    public int compareTo(Diviner o) {
        if(age>o.getAge()){
            return 1;
        }else if(age==o.getAge()){
            return 0;
        }else{
            return -1;
        }
    }
}
