package com.jd.www.netty.study.niostart;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhujinpeng on 2017/4/4.
 */
public class NioServer {

    public static void main(String[] args) throws Exception {
        //获取一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(9090));
        //获取通道管理器
        Selector selector = Selector.open();
        //将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);





        while(true){
            //当有注册的事件到达时，方法返回，否则阻塞。
            selector.select();
            for(SelectionKey key : selector.selectedKeys()){
                if(key.isAcceptable()){
                    ServerSocketChannel server =
                            (ServerSocketChannel)key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(
                            new String("send message to client").getBytes()));
                    //在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
                    channel.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){//有可读数据事件
                    SocketChannel channel = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    int read = channel.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    System.out.println("receive message from client, size:"
                            + buffer.position() + " msg: " + message);
                }
            }
        }
    }

}
