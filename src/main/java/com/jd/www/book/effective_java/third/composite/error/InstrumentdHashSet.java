package com.jd.www.book.effective_java.third.composite.error;

import java.util.Collection;
import java.util.HashSet;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午3:52</li>
 * <li>function:</li>
 * </ul>
 */
public class InstrumentdHashSet<E> extends HashSet<E> {
    private int addCount = 0;
    public InstrumentdHashSet(){}
    public InstrumentdHashSet(int initCap,float loadFactor){
        super(initCap,loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    //add all 后 addcount 会  双倍
    //因为super addall  会调用 子类的 add  加了两次
    //这种 addall self-use 很脆弱
    //这些问题来源就是 来源于覆盖 继承 存在此类问题
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount(){
        return addCount;
    }
}
