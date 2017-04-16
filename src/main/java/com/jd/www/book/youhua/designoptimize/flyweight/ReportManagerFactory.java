package com.jd.www.book.youhua.designoptimize.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhujinpeng on 2017/4/16.
 */
public class ReportManagerFactory {
    Map<String,IReportManager> financialReportManager = new HashMap<>();
    Map<String,IReportManager> employeeReportManager = new HashMap<>();

    IReportManager getFinancialManager(String tenantId){
        IReportManager r = financialReportManager.get(tenantId);

        if(r == null){
            r = new FinancialReportManager(tenantId);
            financialReportManager.put(tenantId,r);
        }

        return r;
    }

    IReportManager getEmployeeManager(String tenantId){
        IReportManager r = employeeReportManager.get(tenantId);

        if(r == null){
            r = new EmployeeReportManager(tenantId);
            employeeReportManager.put(tenantId,r);
        }

        return r;
    }


    public static void main(String[] args) {
        ReportManagerFactory rmf = new ReportManagerFactory();
        IReportManager rm = rmf.getFinancialManager("A");
        System.out.println(rm.createReport());
    }




}
