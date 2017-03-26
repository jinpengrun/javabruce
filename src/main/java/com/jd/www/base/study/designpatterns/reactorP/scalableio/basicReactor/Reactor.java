package com.jd.www.base.study.designpatterns.reactorP.scalableio.basicReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhujinpeng on 16/3/9.
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel serverSocket;

    public Reactor(int port) throws Exception{
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false); //非阻塞
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT); //分步处理,第一步,接收accept事件
        sk.attach(new Acceptor()); //attach callback object, Acceptor
    }


    @Override
    public void run() {
        try{
        while (!Thread.interrupted()) {
            selector.select();
            Set selected = selector.selectedKeys();
            Iterator it = selected.iterator();
            while (it.hasNext())
                dispatch((SelectionKey)(it.next())); //Reactor负责dispatch收到的事件
            selected.clear();
        }
    } catch (IOException ex) { /* ... */ }

    }


    void dispatch(SelectionKey k) {
        Runnable r = (Runnable)(k.attachment()); //调用之前注册的callback对象
        if (r != null)
            r.run();
    }




     class Acceptor implements Runnable{
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null)
                    new Handler(selector, c);
            }
            catch(IOException ex) {
            }
        }
    }

}


