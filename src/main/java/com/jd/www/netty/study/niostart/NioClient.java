package com.jd.www.netty.study.niostart;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by zhujinpeng on 2017/4/4.
 */
public class NioClient {
    public static void main(String[] args) throws Exception{
        //获取socket通道
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        //获得通道管理器
        Selector selector= Selector.open();
        channel.connect(new InetSocketAddress("localhost", 9090));
        //为该通道注册SelectionKey.OP_CONNECT事件
        channel.register(selector, SelectionKey.OP_CONNECT);


        while(true){
            //选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
            selector.select();
            for(SelectionKey key : selector.selectedKeys()){
                if(key.isConnectable()){
                    SocketChannel channelone=(SocketChannel)key.channel();
                    if(channelone.isConnectionPending()){
                        channelone.finishConnect();//如果正在连接，则完成连接
                    }
                    channelone.register(selector, SelectionKey.OP_READ);
                }else if(key.isReadable()){ //有可读数据事件。
                    SocketChannel channelone = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(10);
                    channelone.read(buffer);
                    byte[] data = buffer.array();
                    String message = new String(data);
                    System.out.println("recevie message from server:, size:"
                            + buffer.position() + " msg: " + message);
                }
            }
        }
    }
}
