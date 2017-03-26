package com.jd.www.book.effective_java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/3 上午11:08</li>
 * <li>function:</li>
 * </ul>
 */
public class StaticFactory {
    public static <k,v> HashMap<k,v> newInstance(){
        return new HashMap<k,v>();
    }

    public static void main(String[] args) {
        Map<String,String> map = newInstance();


    }
    //服务提供者框架 三个组件 1 服务接口
    public interface Service{

    }
    // 2 服务提供者接口
    public interface Provider{
        Service newInstence();
    }


    public static class Services{
        private Services(){}

        private static  final Map<String,Provider> providers =
                new ConcurrentHashMap<String,Provider>();
        public static final String DEFAULT_PROVIDER_NAME = "<def>";
        // 提供者注册api
        public static void registerDefaultProvider(Provider p ){
            registerProvider(DEFAULT_PROVIDER_NAME,p);
        }

        public static void registerProvider(String name ,Provider p){
            providers.put(name,p);
        }
        //服务访问api
        public static Service newInstance(){
            return null;
        }

        public static Service newInstance(String name){
            Provider provider = providers.get(name);
            if(provider==null){
                throw new IllegalArgumentException("no register provider with name "+name);
            }
            return provider.newInstence();
        }

    }

    //静态工厂惯用名
    //valueOf   of getInstance newInstance getType newType
}
