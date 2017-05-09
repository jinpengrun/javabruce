package com.jd.www.book.youhua.designoptimize.observer;

import java.util.Vector;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public class ConcreteSubject implements ISubject {
    Vector<IObserver> iObservers = new Vector<>();


    @Override
    public void addAttach(IObserver iObserver) {
        iObservers.add(iObserver);
    }

    @Override
    public void deleteAttach(IObserver iObserver) {
        iObservers.remove(iObserver);
    }

    @Override
    public void infirm() {
        Event event = new Event();
        iObservers.forEach(a->{
            a.update(event);
        });
    }
}
