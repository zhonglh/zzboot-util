package com.zzboot.util.cron;

import com.zzboot.util.base.data.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * @author Administrator
 */
@Slf4j
public class CollectUtil {


    private static String[] week   = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    private static String[] month  = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

    private CollectUtil() {}

    public static String getCronDesc(String cron, Locale locale){
    	if(StringUtils.isEmpty(cron)) {
    	    return "";
        }
        if (locale == null || locale.toString().toLowerCase().indexOf("zh")>=0) {
            return getCronDescZH (cron);
        } else {
            return getCronDescEN (cron);
        }
    }

    private static String getCronDescEN(String cron){
        String desc = "";
        List<String> cronList = null;
        try {
            cronList = getTimeExpression (cron);
        } catch (Exception e) {
            log.error (e.getMessage (), e);
            return cron;
        }
        // 判断星期
        if (cronList != null && cronList.size () > 0) {
            // 判断月
            if (cronList.get (1).toString ().indexOf ("*") > -1) {
                desc = "Every month ";
            } else {
                String[] s = cronList.get (1).toString ().split (",");
                for ( int i = 0 ; i < s.length ; i++ ) {
                    desc = desc + month[new Integer(s[i])-1] + " ";
                }
            }
            // 判断星期
            if (cronList.get (0).toString ().indexOf ("?") > -1) {

            } else if (cronList.get (0).toString ().indexOf ("*") > -1) {
                if (!StringUtils.isBlank (desc)) {
                    desc = desc + "Every day ";
                }
            } else {
                String[] s = cronList.get (0).toString ().split (",");
                if (s.length > 0) {
                    desc = desc + "Every week ";
                }
                for ( int i = 0 ; i < s.length ; i++ ) {
                    if ("1".equals (s[i])) {
                        desc = desc + "Sun";
                    } else {
                        desc = desc + week[(new Integer(s[i]) - 1)] + " ";
                    }
                }
            }

            // 判断天
            if (cronList.get (2).toString ().indexOf ("?") > -1) {

            } else if (cronList.get (2).toString ().indexOf ("*") > -1) {
                desc = desc + "Every day ";
            } else {
                String[] s = cronList.get (2).toString ().split (",");
                for ( int i = 0 ; i < s.length ; i++ ) {
                    if ("1".equals (s[i])) {
                        desc = desc + s[i] + "st ";
                    } else if ("2".equals (s[i])) {
                        desc = desc + s[i] + "nd ";
                    } else if ("3".equals (s[i])) {
                        desc = desc + s[i] + "rd ";
                    } else {
                        desc = desc + s[i] + "th ";
                    }
                }
            }

            // 判断时
            if (cronList.get (3).toString ().indexOf ("*") > -1) {
                desc = desc + "Every hour ";
            } else {
                String[] s = cronList.get (3).toString ().split (",");
                if (s.length == 1) {
                    desc = desc + "at " + s[0] + " hour ";
                } else {
                    desc = desc + " at hours " + Arrays.toString (s) + " ";
                }
            }

            // 判断分
            if (cronList.get (4).toString ().indexOf ("/") > -1) {
                String str = cronList.get (4).toString ();
                // String fromMinutes = str.substring (0, str.lastIndexOf ("/"));
                // if (!"0".equals (fromMinutes) && !"*".equals (fromMinutes)) {
                // desc = desc + "从第" + fromMinutes + "分钟开始";
                // }
                desc = desc + "Every " + str.substring (str.lastIndexOf ("/") + 1) + " minute(s) ";
            } else {
                String[] s = cronList.get (4).toString ().split (",");
                if (s.length == 1) {
                    if (s[0].equals ("*")) {
                        desc = desc + "Every minute ";
                    } else {
                        desc = desc + "at " + s[0] + " minute ";
                    }
                } else {
                    desc = desc + "at minutes " + Arrays.toString (s);
                }
            }

        }
        return desc;
    }

    private static String getCronDescZH(String cron){
        String desc = "";
        List<String> cronList = null;
        try {
            cronList = getTimeExpression (cron);
        } catch (Exception e) {
            log.error (e.getMessage (), e);
            return cron;
        }
        // 判断星期
        if (cronList != null && cronList.size () > 0) {
            // 判断月
            if (cronList.get (1).toString ().indexOf ("*") > -1) {
                desc = "每月";
            } else {
                String[] s = cronList.get (1).toString ().split (",");
                for ( int i = 0 ; i < s.length ; i++ ) {
                    desc = desc + s[i] + "月";
                }
            }
            // 判断星期
            if (cronList.get (0).toString ().indexOf ("?") > -1) {

            } else if (cronList.get (0).toString ().indexOf ("*") > -1) {
                desc = desc + "每天";
            } else {
                String[] s = cronList.get (0).toString ().split (",");
                if (s.length > 0) {
                    desc = desc + "每周 ";
                }
                for ( int i = 0 ; i < s.length ; i++ ) {
                    if ("1".equals (s[i])) {
                        desc = desc + "星期日";
                    } else {
                        desc = desc + "星期" + NumberUtil.numberToZH ((new Integer(s[i]) - 1), false);
                    }
                }
            }

            // 判断天
            if (cronList.get (2).toString ().indexOf ("?") > -1) {

            } else if (cronList.get (2).toString ().indexOf ("*") > -1) {
                desc = desc + "每天";
            } else {
                String[] s = cronList.get (2).toString ().split (",");
                for ( int i = 0 ; i < s.length ; i++ ) {
                    desc = desc + s[i] + "号";
                }
            }

            // 判断时
            if (cronList.get (3).toString ().indexOf ("*") > -1) {
                desc = desc + "每小时";
            } else {
                String[] s = cronList.get (3).toString ().split (",");
                for ( int i = 0 ; i < s.length ; i++ ) {
                    desc = desc + s[i] + "点";
                }
            }

            // 判断分
            if (cronList.get (4).toString ().indexOf ("/") > -1) {
                String str = cronList.get (4).toString ();
                // String fromMinutes = str.substring (0, str.lastIndexOf ("/"));
                // if (!"0".equals (fromMinutes) && !"*".equals (fromMinutes)) {
                // desc = desc + "从第" + fromMinutes + "分钟开始";
                // }
                desc = desc + "每隔" + str.substring (str.lastIndexOf ("/") + 1) + "分钟";
            } else {
                String[] s = cronList.get (4).toString ().split (",");
                if (s.length == 1) {
                    if (s[0].equals ("*")) {
                        desc = desc + "每分钟";
                    } else {
                        desc = desc + "第" + s[0] + "分钟";
                    }
                } else {
                    for ( int i = 0 ; i < s.length ; i++ ) {
                        desc = desc + "第" + s[i] + "分钟";
                    }
                }
            }

        }
        return desc + "执行一次";
    }

	/**
	 * 根据传过来的cron串解析成具体时间
	 * 
	 * @throws ParseException
	 */
	public static List<String> getTimeExpression(String cron)
			throws IllegalArgumentException, IllegalAccessException,
            InvocationTargetException, ParseException {
		List<String> list = new ArrayList<String>();
		try {
			CronExpressionEx ex = new CronExpressionEx(cron);
			// ----------------------------只输出后5条数据进行解析
			list.add(ex.getDaysOfWeekExp());
			list.add(ex.getMonthsExp());
			list.add(ex.getDaysOfMonthExp());
			list.add(ex.getHoursExp());
			list.add(ex.getMinutesExp());
		} catch (ParseException e) {
			log.error("TaskUtil------cron串解析异常");
			throw e;
		}
		return list;
	}

}
