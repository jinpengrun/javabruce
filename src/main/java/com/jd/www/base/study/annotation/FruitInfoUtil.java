package com.jd.www.base.study.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by zhujinpeng on 16/1/7.
 * 注解解释器
 */
public class FruitInfoUtil {



    //注解处理器
    public static void getFruitInfo(Class<?> clazz){

        String strFruitName=" 水果名称：";
        String strFruitColor=" 水果颜色：";
        String strFruitProvicer="供应商信息：";

        //声明的属性
        Field[] fields = clazz.getDeclaredFields();

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations){

             if(annotation instanceof Bruce){
                 System.out.println(((Bruce) annotation).address()+"---"+((Bruce) annotation).name()+"---"+((Bruce) annotation).y());
             }
        }

        for(Field field :fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                strFruitName=strFruitName+fruitName.value();
                System.out.println(strFruitName);
            }
            else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor= (FruitColor) field.getAnnotation(FruitColor.class);
                strFruitColor=strFruitColor+fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }
            else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider= (FruitProvider) field.getAnnotation(FruitProvider.class);
                strFruitProvicer=" 供应商编号："+fruitProvider.id()+" 供应商名称："+fruitProvider.name()+" 供应商地址："+fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
    }


    public static void main(String[]args){
        /**
         * @param args
         */

            FruitInfoUtil.getFruitInfo(Apple.class);

    }
}
