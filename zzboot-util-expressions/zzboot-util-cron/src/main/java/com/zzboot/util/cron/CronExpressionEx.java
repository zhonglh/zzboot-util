
package com.zzboot.util.cron;

import java.text.ParseException;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * Quartz 时间表达式
 */
public class CronExpressionEx  extends CronExpression{

	private static final long serialVersionUID = -8177514590861461678L;

	public static final int     NO_SPEC_INT = 98;
    // 秒钟
    private String secondsExp;
    // 分钟
    private String minutesExp;
    // 小时
    private String hoursExp;
    // 月
    private String monthsExp;
    // 星期
    private String daysOfWeekExp;
    // 日或者天
    private String daysOfMonthExp;

    public CronExpressionEx(String cronExpression) throws ParseException {
        super (cronExpression);
        StringTokenizer exprsTok = new StringTokenizer(cronExpression," \t",false);
        secondsExp = exprsTok.nextToken ().trim ();
        minutesExp = exprsTok.nextToken ().trim ();
        hoursExp = exprsTok.nextToken ().trim ();
        daysOfMonthExp = exprsTok.nextToken ().trim ();
        monthsExp = exprsTok.nextToken ().trim ();
        daysOfWeekExp = exprsTok.nextToken ().trim ();
    }

    // 返回秒钟集合
    public Set<Integer> getSecondsSet(){
        return seconds;
    }

    // 返回秒钟字符拼串
    public String getSecondsField(){
        return getExpressionSetSummary (seconds);
    }

    // 返回分钟集合 如：“[0, 1, 2, 3]”
    public Set<Integer> getMinutesSet(){
        return minutes;
    }

    // 返回分钟字符拼串 如“0,1,2,3”
    public String getMinutesField(){
        return getExpressionSetSummary (minutes);
    }

    // 返回小时集合 如：[0, 1, 2, 3]
    public Set<Integer> getHoursSet(){
        return hours;
    }

    // 返回小时字符拼串 如“0,1,2,3”
    public String getHoursField(){
        return getExpressionSetSummary (hours);
    }

    public Set<Integer> getDaysOfMonthSet(){
        return daysOfMonth;
    }

    public String getDaysOfMonthField(){
        return getExpressionSetSummary (daysOfMonth);
    }

    // 返回月份集合
    public Set<Integer> getMonthsSet(){
        return months;
    }

    // 返回月份字符拼串
    public String getMonthsField(){
        return getExpressionSetSummary (months);
    }

    // 返回星期中天集合
    public Set<Integer> getDaysOfWeekSet(){
        return daysOfWeek;
    }

    // 返回星期中天字符拼串
    public String getDaysOfWeekField(){
        return getExpressionSetSummary (daysOfWeek);
    }

    public String getSecondsExp(){
        return secondsExp;
    }

    public String getMinutesExp(){
        return minutesExp;
    }

    public String getHoursExp(){
        return hoursExp;
    }

    public String getDaysOfMonthExp(){
        return daysOfMonthExp;
    }

    public String getMonthsExp(){
        return monthsExp;
    }

    public String getDaysOfWeekExp(){
        return daysOfWeekExp;
    }
}
