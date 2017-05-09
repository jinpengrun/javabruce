package com.jd.www.book.youhua.designoptimize.observer;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public interface ISubject {
    void addAttach(IObserver iObserver);
    void deleteAttach(IObserver iObserver);
    void infirm();
}
