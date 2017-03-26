package com.jd.www.base.study.specialf.DriverManager.example;

/**
 * Created by zhujinpeng on 16/3/4.
 */
public class SijinRequestProcesser implements RequestProcesser {

    private static final String types = "sijin";

    @Override
    public boolean acceptType(String type) {
        if(types.equals(type)){
            return true;
        }
        return false;
    }

    @Override
    public String processTheRequest(String info) {
        return "sb:"+info;
    }
}
