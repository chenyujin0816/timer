package com.example.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
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

}
