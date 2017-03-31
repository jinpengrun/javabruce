package com.jd.www.netty.study.biostart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        serverSocket.setSoTimeout(5000);

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("client:"+socket.getInetAddress().getHostName());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readline = null;
            while((readline=bufferedReader.readLine())!=null){
                System.out.println("your input is : "+readline);
                if("close".equals(readline)){
                    try {
                        socket.close();
                    }catch (Exception e){

                    }
                }
            }
        }
    }
}
