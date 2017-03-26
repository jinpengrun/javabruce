package com.jd.www.base.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhujinpeng on 16/5/12.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bruce {
    public enum Color{BULE,RED,GRREN};

    Color fruitColor() default Color.BULE;

    String name() default "bruce";

    String address() default "陕西省渭南市临渭区朱王村4组";

    String y();
}
