package com.jd.www.base.study.specialf.DriverManager.example;

/**
 * Created by zhujinpeng on 16/3/4.
 */
public interface RequestProcesser {

    boolean acceptType(String type);

    String  processTheRequest(String info);
}
