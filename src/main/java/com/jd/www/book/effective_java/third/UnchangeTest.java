package com.jd.www.book.effective_java.third;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 上午11:44</li>
 * <li>function:</li>
 * </ul>
 */
public class UnchangeTest {
    private final double re;
    private final double im;
    public UnchangeTest(double re,double im){
        this.re = re;
        this.im = im;
    }

    public double realP(){
        return re;
    }
    public double impP(){
        return im;
    }
    //加减
    public UnchangeTest addUn(UnchangeTest unchangeTest){
        return new UnchangeTest(re+unchangeTest.re,im+unchangeTest.im);
    }

    public UnchangeTest jianUn(UnchangeTest unchangeTest){
        return new UnchangeTest(re-unchangeTest.re,im-unchangeTest.im);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof UnchangeTest))
            return false;
        UnchangeTest unchangeTest = (UnchangeTest)obj;
        return Double.compare(re,unchangeTest.re) == 0  && Double.compare(im,unchangeTest.im) == 0 ;

    }

    @Override
    public int hashCode() {
       int result = 17;
        result = result*31 + hashCodeDouble(re);
        result = result * 31 + hashCodeDouble(im);
        return result;
    }

    private int hashCodeDouble(double val){
       long longBits = Double.doubleToLongBits(val);
        return (int)(longBits ^ (longBits >>> 32));
    }

    @Override
    public String toString() {
        return "UnchangeTest{" +
                "re=" + re +
                ", im=" + im +
                '}';
    }
}
