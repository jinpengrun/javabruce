package com.jd.www.book.principles_practice_distributed_framework.simplerpc;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/10/28 下午2:21</li>
 * <li>function:</li>
 * </ul>
 */
public class RpcImporter<S> {
    //本地服务代理
    public S importer(final Class<?> serviceClass,final InetSocketAddress address){
       return (S)Proxy.newProxyInstance(serviceClass.getClassLoader(),new Class<?>[] {serviceClass.getInterfaces()[0]},new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                try{
                   socket = new Socket();
                    socket.connect(address);
                    output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeUTF(serviceClass.getName());
                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);

                    input = new ObjectInputStream(socket.getInputStream());

                    return input.readObject();
                }finally {
                    if(input!=null){
                        input.close();
                    }
                    if(output!=null){
                        output.close();
                    }
                    if(socket!=null){
                        socket.close();
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //启动服务
                try {
                    Main.RpcExporter.exporter("localhost",8088);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();




        RpcImporter<Main.EchoService> importer = new RpcImporter<>();

        Main.EchoService echoService =  importer.importer(Main.EchoServiceImpl.class,new InetSocketAddress("localhost",8088));


        System.out.println(echoService.echo(null));
    }
}
