package com.jd.www.base.study.specialf.DriverManager.example;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by zhujinpeng on 16/3/4.
 */
public class MaitaRequestProcesser implements RequestProcesser {

    private static final  ServiceLoader<RequestProcesser> serviceLoader = ServiceLoader.load(RequestProcesser.class);

    private static final String types = "maita";

    @Override
    public boolean acceptType(String type) {
        if(types.equals(type)){
            return true;
        }
        return false;
    }

    @Override
    public String processTheRequest(String info) {
        return "hr:"+info;
    }


    public void test(String message){
        Iterator<RequestProcesser> requestProcesserIterator = serviceLoader.iterator();

        while(requestProcesserIterator.hasNext()){
            RequestProcesser requestProcesser = requestProcesserIterator.next();
            if(requestProcesser.acceptType(message)){
                System.out.println(requestProcesser.processTheRequest(message));
            }
        }
    }



    public static void main(String[]args){
        MaitaRequestProcesser maitaRequestProcesser = new MaitaRequestProcesser();
        //maitaRequestProcesser.test("sijin");
        maitaRequestProcesser.test("maita");
    }
}
