package com.jd.www.netty.study.niostart;

import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by zhujinpeng on 15/12/7.
 */
public class TimeServer {

    public static void main(String[]args){
        int port = 9090;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
                                        //名称
        //new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
        long y = 1000000000000l;
        int t = (int)(y >>> 32);
        System.out.println(t);
        System.out.println((int)y);
    }
}
