package com.zzboot.util.base.data;


import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateKit extends DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM",
            "yyyy年MM月dd日", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月",
            "yyyy"};

    /**
     * 日期型字符串转化为日期 格式   see to DateUtils#parsePatterns
     */
    public static Date parseDate(String str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }




    /**
     * 计算当前日期和目标日期  相差的天数
     * @return
     */
    public static int getIntervalDays(Date targetDate){

        long intervalMilli = System.currentTimeMillis() - targetDate.getTime();
        int days =  (int) (intervalMilli / (24 * 60 * 60 * 1000));
        return days + 1;
    }

    /**
     * 获取距离目标日期天数前缀
     * @return
     */
    public static String getPrefix(){
        return fmtDateToStr (new Date(), "yyyyMMdd");
    }


    /**
     * 是否同一天
     * @param date
     * @return
     */
    public static boolean isSameDayWithToday(Date date) {

        if (date == null) {
            return false;
        }

        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        todayCal.setTime(new Date());
        dateCal.setTime(date);
        int subYear = todayCal.get(Calendar.YEAR) - dateCal.get(Calendar.YEAR);
        int subMouth = todayCal.get(Calendar.MONTH) - dateCal.get(Calendar.MONTH);
        int subDay = todayCal.get(Calendar.DAY_OF_MONTH) - dateCal.get(Calendar.DAY_OF_MONTH);
        if (subYear == 0 && subMouth == 0 && subDay == 0) {
            return true;
        }
        return false;
    }

    /**
     * 将字符串转化为DATE
     * 
     * @param date
     *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd或 yyyy-M-dd或 yyyy-M-d或 yyyy-MM-d或 yyyy-M-dd
     * @return
     */
    public static Date fmtStrToDate(String date){
        if (date == null || date.equals ("")) { return null; }
        try {
            if (date.length () == 9 || date.length () == 8) {
                String[] dateStr = date.split ("-");
                date = dateStr[0] + (dateStr[1].length () == 1 ? "-0" : "-") + dateStr[1] + (dateStr[2].length () == 1 ? "-0" : "-") + dateStr[2];
            }
            if (date.length () != 10 & date.length () != 19) {
                return null;
            }
            if (date.length () == 10) {
                date = date + " 00:00:00";
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse (date);
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }

    public static Date parseDate(String str, String parsePatterns) {
        try {
            return parseDate(str, new String[] { parsePatterns });
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Title: fmtYMDHMSToDate
     * @Description: yyyyMMddHHmmss
     * @param dtFormat
     * @return
     *
     */
    @Deprecated
    public static Date fmtYMDHMSToDate(String dtFormat){
        if (dtFormat == null || dtFormat.equals ("")) { return null; }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.parse (dtFormat);
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }

    /**
     * Description:格式化日期,如果格式化失败返回def
     */
    public static Date fmtStrToDate(String dtFormat, Date def){
        Date d = fmtStrToDate (dtFormat);
        if (d == null) {
            return def;
        }
        return d;
    }

    /**
     * 返回当日短日期型
     */
    public static Date getToDay(){
        return toShortDate (new Date());
    }

    /**
     * Description:格式化日期,String字符串转化为Date
     * 
     * @param date
     * @param dtFormat
     *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd
     * @return
     */
    public static String fmtDateToStr(Date date, String dtFormat){
        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format (date);
        } catch (Exception e) {
            e.printStackTrace ();
            return "";
        }
    }
    public static String formatDate(Date date, String dtFormat){
        return fmtDateToStr(date,dtFormat);
    }


    /**
     * Description:按指定格式 格式化日期
     * 
     * @param date
     * @param dtFormat
     * @return
     */
    public static Date fmtStrToDate(String date, String dtFormat){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.parse (date);
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }


    public static String fmtDateToYMDHMS(Date date){
        return fmtDateToStr (date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String fmtDateToYMDHM(Date date){
        return fmtDateToStr (date, "yyyy-MM-dd HH:mm");
    }

    public static String fmtDateToYMD(Date date){
        return fmtDateToStr (date, "yyyy-MM-dd");
    }

    public static String fmtDateToYM(Date date){
        return fmtDateToStr (date, "yyyy-MM");
    }

    public static String fmtDateToM(Date date){
        return fmtDateToStr (date, "MM");
    }

    /**
     * Description:只保留日期中的年月日
     * 
     * @param date
     * @return
     * @author 孙钰佳
     * @since：2007-12-10 上午11:25:50
     */
    public static Date toShortDate(Date date){
        return DateProcess.getFirst(date);
    }

    /**
     * 只保留日期中的年月日
     * 
     * @param date 格式要求yyyy -MM-dd
     * @return
     */
    public static Date toShortDate(String date){
        if (date != null && date.length () >= 10) {
            return fmtStrToDate (date.substring (0, 10));
        } else {
            return fmtStrToDate (date);
        }
    }

    /**
     * 求对日
     * 
     * @param countMonth 月份的个数(几个月)
     * @param before true 求前countMonth个月的对日:false 求下countMonth个月的对日
     * @return
     */
    public static Date getCounterglow(int countMonth, boolean before){
        Calendar ca = Calendar.getInstance ();
        return getCounterglow (ca.getTime (), before ? -countMonth : countMonth);
    }

    /**
     * Description: 求对日 加月用+ 减月用-
     * 
     * @param date
     * @param num
     * @return
     * @since：2007-12-13 下午03:16:47
     */
    public static Date getCounterglow(Date date, int num){
        Calendar ca = Calendar.getInstance ();
        ca.setTime (date);
        ca.add (Calendar.MONTH, num);
        return ca.getTime ();
    }

    /**
     * Description:加一天
     * 
     * @param date
     * @return
     * @author 孙钰佳
     * @since：2007-12-13 下午02:57:38
     */
    public static Date addDay(Date date){
        Calendar cd = Calendar.getInstance ();
        cd.setTime (date);
        cd.add (Calendar.DAY_OF_YEAR, 1);
        return cd.getTime ();
    }

    /**
     * Description:判断一个日期是否为工作日(非周六周日)
     * 
     * @param date
     * @return
     */
    public static boolean isWorkDay(Date date){
        Calendar cd = Calendar.getInstance ();
        cd.setTime (date);
        int dayOfWeek = cd.get (Calendar.DAY_OF_WEEK);
        if (dayOfWeek != Calendar.SUNDAY || dayOfWeek != Calendar.SATURDAY) {
            return false;
        }
        return true;
    }

    /**
     * 取一个月的最后一天
     */
    public static Date getLastDayOfMonth(Date date1){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.set (Calendar.DAY_OF_MONTH, 1);
        date.add (Calendar.MONTH, 1);
        date.add (Calendar.DAY_OF_YEAR, -1);
        return toShortDate (date.getTime ());
    }
    

    
    public static Date getFirstDayOfWeek(Date date1){
        Calendar cdate = dateToCalendar (date1);
        cdate.set (Calendar.DAY_OF_WEEK, 1);
        return cdate.getTime ();
    }
    
    public static Date getLastDayOfWeek(Date date1){
        Calendar cdate = dateToCalendar (date1);
        cdate.set (Calendar.DAY_OF_WEEK, 7);
        return cdate.getTime ();
    }
    

    /**
     * 取一个月的第 一天
     */
    public static Date getFirstDayOfMonth(Date date1){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.set (Calendar.DAY_OF_MONTH, 1);
        return toShortDate (date.getTime ());
    }
    
    

    /**
     * 求开始截至日期之间的天数差.
     * 
     * @param d1
     *            开始日期
     * @param d2
     *            截至日期
     * @return 返回相差天数
     */
    public static int getDaysInterval(Date d1, Date d2){
        if (d1 == null || d2 == null) {
            return 0;
        }
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Calendar[] cal = new Calendar[2];
        for ( int i = 0 ; i < cal.length ; i++ ) {
            cal[i] = Calendar.getInstance ();
            cal[i].setTime (toShortDate (d[i]));
            cal[i].set (Calendar.HOUR_OF_DAY, 0);
            cal[i].set (Calendar.MINUTE, 0);
            cal[i].set (Calendar.SECOND, 0);
        }
        long m = cal[0].getTime ().getTime ();
        long n = cal[1].getTime ().getTime ();
        int ret = (int) Math.abs ((m - n) / 1000 / 3600 / 24);
        return ret;
    }

    public static String getDayOfWeek(Date date){
        Calendar cl = Calendar.getInstance ();
        cl.setTime (date);
        return "周" + toChNumber (cl.get (Calendar.DAY_OF_WEEK) - 1);
    }

    /**
     * 将数字转为中文。 "0123456789"->"〇一二三四五六七八九"
     * 
     * @param num
     *            长度为1,'0'-'9'的字符串
     * @return
     */
    private static String toChNumber(int num){
        final String str = "〇一二三四五六七八九";
        return str.substring (num, num + 1);
    }

    /**
     * Description:指定日期加或减days天
     */
    public static Date addDay(Date date1, int days){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.add (Calendar.DAY_OF_YEAR, days);
        return date.getTime ();
    }

    /**
     * Description:指定日期加或减months月
     * 
     * @param date1
     * @param months
     */
    public static Date addMonth(Date date1, int months){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.add (Calendar.MONTH, months);
        return date.getTime ();
    }

    /**
     * Description:指定日期加或减years年
     * 
     * @param date1
     * @param years
     * @return
     */
    public static Date addYear(Date date1, int years){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.add (Calendar.YEAR, years);
        return date.getTime ();
    }

    /**
     * Description:指定日期加或减,单位分
     * 
     * @param date1
     * @param minute
     * @return
     */
    public static Date addMinute(Date date1, int minute){
        Calendar date = Calendar.getInstance ();
        date.setTime (date1);
        date.add (Calendar.MINUTE, minute);
        return date.getTime ();
    }

    /**
     * 指定期间的开始日期
     * 
     * @param date
     *            指定日期
     * @param type
     *            期间类型
     * @param diff
     *            与指定日期的范围
     * @return
     */
    public static Date getPeriodStart(Calendar date, int type, int diff){
        date.add (type, diff * (-1));
        return date.getTime ();
    }

    /**
     * 指定期间的开始日期
     * 
     * @param date
     *            指定日期
     * @param type
     *            期间类型
     * @param diff
     *            与指定日期的范围
     * @return
     */
    public static Date getPeriodStart(Date date, int type, int diff){
        return getPeriodStart (dateToCalendar (date), type, diff);
    }

    /**
     * 指定期间的结束日期
     * 
     * @param date
     *            指定日期
     * @param type
     *            期间类型
     * @param diff
     *            与指定日期的范围
     * @return
     */
    public static Date getPeriodEnd(Calendar date, int type, int diff){
        date.add (type, diff);
        return date.getTime ();
    }

    /**
     * 指定期间的结束日期
     * 
     * @param date
     *            指定日期
     * @param type
     *            期间类型
     * @param diff
     *            与指定日期的范围
     * @return
     */
    public static Date getPeriodEnd(Date date, int type, int diff){
        return getPeriodEnd (dateToCalendar (date), type, diff);
    }
    
    /**
     * 调整日期
     * @param date		要调整的时间
     * @param value		调整值， 可以试正负数
     * @param unit		调整单位
     * @return
     */
    public static Date modifyDateTime(Date date, int value, int unit){
        Calendar cdate = dateToCalendar (date);
        cdate.add (unit, value);
        return cdate.getTime ();
    }
    

    /**
     * 指定日期所在星期的第一天
     * 
     * @param date
     * @return
     */
    public static Date getWeekStart(Date date){
        Calendar cdate = dateToCalendar (date);
        cdate.set (Calendar.DAY_OF_WEEK, 2);
        return cdate.getTime ();
    }

    /**
     * 将java.util.Date类型转换成java.util.Calendar类型
     * 
     * @param date
     * @return
     */
    public static Calendar dateToCalendar(Date date){
        Calendar cdate = Calendar.getInstance ();
        cdate.setTime (date);
        return cdate;
    }

    /**
     * 指定日期所在月的第一天
     * 
     * @param date
     * @return
     */
    public static Date getMonthStart(Date date){
        Calendar cdate = dateToCalendar (date);
        cdate.set (Calendar.DAY_OF_MONTH, 1);
        return toShortDate (cdate.getTime ());
    }



    public static int getDaysOfMonth(Date date){
        Calendar cdate = dateToCalendar (date);
        return cdate.get (Calendar.DAY_OF_MONTH);
    }

    /**
     * 指定日期所在上月的第一天
     * 
     * @param date
     * @return
     */
    public static Date getLastMonthStart(Date date){
        Calendar cdate = dateToCalendar (date);
        cdate.set (Calendar.DAY_OF_MONTH, 1);
        cdate.add (Calendar.MONTH, -1);
        return toShortDate (cdate.getTime ());
    }

    /**
     * 指定日期所在旬的第一天
     * 
     * @param date
     * @return
     */
    public static Date getTenDaysStart(Date date){
        Calendar cdate = dateToCalendar (date);
        int day = cdate.get (Calendar.DAY_OF_MONTH) / 10 * 10 + 1;
        if (cdate.get (Calendar.DAY_OF_MONTH) % 10 == 0 || day == 31) {
            day = day - 10;
        }
        cdate.set (Calendar.DAY_OF_MONTH, day);
        return cdate.getTime ();
    }

    /**
     * 指定日期所在旬的最后一天
     * 
     * @param date
     * @return
     */
    public static Date getTenDaysEnd(Date date){
        Calendar cdate = dateToCalendar (date);
        if (cdate.get (Calendar.DAY_OF_MONTH) / 10 == 2 && cdate.get (Calendar.DAY_OF_MONTH) != 20) {
            return getLastDayOfMonth (date);
        }else {
            return addDay (getTenDaysStart (addDay (date, 10)), -1);
        }
    }

    /**
     * 指定日期所在年的第一天
     * 
     * @param date
     * @return
     */
    public static Date getYearStart(Date date){
        Calendar cdate = dateToCalendar (date);
        cdate.set (Calendar.DAY_OF_YEAR, 1);
        return cdate.getTime ();
    }

    /**
     * 指定日期所在季度的第一天
     * 
     * @param date
     * @return
     */
    public static Date getQuarterStart(Date date){
        Calendar cdate = dateToCalendar (date);
        int month = (cdate.get (Calendar.MONTH) / 3) * 3;
        cdate.set (Calendar.MONTH, month);
        return getMonthStart (cdate.getTime ());
    }

    /**
     * 指定日期返回带中文的字符串（目前为年月日类型，之后补充）
     * 
     * @param date
     * @param format
     * @return
     */
    public static String dateToStringByChinese(String format, Date date){
        String dateString = fmtDateToStr (date, format);
        String[] dateStringArray = dateString.split ("-");
        if ("yyyy-MM-dd".equals (format)) {
            dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月" + dateStringArray[2] + "日";
        } else if ("yyyy-MM".equals (format)) {
            dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月";
        }
        return dateString;
    }

    /**
     * 取一年的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String years = dateFormat.format (date);
        years += "-12-31";
        Date returnDate = fmtStrToDate (years);
        return toShortDate (returnDate);
    }

    /**
     * 计算两个日期之间相差的月数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonths(Date date1, Date date2){
        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance ();
            objCalendarDate1.setTime (date1);

            Calendar objCalendarDate2 = Calendar.getInstance ();
            objCalendarDate2.setTime (date2);

            if (objCalendarDate2.equals (objCalendarDate1)) {
                return 0;
            }
            if (objCalendarDate1.after (objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }
            if (objCalendarDate2.get (Calendar.DAY_OF_MONTH) < objCalendarDate1.get (Calendar.DAY_OF_MONTH)) {
                flag = 1;
            }

            if (objCalendarDate2.get (Calendar.YEAR) > objCalendarDate1.get (Calendar.YEAR)) {
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12
                        + objCalendarDate2.get(Calendar.MONTH) - flag)
                        - objCalendarDate1.get(Calendar.MONTH);
            }
            else {
                iMonth = objCalendarDate2.get (Calendar.MONTH) - objCalendarDate1.get (Calendar.MONTH) - flag;
            }

        } catch (Exception e) {
            e.printStackTrace ();
        }
        return iMonth;
    }

    /**
     * 指定日期上一个旬的第一天
     */
    public static Date getLastTenStartDate(Date date){
        Date returnDate = DateKit.toShortDate (date);
        returnDate = DateKit.getTenDaysStart (date);
        returnDate = DateKit.addDay (returnDate, -1);
        returnDate = DateKit.getTenDaysStart (returnDate);
        return DateKit.toShortDate (returnDate);
    }

    /**
     * 指定日期上一个旬的最后一天
     */
    public static Date getLastTenEndDate(Date date){
        Date returnDate = DateKit.toShortDate (date);
        returnDate = DateKit.getTenDaysStart (date);
        returnDate = DateKit.addDay (returnDate, -1);
        return DateKit.toShortDate (returnDate);
    }

    /**
     * 指定日期上个月第一天
     */
    public static Date getLastMonthStartDate(Date date){
        Date returnDate = DateKit.toShortDate (date);
        returnDate = DateKit.getLastMonthStart (date);
        return DateKit.toShortDate (returnDate);
    }

    /**
     * 指定日期上个月最后一天
     */
    public static Date getLastMonthEndDate(Date date){
        Date returnDate = DateKit.toShortDate (date);
        returnDate = DateKit.getMonthStart (date);
        returnDate = DateKit.addDay (returnDate, -1);
        return DateKit.toShortDate (returnDate);
    }

    public static String toUpTimeString(int min){
        int hours = (int) Math.floor (min / 60.0), days = (int) Math.floor (min / 1440.0), months = (int) Math.floor (min / 43200.0);
        int mi = min % 60, HH = hours % 24, dd = days % 30, MM = months;
        StringBuilder sb = new StringBuilder();
        if (MM > 0) {
            sb.append (MM).append ("mon,").append (dd).append ("d");
        } else if (dd > 0) {
            sb.append (dd).append ("d,").append (HH).append ("h");
        } else if (HH > 0) {
            sb.append (HH).append ("h,").append (mi).append ("min");
        } else {
            sb.append (mi).append ("min");
        }
        return sb.toString ();
    }

    public static String toGMTString(Date date){
        SimpleDateFormat GMT_FMT = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z",new java.util.Locale ("en","GB"));
        GMT_FMT.setTimeZone (new java.util.SimpleTimeZone (0,"GMT"));
        return GMT_FMT.format (date);
    }

    public static String toPDString(Date date){
        SimpleDateFormat PD_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return PD_FMT.format (date);
    }

    /**
     * 将日期转换为 17 位的字符串(格式为yyyy-MM-dd HHmmss).
     */
    public static String dateTo19String(Date date){
        if (date == null) { return null; }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format (date);
    }

    public static long nMsBetweenTwoDate(Date d1, Date d2){
        return d1.getTime () - d2.getTime ();
    }

    // 当前日期加减n秒后的日期，返回String (yyyy-mm-dd)
    public static Date nSecAfterNowDate(int n){
        Calendar rightNow = Calendar.getInstance ();
        // rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add (Calendar.SECOND, +n);
        return rightNow.getTime ();
    }

    public static Date nSecAfterNowDate(Date d, int n){
        Calendar rightNow = Calendar.getInstance ();
        rightNow.setTime (d);
        // rightNow.add(Calendar.DAY_OF_MONTH,-1);
        rightNow.add (Calendar.SECOND, +n);
        return rightNow.getTime ();
    }

    /**
     * @Title: areSameDay
     * @Description: 两个日期是否为同一天
     * @param dateA
     * @param dateB
     * @return
     */
    public static boolean areSameDay(Date dateA, Date dateB){
        Calendar calDateA = Calendar.getInstance ();
        calDateA.setTime (dateA);
        Calendar calDateB = Calendar.getInstance ();
        calDateB.setTime (dateB);
        return calDateA.get (Calendar.YEAR) == calDateB.get (Calendar.YEAR) && calDateA.get (Calendar.MONTH) == calDateB.get (Calendar.MONTH)
                && calDateA.get (Calendar.DAY_OF_MONTH) == calDateB.get (Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取当前时间
     * @return
     */
	public static java.sql.Timestamp getCurrentDate(){
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

    public static void main(String[] args){

        System.out.println (getPrefix());
        System.out.println(fmtYMDHMSToDate("yyyy-MM-dd"));

        /*Date date = DateKit.modifyDateTime(new Date(),1,Calendar.DAY_OF_YEAR);
        System.out.println (DateKit.fmtDateToStr(date,"yyyy-MM-dd HH:mm:ss"));

        System.out.println (DateKit.fmtDateToStr(getLastDayOfMonth (new Date ()),"yyyy-MM-dd HH:mm:ss"));
        System.out.println (DateKit.fmtDateToStr(getFirstDayOfMonth (new Date ()),"yyyy-MM-dd HH:mm:ss"));
        

        System.out.println (DateKit.fmtDateToStr(getLastDayOfWeek (new Date ()),"yyyy-MM-dd HH:mm:ss"));*/
        
        
        
        
        Calendar date1 = Calendar.getInstance ();
        date1.setTime (new Date());
        date1.add (Calendar.DAY_OF_MONTH, 100);
        System.out.println(DateKit.fmtDateToStr(date1.getTime (),"yyyy-MM-dd HH:mm:ss"));
        

        Calendar date2 = Calendar.getInstance ();
        date2.setTime (new Date());
        date2.add (Calendar.DAY_OF_WEEK, 100);
        System.out.println(DateKit.fmtDateToStr(date2.getTime (),"yyyy-MM-dd HH:mm:ss"));

        Calendar date3 = Calendar.getInstance ();
        date3.setTime (new Date());
        date3.add (Calendar.DAY_OF_WEEK_IN_MONTH, 100);
        System.out.println(DateKit.fmtDateToStr(date3.getTime (),"yyyy-MM-dd HH:mm:ss"));

        Calendar date4 = Calendar.getInstance ();
        date4.setTime (new Date());
        date4.add (Calendar.DAY_OF_YEAR, 100);
        System.out.println(DateKit.fmtDateToStr(date4.getTime (),"yyyy-MM-dd HH:mm:ss"));
        

        Calendar date5 = Calendar.getInstance ();
        date5.setTime (new Date());
        date5.add (Calendar.WEEK_OF_MONTH, 2);
        System.out.println(DateKit.fmtDateToStr(date5.getTime (),"yyyy-MM-dd HH:mm:ss"));
        

        Calendar date6 = Calendar.getInstance ();
        date6.setTime (new Date());
        date6.add (Calendar.WEEK_OF_YEAR, 2);
        System.out.println(DateKit.fmtDateToStr(date6.getTime (),"yyyy-MM-dd HH:mm:ss"));
        
        Calendar date7 = Calendar.getInstance ();
        date7.setTime (new Date());
        date7.add (Calendar.MONTH, 2);
        System.out.println(DateKit.fmtDateToStr(date7.getTime (),"yyyy-MM-dd HH:mm:ss"));
        

        Calendar date8 = Calendar.getInstance ();
        date8.setTime (new Date());
        date8.add (Calendar.YEAR, 2);
        System.out.println(DateKit.fmtDateToStr(date8.getTime (),"yyyy-MM-dd HH:mm:ss"));
        
        
    }
}