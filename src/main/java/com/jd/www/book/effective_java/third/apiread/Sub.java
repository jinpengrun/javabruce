package com.jd.www.book.effective_java.third.apiread;


import java.util.Date;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午6:06</li>
 * <li>function:</li>
 * </ul>
 */
public class Sub extends Super {
    private final Date date;
    public Sub(Date date){
        this.date = date;
    }

    @Override
    public void overrideMe() {
        System.out.println(date);
    }

    public static void main(String[] args) {
        Sub sub = new Sub(new Date());
        sub.overrideMe();
    }
}
