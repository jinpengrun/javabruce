package com.jd.www.netty.study.biostart;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;

/**
 * Created by zhujinpeng on 2017/3/31.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        //
        ServerSocket serverSocket = new ServerSocket(9999,1);
        //serverSocket.setReceiveBufferSize(10000);

        while(true){
            Socket socket = serverSocket.accept();
            //socket.setReceiveBufferSize(100000);
            System.out.println("client:"+socket.getInetAddress().getHostName());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            BufferedWriter pw = new BufferedWriter(new PrintWriter(socket.getOutputStream(),true));

            System.out.println(socket.getReceiveBufferSize());
            System.out.println(socket.getSendBufferSize());
            String readline = null;
//            while((readline=bufferedReader.readLine())!=null){
//                System.out.println("your input is : "+readline);
//                if("close".equals(readline)){
//                    try {
//                        socket.close();
//                    }catch (Exception e){
//
//                    }
//                }
//            }
            //不读取

            //不读取数据 直接 睡眠  数据 在内核缓冲区内
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("开始从内核缓冲区拿数据。。。。。");
            while((readline=bufferedReader.readLine())!=null){
                System.out.println("your input is : "+readline);
                if("close".equals(readline)){
                    try {

                        pw.close();
                        //bufferedReader.close();
                        break;
                    }catch (Exception e){

                    }
                }
            }
        }
    }
}
