package com.jd.www.base.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by zhujinpeng on 16/5/11.
 */
@Target(ElementType.TYPE)

public @interface Table {
    public String value() default  "";
}

