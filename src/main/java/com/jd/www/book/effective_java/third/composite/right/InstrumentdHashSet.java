package com.jd.www.book.effective_java.third.composite.right;

import java.util.Collection;
import java.util.Set;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午5:20</li>
 * <li>function:</li>
 * </ul>
 *
 * 这各类被成为 wrapclass 包装类
 * 有时候 符合 和 转发 结合被成为 委托，技术角度而言不是委托 delegation
 * 委托是包装对象把自身传递给被包装对象
 * 包装类不适合用在回调框架。
 *
 *
 */
public class InstrumentdHashSet<E> extends ForwardHashSet<E> {
    private int addCount = 0;
    public InstrumentdHashSet(Set<E> set) {
        super(set);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount+=c.size();
        return super.addAll(c);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

}
