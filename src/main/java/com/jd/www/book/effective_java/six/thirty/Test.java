package com.jd.www.book.effective_java.six.thirty;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/22 上午11:22</li>
 * <li>function:</li>
 * </ul>
 */
public class Test {
    //int枚举模式，类型安全性和使用方便性方面没有任何帮助。
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;


    //JAVA 枚举本质上是int 值。
    public enum Apple{FUJI,PIPPIN,GRANNY_SIMITH}
    public enum Orange{NEVEL,TEMPLE,BLOOD}



    //枚举结合方法 枚举天生是 final 的

    public enum  Planet{
        MERCURY(3.302e+23,2.439e6),
        VENUS(3.32e+23,6.032E3);

        private final double mass;
        private final double radius;

        private final double surfaceGravity;

        Planet(double mass,double radius){
            this.mass = mass;
            this.radius = radius;
            surfaceGravity = mass*radius;
        }

        public double mass(){return  mass;}
        public double radius(){return radius;}


    }


    //糟糕的是：脆弱，你添加了新的枚举类型，忘记给switch 添加相应的条件
    public enum Operation{
        PLUS,MINUS,TIMES,DIVIDE;

        double apply(double x,double y){
            switch (this){
                case PLUS:return x+y;
                case MINUS:return x-y;
                case TIMES:return x*y;
                case DIVIDE:return x/y;
            }
            throw new AssertionError("Unknown op:"+this);
        }
    }


    //更好的 解决办法

    public enum Oprationtest{
        PLUS{double apply(double x,double y){return x+y;}},
        MINUX{double apply(double x,double y){return x-y;}},
        TIMES{double apply(double x,double y){return x*y;}},
        DIVIDE{double apply(double x,double y){return x/y;}};


        abstract double apply(double x,double y);
    }

    public enum Operationtest1{
        PLUS("+"){double apply(double x,double y){return x+y;}};
        private final String symbol;
        Operationtest1(String symbol){this.symbol = symbol;}
        @Override
        public String toString(){return symbol;}
        abstract  double apply(double x,double y);
    }

    //策略枚举 更高级的

//    public enum PayrollDay{
//        MONDAY(PayType.WEEDAY);
//
//        private final PayType payType;
//
//        PayrollDay(PayType payType){this.payType = payType;}
//
//        private enum PayType{
//            private static final  int HOURS_PER_SHIRT = 8;
//            WEEKDAY{
//                double overtimePay(double hours,double payRate){
//                    return hours <= HOURS_PER_SHIRT ? 0:(hours - HOURS_PER_SHIRT)*payRate/2;
//                }
//            };
//        }
//    }

    public static void main(String[] args) {
        double earthWeight = 2.3333d;
        double mass =earthWeight / Planet.MERCURY.surfaceGravity;
        for(Planet p : Planet.values()){
            System.out.println(p.mass()+"----"+p.radius);
        }

        double x = 1.2d;
        double y = 1.3d;
        for(Operationtest1 op:Operationtest1.values()){
            System.out.println(x+"--"+op+"---"+y+"---"+op.apply(x,y));
        }
    }

}
