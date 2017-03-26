package com.jd.www.base.study.zijiema.cglib.jdk;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class BMWCar implements Recharge,Vehicle {
    @Override
    public String charge() {
        System.out.println("我可以充电。。。。。");
        return "111";
    }

    @Override
    public String drive() {
        System.out.println("我可以驾驶。。。。。");
        return "222SSSSS";
    }
}
