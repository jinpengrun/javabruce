package com.jd.www.designmode.spring.adapter.springadapter;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/20 下午3:08</li>
 * <li>function:</li>
 * </ul>
 *
 * 适配器
 */
public class TestMethodBeforeAdapter implements TestAdapter {

    //适配方法
    @Override
    public boolean isSupport(TestAdvice testAdvice) {
        //适配方法
        return (testAdvice instanceof TestBeforeAdvice);
    }

    @Override
    public void test() {
        //这里也可以 适配方法
    }
}
