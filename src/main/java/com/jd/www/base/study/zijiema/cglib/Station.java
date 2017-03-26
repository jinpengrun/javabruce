package com.jd.www.base.study.zijiema.cglib;

/**
 * Created by zhujinpeng on 16/1/12.
 */
public class Station implements TicketService{
    @Override
    public void sellTicket() {
        System.out.println("正在卖票......");
    }

    @Override
    public void inquire() {
        System.out.println("正在问询.......");
    }

    @Override
    public void withdraw() {
        System.out.println("正在退票......");
    }

    @Override
    public void changeTicket() {
        System.out.println("正在改签......");
    }
}
