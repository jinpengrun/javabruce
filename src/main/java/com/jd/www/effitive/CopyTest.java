package com.jd.www.effitive;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/16 下午1:23</li>
 * <li>function:</li>
 * </ul>
 */
public class CopyTest {

    public static class M2{
        double h;
        String a;
       int b;

        public double getH() {
            return h;
        }

        public void setH(double h) {
            this.h = h;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public int  getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public float getC() {
            return c;
        }

        public void setC(float c) {
            this.c = c;
        }

        float c;


        @Override
        public String toString() {
            return "M2{" +
                    "h=" + h +
                    ", a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    ", c=" + c +
                    '}';
        }
    }

    public static class M1{
        double h;
        String a;
        int   b;
        float c;

        public double getH() {
            return h;
        }

        public void setH(double h) {
            this.h = h;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public float getC() {
            return c;
        }

        public void setC(float c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "M1{" +
                    "h=" + h +
                    ", a='" + a + '\'' +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }


    public static  void testBeanUtils(){
        long start = System.currentTimeMillis();
        M1 m1 = new M1();
        m1.setA("1");
        m1.setB(1);m1.setC(1.0f);m1.setH(1.1d);
        System.out.println(m1);

        for(int i=0;i<3;i++){
            M2 m2 = new M2();
            try {
                BeanUtils.copyProperties(m2,m1);
                System.out.println(m2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }


    public static void testPropertyUtils(){
        long start = System.currentTimeMillis();
        M2 m1 = new M2();
        m1.setA("1");
        m1.setB(1);m1.setC(1.0f);m1.setH(1.1d);

        for(int i=0;i<3;i++){
            M2 m2 = new M2();

            try {
                PropertyUtils.copyProperties(m2, m1);
                System.out.println(m2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        System.out.println(System.currentTimeMillis()-start);
    }


    public static void main(String[] args) {
        testBeanUtils();
        testPropertyUtils();
    }
}
