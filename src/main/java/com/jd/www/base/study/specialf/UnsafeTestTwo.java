package com.jd.www.base.study.specialf;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zhujinpeng on 16/2/18.
 * java中最直接的内错操作方法是什么，java最初被设计为一种安全的受控环境，
 * java hotspot 包含了一个后门， sun.misc.Unsafe, 内部包 大量 使用  nio and  java.util.concurrent
 * 用处：观察hotspot jvm 内部结构 并且可以对其进行修改。有时它可以被用来在不适合c++ 调试的情况下学习虚拟机内部结构
 * 可以被用来做性能监控和开发工具
 *
 * 构造器私有 ，getUnsafe 只能 被bootloader 所加载，
 *
 *
 *
 *
 * Unsafe 一些有用的特性
 * 1 虚拟机集约化
 * 2 主机虚拟机
 * 3 从本地内存地址中 追踪到这些数据。 使用 Unsafe 类 获取内存地址 ， 并且可以以直接操作这些变量
 * 4 使用allocateMemory 内存可以分配到堆外
 * 5 arrayBaseoffset和 arrayIndexScale 方法可以被用于开发 arraylets
 *
 */
public class UnsafeTestTwo {


    public static void main(String[]args) throws Exception{
      Field fileld = Unsafe.class.getDeclaredField("theUnsafe");

        fileld.setAccessible(true);

         Unsafe unsafe = (Unsafe)fileld.get(null);

    }
}
