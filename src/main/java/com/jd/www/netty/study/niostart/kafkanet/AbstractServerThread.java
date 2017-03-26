package com.jd.www.netty.study.niostart.kafkanet;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/1/20 下午3:06</li>
 * <li>function:</li>
 * </ul>
 */
public abstract class AbstractServerThread implements Runnable,Closeable {

    private Selector selector;
    protected final CountDownLatch startupLatch = new CountDownLatch(1);
    protected final CountDownLatch shutdownLatch = new CountDownLatch(1);
    protected final AtomicBoolean alive = new AtomicBoolean(false);


    public Selector getSelector(){
        if(selector == null){
            try{
                selector = Selector.open();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }

        return selector;
    }


    protected void closeSelector(){
        //Closer.closeQuietly(selector, logger);
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void close(){
        alive.set(false);
        //
        selector.wakeup();
        try{
            shutdownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    protected void startupComplete(){
        alive.set(true);
        startupLatch.countDown();
    }


    protected void shutdownComplete(){
        shutdownLatch.countDown();
    }

    protected boolean isRunning(){
        return alive.get();
    }

    public void awaitStartup()throws InterruptedException{
        startupLatch.await();
    }

}
