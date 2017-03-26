package com.jd.www.book.effective_java.third.twentyfirst;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午8:42</li>
 * <li>function:</li>
 * </ul>
 * 函数对象表示策略
 */
public class StringtoLengthCompator {
    //具体策略  不包含 任何域  可以使用 单例
    public int compare(String i,String b){
        return i.length()-b.length();
    }

    public static final StringtoLengthCompator stringtoLengthCompator = new StringtoLengthCompator();

    private StringtoLengthCompator(){}

    //这样并不好 我们 应该创建个策略接口
    public interface Comparator<T>{
        public int compare(String i,String b);
    }

    //具体策略类 经常使用 匿名类声明

    public void test(){

        //这样意味着每次访问就会 创建一个类  我们可以 建立一个私有的 静态的 类 来保存
        Arrays.sort(new String[]{"1", "222", "42", "232323"}, new java.util.Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
    }



    private static final Comparator stringbidui = new Comparator() {
        @Override
        public int compare(String i, String b) {
            return i.length() - b.length();
        }
    };

}
