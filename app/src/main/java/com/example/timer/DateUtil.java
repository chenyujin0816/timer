package com.example.timer;

import android.icu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String stdDatePattern="yyyy-MM-dd";
    public static final String stdTimePattern="HH-mm";

    public static long getCurTimeLong(){
        long time=System.currentTimeMillis();
        return time;
    }

    public static String getCurTime(String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        return format.format(getCurTimeLong());
    }

    public static String getDateToString(long milSecond,String pattern){
        Date date=new Date(milSecond);
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static long getStringToDate(String dataString,String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        Date date=new Date();
        try{
            date=format.parse(dataString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getWeek(long time){
        Calendar cd=Calendar.getInstance();
        cd.setTime(new Date(time));

        int week=cd.get(Calendar.DAY_OF_WEEK);
        String weekString="";
        switch (week){
            case Calendar.SUNDAY:
                weekString="周日";
                break;
            case Calendar.SATURDAY:
                weekString="周六";
                break;
            case Calendar.FRIDAY:
                weekString="周五";
                break;
            case Calendar.THURSDAY:
                weekString="周四";
                break;
            case Calendar.WEDNESDAY:
                weekString="周三";
                break;
            case Calendar.TUESDAY:
                weekString="周二";
                break;
            case Calendar.MONDAY:
                weekString="周一";
                break;
        }
        return weekString;
    }

}
