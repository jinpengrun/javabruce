package com.jd.www.book.effective_java.third.tenth;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/17 下午3:27</li>
 * <li>function:</li>
 * </ul>
 */
public final class ReadObjectTest implements Serializable{
    private final Date start;
    private final Date end;

    public ReadObjectTest(Date start,Date end){
        this.start = start;
        this.end = end;
        if(start.compareTo(end)>0){
            throw  new IllegalArgumentException(start +" must after "+end);
        }
    }

    public Date start(){
        return new Date(start.getTime());
    }

    public Date end(){
        return new Date(end.getTime());
    }

    @Override
    public String toString() {
        return "ReadObjectTest{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }


}
