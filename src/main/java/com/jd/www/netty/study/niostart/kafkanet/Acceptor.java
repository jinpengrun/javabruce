package com.jd.www.netty.study.niostart.kafkanet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/1/20 下午3:31</li>
 * <li>function:</li>
 * </ul>
 * 这个线程只负责 处理 新建连接
 */
public class Acceptor extends AbstractServerThread {

    private int port;
    //private Processor[] processors;
    //发送buffer 大小
    private int sendBufferSize;
    // 接收buffer 大小
    private int receiveBufferSize;


    public Acceptor(int port, int sendBufferSize, int receiveBufferSize) {
        super();
        this.port = port;
        this.sendBufferSize = sendBufferSize;
        this.receiveBufferSize = receiveBufferSize;
    }

    @Override
    public void run() {
         ServerSocketChannel serverSocketChannel = null;
        try{
           serverSocketChannel = ServerSocketChannel.open();
           serverSocketChannel.configureBlocking(false);
           serverSocketChannel.socket().bind(new InetSocketAddress(port));
           serverSocketChannel.register(getSelector(), SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }

        startupComplete();

        int currentProcessor = 0;
        while(isRunning()){
            int ready = -1;

            try {
                ready = getSelector().select(500);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(ready<=0){
                continue;
            }

            Iterator<SelectionKey> iter = getSelector().selectedKeys().iterator();
            while(iter.hasNext()&&isRunning()){
                SelectionKey key = iter.next();
                iter.remove();
                if(key.isAcceptable()){
                    //accept(key,processors[currentProcessor])
                }else{
                    throw new IllegalStateException("unrecongnized key state for acceptor thread");
                }

                //currentProcessor = (currentProcessor + 1) % processors.length;
            }

        }

        try {
            serverSocketChannel.close();
            getSelector().close();
            shutdownComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
