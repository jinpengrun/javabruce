package com.jd.www.book.effective_java.third.twentysecond;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午9:41</li>
 * <li>function:</li>
 * </ul>
 */
public class NotStaticTest {
    private final String i;

    public NotStaticTest(String i){
        this.i = i;
    }
    public class Hello{
        public static final int cc = 10;
        public void println(){
            System.out.println("wode tiankong");
            test();
        }

    }

    private void test(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        NotStaticTest notStaticTest= new NotStaticTest("111");
        //非静态类必须与 外部对象实例绑定
        Hello hello = notStaticTest.new Hello();
        hello.println();

    }
}
