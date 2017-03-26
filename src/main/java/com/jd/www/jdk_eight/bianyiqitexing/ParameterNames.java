package com.jd.www.jdk_eight.bianyiqitexing;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 下午2:05</li>
 * <li>function:</li>
 * </ul>
 */
public class ParameterNames {
    public static void main(String[] args)throws Exception{
        ParameterNames parameterNames = new ParameterNames();
        Method method = ParameterNames.class.getMethod("test",String.class,String.class);
        method.invoke(parameterNames,"d","sdfasd1f");
        for(final Parameter parameter : method.getParameters()){
            System.out.println("Parameter:"+parameter.getName());
        }
    }

    public void test(String a,String b){
        System.out.println(a+"--"+b);
    }
}
