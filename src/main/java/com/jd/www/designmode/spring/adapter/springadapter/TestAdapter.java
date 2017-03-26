package com.jd.www.designmode.spring.adapter.springadapter;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/20 下午3:06</li>
 * <li>function:</li>
 * </ul>
 *
 *
 * target
 */
public interface TestAdapter {
    boolean isSupport(TestAdvice testAdvice);
    void test();
}
