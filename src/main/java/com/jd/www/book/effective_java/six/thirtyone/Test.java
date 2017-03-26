package com.jd.www.book.effective_java.six.thirtyone;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/22 下午2:28</li>
 * <li>function:</li>
 * </ul>
 */
public class Test {
    //这样如果重新排序 顺序将会乱掉
    public enum Ensemble{

        SOLO,DUET,TRIO,QUARTET,QUINTET,SEXTET;

        public int getordi(){
            return ordinal()+1;
        }
    }


    public enum Ensebeltest{
        SOLO(1),DUEB(2),TRIO(3);

        private final int numberOfMusicians;

        Ensebeltest(int size){
            this.numberOfMusicians = size;
        }


        public int getNumberOfMusicians(){
            return numberOfMusicians;
        }
    }
    public static void main(String[] args) {
        System.out.println(Ensemble.DUET.getordi());
        //不依赖 ordinal 方法
        System.out.println(Ensebeltest.DUEB.getNumberOfMusicians());

    }
}
