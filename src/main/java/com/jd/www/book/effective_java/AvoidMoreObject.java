package com.jd.www.book.effective_java;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/11/3 下午3:46</li>
 * <li>function:</li>
 * </ul>
 */
public class AvoidMoreObject {
    public static class person{
        private final Date birthDate;

        //cuowu
        public boolean isBabyBoomer(){
            Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT"));
             calendar.set(1946,Calendar.JANUARY,1,0,0,0);
            Date datestart = calendar.getTime();
            calendar.set(1958,Calendar.JANUARY,1,0,0,0);
            Date dateend = calendar.getTime();
            return birthDate.compareTo(datestart)>=0 && birthDate.compareTo(dateend)<0;
        }

        private final static Date BOOMER_START;
        private final static Date BOOMER_END;

        //effective
        static{
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.set(1946,Calendar.JANUARY,1,0,0,0);
            BOOMER_START = calendar.getTime();

            calendar.set(1958,Calendar.JANUARY,1,0,0,0);

            BOOMER_END  = calendar.getTime();
        }



        public person(Date date){
            this.birthDate = date;
        }
    }
}
