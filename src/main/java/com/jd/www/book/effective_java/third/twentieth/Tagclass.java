package com.jd.www.book.effective_java.third.twentieth;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午8:14</li>
 * <li>function:</li>
 * </ul>
 * 标签类缺点
 * 标签类过于冗长 容易出错 效率低下
 */
public class Tagclass {
    enum Shape{yuan,bian}
    final Shape shape;

    double length;
    double weight;

    double redis;
    public Tagclass(double length,double weight){
        this.length = length;
        this.weight = weight;
        shape = Shape.bian;
    }


    public Tagclass(double redis){
        this.redis = redis;
        shape = Shape.yuan;
    }

    double area(){
        switch (shape){
            case bian:
                return length*weight;
            case yuan:
                return redis;
            default:
                throw  new AssertionError("not right");
        }
    }





}
