package com.jd.www.book.effective_java.third.twentynine;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/21 下午5:09</li>
 * <li>function:</li>
 * </ul>
 * 类的类型从字面上来看不再只是简单的class 而是Class<T>
 *     String.class 属于Class<String> Integer.class Class<Integer>类型
 * 当一个类的字面文字被用在方法中，来传达编译时和运行时的类型信息时，被称为 type token
 *
 * favorites 实例是类型安全的  当你向他
 */
public class Favorites {

    // 通配符类型是嵌套的， 他不属于通配符类型的map的类型，而是他的键的类型
    // 由此可见，每个键都可以有一个不同的参数化类型，一个可以是Class<String> , 一个是Class<Integer>

    //值类型是Object 换句话说 map不能保证键和 值之间的类型关系，即不能保证每个值的类型都与键类型相同。
    //
    private Map<Class<?>,Object> favorites = Maps.newHashMap();


    //
    public <T> void putFavorites(Class<T> type,T instance){
        if(type == null)
            throw new NullPointerException("type is null");

        favorites.put(type,instance);
        //可以加一个动态转换 以确保 正确
        favorites.put(type,type.cast(instance));
    }

    public <T> T getFavorites(Class<T> type){
        //先从favorites映射 中获得与指定class 对象相对应 值，Class 的 cast 方法，将对应引用动态转换
        //
        return type.cast(favorites.get(type));
    }


    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorites(String.class,"java");
        f.putFavorites(Integer.class,0xcafebabe);
        f.putFavorites(Class.class,Favorites.class);




        String favoriteString = f.getFavorites(String.class);
        int favoriteInteger = f.getFavorites(Integer.class);

        Class<?> favoriteClass = f.getFavorites(Class.class);


    }

}
