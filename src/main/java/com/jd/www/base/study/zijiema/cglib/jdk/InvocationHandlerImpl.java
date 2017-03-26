package com.jd.www.base.study.zijiema.cglib.jdk;

import com.jd.www.base.study.proxy.Account;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private BMWCar bmwCar;

    public InvocationHandlerImpl(BMWCar bmwCar){
        this.bmwCar = bmwCar;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("You are going to invoke "+method.getName()+" ...");
        Object o = method.invoke(bmwCar,args);
        System.out.println(method.getName()+" invocation Has Been finished...");
        return o;
    }

    public static void main(String[]args)throws Exception{
        BMWCar bmwCar = new BMWCar();
        Class[] classes = bmwCar.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandlerImpl(bmwCar);
        Object o =  Proxy.newProxyInstance(bmwCar.getClass().getClassLoader(),
                //所有的接口
                classes,invocationHandler);

        Vehicle vehicle = (Vehicle)o;
        System.out.println(vehicle.drive()+"-------");

        Recharge recharge = (Recharge)o;
        System.out.println(recharge.charge()+"-------S");
    }
}
