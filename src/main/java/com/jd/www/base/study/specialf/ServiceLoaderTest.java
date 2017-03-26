package com.jd.www.base.study.specialf;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by zhujinpeng on 16/3/4.
 */
public class ServiceLoaderTest {
    public static void main(String[]rags){
        //测试 ServiceLoader
        // 从 META-INF  里读取到  实现类 然后进行装载 ，
        //比如我提供接口 ，别人实现，别人实现后 第三包引入 但是我不想每次更改代码 ok 这样就ok
        //第三方接口人 将实现写入到META-INF 服务端我只需下面就可以运行 简单
        ServiceLoader<ManagerInterface> serviceLoader =
                ServiceLoader.load(ManagerInterface.class);

        Iterator iterator = serviceLoader.iterator();

        for(ManagerInterface service : serviceLoader) {
            service.send("sdfasdf");
        }
    }


    static interface ManagerInterface{
        void send(String str);
    }
    // 两种实现



    //作为provider
    public static class ManagerImpl implements ManagerInterface{

        @Override
        public void send(String str) {
            System.out.println("manager send"+str);
        }
    }

    public static class SendImpl implements ManagerInterface{

        @Override
        public void send(String str) {
            System.out.println("sendimpl send"+str);
        }
    }



}
