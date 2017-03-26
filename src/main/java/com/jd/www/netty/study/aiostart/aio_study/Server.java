package com.jd.www.netty.study.aiostart.aio_study;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by zhujinpeng on 16/5/23.
 */
public class Server {
    static int PORT = 8080;
    static int BUFFER_SIZE = 1024;
    static String CHARSET = "utf-8";

    static CharsetDecoder decoder = Charset.forName(CHARSET).newDecoder();

    int port;


    //内属性在 回调函数里操作
    AsynchronousServerSocketChannel serverSocketChannel;


    public Server(int port) throws IOException{
        this.port = port;
        //解码
        this.decoder = Charset.forName(CHARSET).newDecoder();
    }


    private void listen() throws Exception{
        //打开一个服务通道
        //绑定服务端口                                                                                    //等待的队列为 100 再来就要报错

        this.serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port),100);

                                        //
        this.serverSocketChannel.accept(this,new AcceptHandler());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //System.out.println("运行中。。。。");
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }




    /**
     * accept到一个请求时的回调
     */
    private class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Server> {

        //接收请求完成
        @Override
        public void completed(final AsynchronousSocketChannel client, Server attachment) {
            try {
                System.out.println("远程地址：" + client.getRemoteAddress());
                //tcp各项参数
                client.setOption(StandardSocketOptions.TCP_NODELAY, true);
                client.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
                client.setOption(StandardSocketOptions.SO_RCVBUF, 1024);

                if (client.isOpen()) {
                    System.out.println("client.isOpen：" + client.getRemoteAddress());
                    final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                    buffer.clear();
                    client.read(buffer, client, new ReadHandler(buffer));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                attachment.serverSocketChannel.accept(attachment, this);// 监听新的请求，递归调用。
            }
        }

        @Override
        public void failed(Throwable exc, Server attachment) {
            try {
                exc.printStackTrace();
            } finally {
                attachment.serverSocketChannel.accept(attachment, this);// 监听新的请求，递归调用。
            }
        }
    }

    private class ReadHandler implements CompletionHandler<Integer,AsynchronousSocketChannel>{

        private ByteBuffer buffer;

        public ReadHandler(ByteBuffer buffer){
            this.buffer = buffer;
        }


        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            try {
                if (result < 0) {// 客户端关闭了连接
                    System.out.println("客户端关闭了连接:"+attachment.getRemoteAddress());
                    Server.close(attachment);
                } else if (result == 0) {
                    System.out.println("空数据"); // 处理空数据
                } else {
                    // 读取请求，处理客户端发送的数据
                    buffer.flip();
                    CharBuffer charBuffer = Server.decoder.decode(buffer);
                    System.out.println(charBuffer.toString()); //接收请求

                    //响应操作，服务器响应结果
                    buffer.clear();
                    String res = "HTTP/1.1 200 OK" + "\r\n\r\n" + "hellworld";
                    buffer = ByteBuffer.wrap(res.getBytes());
                    attachment.write(buffer, attachment, new WriteHandler(buffer));//Response：响应。
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
            Server.close(attachment);
        }
    }




    /**
     * Write响应完请求的回调
     */
    private class WriteHandler implements CompletionHandler<Integer, AsynchronousSocketChannel> {
        private ByteBuffer buffer;

        public WriteHandler(ByteBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void completed(Integer result, AsynchronousSocketChannel attachment) {
            buffer.clear();
            Server.close(attachment);
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
            exc.printStackTrace();
            Server.close(attachment);
        }
    }


    private static void close(AsynchronousSocketChannel client) {
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            System.out.println("正在启动服务...");
            Server server = new Server(PORT);
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
