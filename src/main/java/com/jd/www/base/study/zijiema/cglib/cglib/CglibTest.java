package com.jd.www.base.study.zijiema.cglib.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class CglibTest {

    public static void main(String[] args) throws Exception{
        //可以将字节码打印出来的class 写入到文件中
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/zhujinpeng/bruce/sourcecode/study/java/javabruce/target/classes/com/jd/www/base/study/zijiema/cglib");
        //程序员类
        Programmer progammer = new Programmer();
        //hacker类
        Hacker hacker = new Hacker();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类   需要创建动态代理的类
        //生成 了 子类

        enhancer.setSuperclass(progammer.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，
        // 而Callback则需要实行intercept()方法进行拦截

        enhancer.setCallback(hacker);
        //代理类
        Programmer proxy =(Programmer)enhancer.create();
        //这里打印的是 代理类的   代理类名称
        System.out.println(proxy.getClass().getName());



//        ClassReader cr = new ClassReader(proxy.getClass().getName());
//        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
//
//        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
//
//        byte[] data = cw.toByteArray();
//        File file = new File("HelloCro.class");
//        System.out.println(file.getAbsolutePath());
//        FileOutputStream fout = new FileOutputStream(file);
//        fout.write(data);
//        fout.close();





        proxy.code();

    }
}
