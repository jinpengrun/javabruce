package com.jd.www.jdk_eight.defaultmethod;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/14 下午2:25</li>
 * <li>function:</li>
 * </ul>
 */
public interface TimeClient {
    void setTime(int hour,int minute,int second);
    void setDate(int day,int month,int year);
    void setDateAndTime(int day,int month,int year,int hour,int minute,int second);
    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString){
        try {
            return ZoneId.of(zoneString);
        }catch (DateTimeException e){
            System.err.println("invalid time zone:"+zoneString+"; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }


    default ZonedDateTime getZoneDateTime(String zoneString){
        return ZonedDateTime.of(getLocalDateTime(),getZoneId(zoneString));
    }
}
