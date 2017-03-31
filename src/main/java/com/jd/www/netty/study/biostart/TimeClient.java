package com.jd.www.netty.study.biostart;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhujinpeng on 2017/3/31.
 */
public class TimeClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",9999);

        BufferedWriter  pw = new BufferedWriter(new PrintWriter(client.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readline =null;
        while((readline=bufferedReader.readLine())!=null){
            System.out.println(readline);
            pw.write(readline);
            pw.write("\n");
            pw.flush();
        }

        pw.close();

    }
}
