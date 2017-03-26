package com.jd.www.base.study.zijiema.cglib.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhujinpeng on 16/1/12.
 * 类似于invocationhandler
 */
public class Hacker implements MethodInterceptor {

    //适配
    private InvocationHandler invocationHandler;

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
//        methodProxy.invokeSuper(o, objects);
//        System.out.println("****  Oh,what a poor programmer.....");
//        return null;

        return invocationHandler.invoke(o,method,objects);
    }



}
