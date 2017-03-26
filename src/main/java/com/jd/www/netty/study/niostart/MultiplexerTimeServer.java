package com.jd.www.netty.study.niostart;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhujinpeng on 15/12/7.
 * 多路复用服务端
 */
public class MultiplexerTimeServer implements  Runnable{
    //选择器
    private Selector selector;
    //通道 一个通道
    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;


    private int i=0;

    /**
     * 初始化多路复用器，绑定监听端口
     */


    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            /**绑定端口**/
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            /**注册选择器   key**/
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }


    @Override
    public void run() {
        while(!stop){
            try{
                //一个线程可以处理 多个 连接请求  多路复用
                //超时时间 可以选择超时时间
               selector.select();

                System.out.println("run----------"+i++);
                Set<SelectionKey>  selectionKeys = selector.selectedKeys();
                System.out.println("run**********"+i);

                //所有的selectionkey
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key  = null;

                //
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{

                        handleInput(key);
                    }catch(Exception e){
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //多路复用器关闭后，所有注册在上面的channel 和 pipe 等资源都会被自动
        //去注册并关闭，不需要重复去释放
        if(selector!=null){
            try{
                selector.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * handle 处理器 可以
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key )throws IOException{
        if(key.isValid()){
            //处理新接入的请求信息 如果是接收新的请求
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                //
                SocketChannel sc = ssc.accept();
                //设置为 非阻塞
                sc.configureBlocking(false);
                //一个ok 后 去注册另外一个事件
                sc.register(selector,SelectionKey.OP_READ);

            }
            //如果可以 读了
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel)key.channel();
                //可以分配 堆外内存  直接内存 bytebuffer 非 复用 分配 1kb
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //从sc 读出 数据到 readbuffer   从 网络缓冲区 到 堆内存  这一步
                int readBytes = sc.read(readBuffer);
                //如果读到了数据
                if(readBytes>0){
                    //现在需要读数据
                    readBuffer.flip();
                    //还有多少可以读
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //将数据从bytebuffer 到 bytes
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("the time server receive order:"+body);

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    //cancel
                    key.cancel();
                    //关闭这个 channel
                    sc.close();
                }else{
                    ;//读到0字节，忽略
                }
            }
        }

    }

    //给这个网络通道
    private void doWrite(SocketChannel channel, String response)throws IOException{
        //
        if(response!=null&&response.trim().length()>0){
            byte[] bytes = response.getBytes();
            //position=0 limit/capcity =length
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //写入后 position = limit/capcity
            writeBuffer.put(bytes);
            //position=0 limit/capcity = length
            writeBuffer.flip();

            channel.write(writeBuffer);
        }
    }


}
