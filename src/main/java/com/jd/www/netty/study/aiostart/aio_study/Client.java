package com.jd.www.netty.study.aiostart.aio_study;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhujinpeng on 16/5/24.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("00".getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
    }
}
