package com.jd.www.book.effective_java.third.apiread;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午6:06</li>
 * <li>function:</li>
 * </ul>
 */
public class S {
    private static final S ourInstance = new S();

    public static S getInstance() {
        return ourInstance;
    }

    private S() {
    }
}
