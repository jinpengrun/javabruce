package com.jd.www.netty.study.biostart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhujinpeng on 16/5/24.
 */
public class BackLogTest {



    public static void main(String[] args) throws Exception {


        final CountDownLatch countDownLatch = new CountDownLatch(1);
        //等待队列里 socket连接数
        int backlog = 2;
        final ServerSocket serversocket = new ServerSocket(10000, backlog);


        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            try {
                while (true) {

                      BufferedReader in = null;
                      PrintWriter out = null;


                    System.out.println("启动服务端......");
                    int i;

                    countDownLatch.countDown();
                    //阻塞

                    Socket socket = serversocket.accept();

                    System.out.println("有客户端连上服务端, 客户端信息如下：" + socket.getInetAddress() + " : " + socket.getPort() + ".");
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);
                    do {
                        char[] c = new char[1024];
                        i = in.read(c);
                        System.out.println("服务端收到信息: " + new String(c, 0, i));
                    } while (i == -1);
                    out.close();
                    in.close();
                    socket.close();
                    System.out.println("关闭服务端......");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        });

        t.start();

        countDownLatch.await();



        for(int i=0;i<5;i++){

            Thread thread = new Thread(new innerClass(i));


            thread.start();
        }













    }


    static class innerClass implements Runnable{
        int i= 0 ;
        public innerClass(int i){
            this.i = i;
        }

        @Override
        public void run() {
            try {
                System.out.println("开始连接：" + i);
                Socket socket = new Socket("127.0.0.1", 10000);
//                OutputStream out = socket.getOutputStream();
//                out.write("hello".getBytes());
//                out.flush();
                System.out.println("结束连接：" + i);
            }catch (Exception e){
                System.out.println("the index is "+i);
                e.printStackTrace();
            }
        }
    }
}
