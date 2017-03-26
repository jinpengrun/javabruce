package com.jd.www.base.study.annotation;

import java.lang.annotation.Inherited;

/**
 * Created by zhujinpeng on 16/1/7.
 */
@Inherited
public @interface Column {
    public enum FontColor{ BULE,RED,GREEN};
    String name();
    FontColor fontColor() default FontColor.GREEN;
}
