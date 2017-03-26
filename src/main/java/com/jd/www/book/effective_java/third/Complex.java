package com.jd.www.book.effective_java.third;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午2:38</li>
 * <li>function:</li>
 * </ul>
 */
public class Complex {
    private double re;
    private float im;

    private Complex(double re,float im){
        this.re = re;
        this.im = im;
    }


    public static final Complex valueOf(double re,float im){
        return new Complex(re,im);
    }

}
