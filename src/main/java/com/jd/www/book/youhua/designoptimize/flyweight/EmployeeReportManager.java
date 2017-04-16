package com.jd.www.book.youhua.designoptimize.flyweight;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class EmployeeReportManager implements IReportManager {
    protected String tenatentId = null;
    public EmployeeReportManager(String tenatentId ){
        this.tenatentId = tenatentId;
    }
    @Override
    public String createReport() {
        return "this is a employee report";
    }
}
