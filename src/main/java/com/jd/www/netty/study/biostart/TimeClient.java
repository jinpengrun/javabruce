package com.jd.www.netty.study.biostart;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhujinpeng on 2017/3/31.
 */
public class TimeClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket client = new Socket("localhost",9999);
        client.setTcpNoDelay(false);

        System.out.println(client.getReceiveBufferSize());
        System.out.println(client.getSendBufferSize());



        BufferedWriter  pw = new BufferedWriter(new PrintWriter(client.getOutputStream(),true));


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readline =null;
        while((readline=bufferedReader.readLine())!=null){
            System.out.println(readline.length());

            pw.write(readline);
            pw.write("\n");

            pw.flush();
            if("close".equals(readline)){
                break;
            }
        }


        //Thread.sleep(10000);

        bufferedReader.close();

        Thread.sleep(10000000);

        //pw.close();

        //client.close();


       // Thread.sleep(10000);


    }
}
