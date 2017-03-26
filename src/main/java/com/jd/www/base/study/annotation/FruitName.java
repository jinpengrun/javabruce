package com.jd.www.base.study.annotation;

import java.lang.annotation.*;

/**
 * Created by zhujinpeng on 16/1/7.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "卡户";
}
