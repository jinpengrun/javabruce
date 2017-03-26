package com.jd.www.designmode.spring.strategy;

/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/20 下午3:52</li>
 * <li>function:</li>
 * </ul>
 *
 *  代理接口
 *
 *
 */
public interface BruceProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
