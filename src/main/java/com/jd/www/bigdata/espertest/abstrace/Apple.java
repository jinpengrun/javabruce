package com.jd.www.bigdata.espertest.abstrace;

import com.espertech.esper.client.*;

/**
 * Created by zhujinpeng on 16/3/11.
 */
public class Apple {

    private  double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;




    static class AppleListener implements UpdateListener{

        @Override
        public void update(EventBean[] newEvents, EventBean[] oldEvents) {
            if(newEvents!=null){
                Double avg = (Double)newEvents[0].get("hello");
                Integer price = (Integer)newEvents[0].get("id");
                System.out.println("apple's average price is " + avg+"----and the id is "+price);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EPServiceProvider epServiceProvider = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epServiceProvider.getEPAdministrator();

        String product = Apple.class.getName();
        //次数4次
        String epl = "select sum(price) from "+ product +".win:length_batch(5) ";
        //时间 2秒内  平均的 价格是多少
        String epl_time = "select avg(price) from "+product+".win:time(2 sec)";
        //filter                                             length窗口 5             amount 属性
        String epl_filter = "select * from "+product+".win:length(5) where amount>200";


        //统计进入的5 个apple 事件，amount总数是多少//
        String epl_sum="select sum(amount) from Apple.win:length_batch(5)";

        //统计进入的5个 apple 事件，amount 总数是多少， 并按照price 进行分组

        // group  跟据id 进行 平均 单价的 计算。
        String epl_group = "select id,avg(price) as hello  from "+product+".win:time(2 sec)  group by id";




        EPStatement state = admin .createEPL(epl_group);


        state.addListener(new AppleListener());


        EPRuntime runtime = epServiceProvider.getEPRuntime();




        Apple apple1 = new Apple();
        apple1.setId(1);
        apple1.setPrice(5);
        runtime.sendEvent(apple1);

        //Thread.sleep(1000);

        Apple apple2 = new Apple();
        apple2.setId(2);
        apple2.setPrice(3);
        runtime.sendEvent(apple2);

        //Thread.sleep(1000);

        Apple apple3 = new Apple();
        apple3.setId(3);
        apple3.setPrice(50000);
        runtime.sendEvent(apple3);

        //Thread.sleep(1000);


        Apple apple4 = new Apple();
        apple4.setId(1);
        apple4.setPrice(50000);
        runtime.sendEvent(apple4);


        //Thread.sleep(2000);

        Apple apple5 = new Apple();
        apple5.setId(5);
        apple5.setPrice(50000);
        runtime.sendEvent(apple5);




        //Thread.sleep(1000);


        //Thread.sleep(40000);


        String ss = "select children[1],phones('home'),address.road from Person where name ='luonanqin'";

        ss = "update Person set phones('home')= 1232321 where name = 'luoqi'";

        //支持对象，  map  和 数组 ，xml

    }


}
