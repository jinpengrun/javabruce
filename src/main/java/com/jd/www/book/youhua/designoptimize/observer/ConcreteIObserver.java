package com.jd.www.book.youhua.designoptimize.observer;

/**
 * Created by zhujinpeng on 2017/4/20.
 */
public class ConcreteIObserver implements IObserver {
    @Override
    public void update(Event event) {
        System.out.println("update.....");
    }
}
