package com.jd.www.book.effective_java.third.twentieth;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午8:22</li>
 * <li>function:</li>
 * </ul>
 *
 * 类层次优于标签类
 */
public class RightTagClass {
    public abstract class Figure{
        abstract double area();
    }

    class Cirle extends Figure{

        final double redius;

        public Cirle(double redius ){
            this.redius = redius;
        }

        @Override
        double area() {
            return redius*redius;
        }
    }

    class Reactagle extends Figure{

        final double length;
        final double weight;

        public Reactagle(double length,double weight){
            this.length = length;
            this.weight = weight;
        }

        @Override
        double area() {
            return length*weight;
        }
    }
}
