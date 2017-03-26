package com.jd.www.jdk_eight.defaultmethod;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 下午2:14</li>
 * <li>function:</li>
 * </ul>
 */
public class DefaultMethod {
    interface DefaultTest{
        int test1();
        default void test2(){
            System.out.println("default test2 method : hello world");
        }
    }

    static class DefaultImplementTest implements DefaultTest{

        @Override
        public int test1() {
            System.out.println("test1 method : hello world");
            return 0;
        }


    }

    public static void main(String[] args) {
        DefaultTest defaultTest = new DefaultImplementTest();
        defaultTest.test1();
        defaultTest.test2();
    }
}
