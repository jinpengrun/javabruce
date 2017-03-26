package com.jd.www.base.study.zijiema.cglib.fastclass;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class Test10 {


    public static void main(String[] args){

        Test1 tt = new Test1();
        Test2 fc = new Test2();

        int index = fc.getIndex("f()V");
        fc.invoke(index, tt, null);
    }

    static class Test1{
        public void f(){
            System.out.println("f method");
        }

        public void g(){
            System.out.println("g method");
        }
    }
// test2 是test 的fastclass 在getindex方法中对test1 每个方法建立索引，并根据入参（方法名+方法描述符）
    //来放回相应的索引。invoke 根据指定的索引，以ol为入参调用对象o的方法。这样就避免了反射调用，提高了
    //效率，
    static class Test2{
        public Object invoke(int index, Object o, Object[] ol){
            Test1 t = (Test1) o;
            switch(index){
                case 1:
                    t.f();
                    return null;
                case 2:
                    t.g();
                    return null;
            }
            return null;
        }
        public int getIndex(String signature){
        switch(signature.hashCode()){
            case 3078479:
                return 1;
            case 3108270:
                return 2;
        }
        return -1;
    }
    }
}
