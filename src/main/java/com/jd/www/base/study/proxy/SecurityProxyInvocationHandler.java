package com.jd.www.base.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhujinpeng on 16/1/11.
 */
public class SecurityProxyInvocationHandler implements InvocationHandler {
    //代理对象
    private Object proxyedObject;
    public SecurityProxyInvocationHandler(Object o) {
        proxyedObject = o;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(proxy instanceof Account && method.getName().equals("operation")){
                System.out.println("我正在检查账户");
        }
        return method.invoke(proxyedObject,args);
    }

    public static void main(String[]args){
        Account account = (Account) Proxy.newProxyInstance(
                Account.class.getClassLoader(),
                //所有的接口
                new Class[]{Account.class},
                //代理
                new SecurityProxyInvocationHandler(new AccountImpl())
        );
        account.operation();
    }
}
