package com.jd.www.base.study.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zhujinpeng on 16/1/7.
 */
@Bruce(y="垃圾京东")
public class Apple {

    @FruitName("apple")
    private String appleName;
    @FruitColor(fruitColor = FruitColor.Color.BULE)
    private String appleColor;
    @FruitProvider(id=1,name="陕西红富士集团",address="陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }
    public String getAppleColor() {
        return appleColor;
    }


    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
    public String getAppleName() {
        return appleName;
    }

    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }

    public static void main(String[]args){
        Apple apple = new Apple();
        System.out.println(apple.getAppleName()+"-----"+apple.getAppleColor()+"----"+apple.getAppleProvider());


    }

}
