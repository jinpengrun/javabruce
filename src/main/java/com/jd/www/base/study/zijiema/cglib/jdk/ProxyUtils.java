package com.jd.www.base.study.zijiema.cglib.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class ProxyUtils {
    /*
     * 将根据类信息 动态生成的二进制字节码保存到硬盘中，
     * 默认的是clazz目录下
         * params :clazz 需要生成动态代理类的类
         * proxyName : 为动态生成的代理类的名称
         *
         * 其实就是 Proxy.newProxyInstance(bmwCar.getClass().getClassLoader(),
                //所有的接口
                classes,invocationHandler);
                这个方法所产生的 代理类 看看这个代理类 很有帮助 ， 然后 通过一个统一的 invocationhandler 来 统一调用 来进行管理
         */
    public static void generateClassFile(Class clazz,String proxyName)
    {
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;

        try {
            //保留到硬盘中
            out = new FileOutputStream(paths+proxyName+".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
