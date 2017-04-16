package com.jd.www.book.youhua.designoptimize.flyweight;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class FinancialReportManager implements IReportManager {

    protected String tenantId = null;

    public FinancialReportManager(String tenantId){
        this.tenantId = tenantId;
    }
    @Override
    public String createReport() {
        return "this is a financial report";
    }
}
