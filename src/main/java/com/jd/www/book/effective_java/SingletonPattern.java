package com.jd.www.book.effective_java;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/3 下午3:10</li>
 * <li>function:</li>
 * </ul>
 */
public class SingletonPattern {

    public static class Evis{
        private Evis(){}
        private static final Evis evis = new Evis();
        public static Evis getInstance(){
            return evis;
        }


    }

    /**
     * after 1.5
     */
    public enum Evisv{
        INSTANCE;

        public void test(){};
    }
}
