package com.jd.www.book.principles_practice_distributed_framework.simplerpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/10/28 上午11:53</li>
 * <li>function:</li>
 * </ul>
 */
public class Main {


    //rpc核心1 服务提供者以某种形式提供服务提供相关调用信息
    public interface  EchoService{
       public String echo(String ping);
    }

    public static class EchoServiceImpl implements EchoService{

        public EchoServiceImpl(){

        }

        @Override
        public String echo(String ping) {
            return ping !=null ? ping+" i am ok..." : "your ping is null";
        }

    }





    //rpc核心3 通信
    public static class RpcExporter{
        static Executor executor = Executors.newFixedThreadPool(10);

        public static void exporter(String hostName,int port)throws Exception
        {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(hostName,port));
            try{
                while(true){
                    //executor.execute();
                    executor.execute(new ExporterTask(serverSocket.accept()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static class ExporterTask implements  Runnable{

        Socket clinet = null;

        public ExporterTask(Socket client){
            this.clinet = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;

            try{
                input = new ObjectInputStream(clinet.getInputStream());
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);

                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[])input.readObject();

                Object[] arguments = (Object[])input.readObject();
                Method method = service.getMethod(methodName,parameterTypes);

                Object result = method.invoke(service.newInstance(),arguments);

                output = new ObjectOutputStream(clinet.getOutputStream());

                output.writeObject(result);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(input!=null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(output!=null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if(clinet!=null){
                    try {
                        clinet.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
