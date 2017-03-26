package com.jd.www.book.effective_java.seven.thirtynine;

import java.util.Date;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/24 下午8:46</li>
 * <li>function:</li>
 * </ul>
 *
 * 必要时进行保护性拷贝
 */
public final class Period {
    private final Date start;
    private final Date end;

    public Period(Date start,Date end){
//        if(start.getTime() > end.getTime())
//            throw  new IllegalArgumentException("end must > start ");
        //this.start = start;
        //this.end = end;

        //进行保护性拷贝 正确的做法

        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        //拷贝完成之后再进行检查
        if(this.start.compareTo(end)>0){
            throw new IllegalArgumentException("end must > start");
        }

        //没有使用 date clone 的方法 对于参数类型可以被不可信任方子类化的参数，请不要使用clone 方法进行保护性拷贝

    }

    public Date start(){
        //防止第二种攻击
        return new Date(start.getTime());
        //return start;
    }


    public Date end(){
        return end;
    }

    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();

        Period period = new Period(start,end);


        //这是第一种攻击
        end.setTime(new Date().getTime()); // 仍然可以进行修改


        //这样对构造器的每个可变参数进行保护性拷贝是必要的


        //查看第二种攻击

        //仍然可以对该 属性进行 修改
        period.end().setTime(new Date().getTime());




    }

}
