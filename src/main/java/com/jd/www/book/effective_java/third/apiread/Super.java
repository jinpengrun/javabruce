package com.jd.www.book.effective_java.third.apiread;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午6:05</li>
 * <li>function:</li>
 * </ul>
 */
public class Super {
    public Super(){
        overrideMe();
    }

    public void overrideMe(){
        System.out.println("super class method");
    }
}
