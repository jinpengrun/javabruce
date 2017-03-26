package com.jd.www.base.study.designpatterns.reactorP.scalableio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhujinpeng on 16/3/9.
 */
public class SimpleServer implements Runnable {
    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9090);

            //三个阻塞状态
            while(!Thread.interrupted()){//
                                        //阻塞在 accept 上
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Handler implements Runnable{

        final Socket socket;

        Handler(Socket s){socket = s;}

        @Override
        public void run() {
            try{
                byte[] input = new byte[1024];
                        //阻塞状态
                socket.getInputStream().read(input);
                byte[] output = process(input);
                        //阻塞状态
                socket.getOutputStream().write(output);
            }catch(Exception e){

            }
        }

        private byte[] process(byte[]cmd){
            return null;
        }
    }

    //客户端阻塞的 原因
    // 1 client 建立连接 会阻塞  直到连接成功
    // 2 线程从socket 读入数据 没有足够数据 进入阻塞
    // 3 线程从soket 写入 直到输出完毕
    // 4 socket.setsolinger 设置socket 的延迟时间，当socket 关闭 进入阻塞 直到全部数据发送完成或者超时



    //基于事件驱动设计

}
