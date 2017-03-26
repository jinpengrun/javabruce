package com.jd.www.jdk_eight.typetuiduan;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 下午1:38</li>
 * <li>function:</li>
 * </ul>
 */
public class Value<T> {

    public static<T> T defaultValue(){
        return null;
    }


    public T getOrDefault(T value,T defaultValue){
        return (value != null) ? value:defaultValue;
    }



    // Value.defaultValue  由编译器推导得出，不需要显shi 指明， java 7 这段代码会有编译错误， 需要使用 Value.<String>defaultValue()
    public static void main(String[] args) {
        final Value<String> value = new Value<>();

        value.getOrDefault("22",Value.<String>defaultValue());
    }

}
