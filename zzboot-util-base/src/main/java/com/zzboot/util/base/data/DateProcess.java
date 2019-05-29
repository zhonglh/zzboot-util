package com.zzboot.util.base.data;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/15.
 */
public class DateProcess {

    /**
     * 获取每年的第一天月
     * @param date1
     * @return
     */
    public static Date getFirstDayOfYear(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.DAY_OF_YEAR, 1);
        cdate.set (Calendar.HOUR_OF_DAY, 0);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }

    /**
     * 获取每月的第一天月
     * @param date1
     * @return
     */
    public static Date getFirstDayOfMonth(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.DAY_OF_MONTH, 1);
        cdate.set (Calendar.HOUR_OF_DAY, 0);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }

    /**
     * 获取每周的第一天
     * @param date1
     * @return
     */
    public static Date getFirstDayOfWeek(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.DAY_OF_WEEK, 1);
        cdate.set (Calendar.HOUR_OF_DAY, 0);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }




    public static Date getFirstHour(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.HOUR_OF_DAY, 0);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }

    public static Date getFirstMinute(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }


    public static Date getFirstSecond(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }





    public static Date getFirst(Date date1){
        Calendar cdate = DateKit.dateToCalendar (date1);
        cdate.set (Calendar.HOUR_OF_DAY, 0);
        cdate.set (Calendar.MINUTE, 0);
        cdate.set (Calendar.SECOND, 0);
        cdate.set (Calendar.MILLISECOND, 0);
        return cdate.getTime ();
    }


    public static void main(String[] args) {
        System.out.println(DateKit.fmtDateToYMD(getFirst(new Date())));
    }


}
