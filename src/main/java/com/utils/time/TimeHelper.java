/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.time;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mphj
 */
public class TimeHelper {

    public static final String SECOND = "s",
            MINUTE = "m",
            HOUR = "h",
            MILISECOND = "ms";
    public static final TimeZone currentTimezone = TimeZone.getTimeZone("Asia/Tehran");
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(Calendar.getInstance(currentTimezone).getTime().getTime());
    }
    
    
    public static Timestamp parseTimestamp(String string, String pattern){
        return new Timestamp(Long.parseLong(string.trim()));
    }

    public static long getCurrentTime() {
        return Calendar.getInstance(currentTimezone).getTime().getTime();
    }

    public static String getTimeUnitAsString(long time, TimeUnit timeUnit) {
        String result = String.valueOf(time);
        switch (timeUnit) {
            case SECONDS:
                result += SECOND;
                break;
            case MINUTES:
                result += MINUTE;
                break;
            case HOURS:
                result += HOUR;
                break;
            case MILLISECONDS:
                result += MILISECOND;
                break;
        }
        return result;
    }
}
