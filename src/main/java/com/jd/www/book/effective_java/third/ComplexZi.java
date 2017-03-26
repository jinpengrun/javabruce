package com.jd.www.book.effective_java.third;

import java.math.BigInteger;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午2:41</li>
 * <li>function:</li>
 * </ul>
 */
public class ComplexZi {

    public  volatile  int hashcode;
    public static BigInteger safeInstance(BigInteger bigInteger){
        if(bigInteger.getClass() != BigInteger.class)
            return new BigInteger(bigInteger.toByteArray());
        return bigInteger;
    }


    @Override
    public int hashCode() {
       int result = hashcode;
        if(result == 0 ){
            result = 17;
            hashcode = result;
        }
        return result;
    }
}
