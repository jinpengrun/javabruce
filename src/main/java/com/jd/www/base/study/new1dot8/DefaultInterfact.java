package com.jd.www.base.study.new1dot8;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/26 下午4:58</li>
 * <li>function:</li>
 * </ul>
 */
public class DefaultInterfact {
    public interface Formula{
        double calculate(int a);


        //可以有静态方法， 可以有  default 方法
         default double sqrt(int a){
            return Math.sqrt(a);
        }
    }

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(10));
    }
}
