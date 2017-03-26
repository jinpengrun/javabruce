package com.jd.www.bigdata.espertest.context.EventType;

import java.util.List;

/**
 * Created by zhujinpeng on 16/3/28.
 */
public class PersonPojo {
    String name;
    int age;
    List<Child> childList;
    Address address;

    /**
     * get方法
     * @return
     */
    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }





    static class Child{
        String name;
        int gender;
    }


    static class Address{
        String road;
        String street;
        int houseNo;
    }

    //所示epl语句如下
    //当person 类型的时间中 name 是 luonanqin 时候 esper 得到相应的age children 和 address
    //select age,children,address from person where name="luonanqin"
           //拿到 第二个
    //select children[1] ,phones("home"),address.road where Person where name ="luonanqin"

    //update person set phones('home') = 12121212 where name = "luonanqin"


 }
