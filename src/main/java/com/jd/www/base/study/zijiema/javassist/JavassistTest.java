package com.jd.www.base.study.zijiema.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import java.lang.reflect.Method;

/**
 * Created by zhujinpeng on 16/1/12.
 *
 * javassist 字节码功能
 */
public class JavassistTest {

    public static void main(String[]args) throws Exception{
        //动态生成一个类  并且调用 方法
        ClassPool pool = ClassPool.getDefault();
        //创建Programmer类
        CtClass cc= pool.makeClass("com.jd.www.base.study.zijiema.javassist.JinpengTest");
        //定义code方法
        CtMethod method = CtNewMethod.make("public static void main(String[]args){}", cc);
        //插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        cc.addMethod(method);
        //保存生成的字节码
        byte[] ss = cc.toBytecode();
        //得到这个类
        Class c = new myTestLoader().defineClassFromClassFile("com.jd.www.base.study.zijiema.javassist.JinpengTest"
                , ss);

        //
        Method[] methods = c.getDeclaredMethods();

        for(Method method1 : methods){
            System.out.println(method1.getName());
            method1.invoke(c,(Object)new String[]{"duancanmeng1","duancanmeng2","duancanmeng3"});
        }

    }


    static class myTestLoader extends ClassLoader{
                                            //全限定名
        public Class defineClassFromClassFile(String className,
                                              byte[] classFile) throws ClassFormatError {
            return defineClass(className, classFile, 0,
                    classFile.length);
        }
    }
}
