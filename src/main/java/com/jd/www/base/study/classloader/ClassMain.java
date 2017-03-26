package com.jd.www.base.study.classloader;

import com.sun.nio.zipfs.ZipPath;

import java.lang.reflect.Method;

/**
 * Created by zhujinpeng on 15/12/14.
 */
public class ClassMain extends ClassLoader{


    public static void main(String[] args)throws Exception
    {

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println(System.getProperty("java.class.path"));

        System.out.println("classMain classloader:"+ClassMain.class.getClassLoader());
        System.out.println("classMain classloader's parent：" + ClassMain.class.getClassLoader().getParent());

        System.out.println(ClassLoader.class.getClassLoader()+"----classLoader 为rt里 由引导类 加载器加载");

        System.out.println(ZipPath.class.getClassLoader()+"---zippath 是扩展类 由 扩展类的加载器 加载");


        System.out.println("------------不同类加载器--------------");
        ClassMain c1 = new ClassMain();
        ClassMain c2 = new ClassMain();


        Class clas1 = c1.loadClass("com.jd.www.base.study.classloader.ClassMain$Sample");
        Class clas2 = c2.loadClass("com.jd.www.base.study.classloader.ClassMain$Sample");


        System.out.println(clas1==clas2);



        System.out.println(clas1 == Sample.class);
        System.out.println(clas2 == Sample.class);

         Object obj1 = clas1.newInstance();
         Object obj2 = clas2.newInstance();

        Method setmethod = clas1.getMethod("setSample",java.lang.Object.class);
        setmethod.invoke(obj1,obj2);

    }


    public static class Sample{
        private Sample instance;

        public void setSample(Object instance){
            this.instance = (Sample)instance;
        }
    }


    public Class defineClassFromClassFile(String classname,byte[] data){
        return this.defineClass(classname,data,0,data.length);
    }






}
