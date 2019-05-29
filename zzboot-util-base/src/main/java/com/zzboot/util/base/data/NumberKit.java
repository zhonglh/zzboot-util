package com.zzboot.util.base.data;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 数字工具类
 * <p>
 * 作用：数据四舍五入、数字格式化
 * <p>
 * 注：ThreadLocal用空间换取时间，解决DecimalFormat线程安全
 */
public class NumberKit {

    private static final int                                     DECIMAL_POINT    = 2;

    private static final int                                     BIGDECIMAL_ROUND = BigDecimal.ROUND_HALF_UP;

    private static final ThreadLocal<Map<String, DecimalFormat>> _threadLocal     = new ThreadLocal<Map<String, DecimalFormat>>() {

                                                                                      @Override
                                                                                      protected Map<String, DecimalFormat> initialValue(){
                                                                                          return new HashMap<String, DecimalFormat>();
                                                                                      }
                                                                                  };

    /** 数据类型格式 */
    private static final String pattern          = "#.00";







    public static DecimalFormat getDecimalFormat(String pattern){
        DecimalFormat fmt = _threadLocal.get ().get (pattern);
        if (fmt == null) {
            fmt = new DecimalFormat(pattern);
            _threadLocal.get ().put (pattern, fmt);
        }
        return fmt;
    }

    /**
     * 把数据转化为double
     * <p>
     * 输出格式小数点后始终有两位“##.00”
     * 
     * @param obj
     *            -<code>BigDecimal</code>,<code>double</code>,<code>int</code> 等数据类型
     * @return
     */
    public static double getNumberFormatByObject(Object obj){
        return Double.valueOf (getDecimalFormat (pattern).format (obj));
    }

    /**
     * 把数据转化为double
     * <p>
     * 输出格式小数点后始终有两位“.00”
     * 
     * @param s
     *            传入数据
     * @return
     */
    public static double getNumberForBigDecimal(BigDecimal s){
        BigDecimal s1 = s.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return s1.doubleValue ();
    }

    /**
     * 把BigDecimal数据转化为Double
     * 
     * @param s
     * @return
     */
    public static double getNumberFormatByInt(int s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number.doubleValue ();
    }



    /**
     * 把BigDecimal数据转化为Double
     * 
     * @param s
     * @return
     */
    public static double getNumberFormatByDouble(double s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number.doubleValue ();
    }

    public static BigDecimal getNumberFormat(double s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number;
    }

    public static BigDecimal getNumberFormat(BigDecimal number){
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number;
    }

    /**
     * 把BigDecimal数据转化为Double
     * 
     * @param s
     * @return
     */
    public static double getNumberFormatByString(String s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number.doubleValue ();
    }

    /**
     * 把BigDecimal数据转化为Double
     * 
     * @param s
     * @return
     */
    public static double getNumberFormatByLong(long s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number.doubleValue ();
    }

    /**
     * 把BigDecimal数据转化为Double
     * 
     * @param s
     * @return
     */
    public static double getNumberFormatByFloat(float s){
        BigDecimal number = new BigDecimal(s);
        number = number.setScale (DECIMAL_POINT, BIGDECIMAL_ROUND);
        return number.doubleValue ();
    }

    public static double getNumberForBigDecimalWithDivide(BigDecimal s1, BigDecimal s2){
        BigDecimal s3 = s1.divide (s2, DECIMAL_POINT, BIGDECIMAL_ROUND);
        double s4 = s3.doubleValue ();
        return s4;
    }

    /**
     * @Title: random
     * @Description: 生成几位随机数 1-10位
     * @param n
     * @return
     */
    public static String random(int n){
        if (n < 1 || n > 10) { throw new IllegalArgumentException("cannot random " + n + " bit number"); }
        Random ran = new Random();
        if (n == 1) { return String.valueOf (ran.nextInt (10)); }
        int bitField = 0;
        char[] chs = new char[n];
        for ( int i = 0 ; i < n ; i++ ) {
            while (true) {
                int k = ran.nextInt (10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

    /**
     * 格式化金额
     * 
     * @param s
     *            格式化字符
     * @param len
     *            保留几们小数
     * @return
     */
    public static String formatMoney(String s, int len){
        if (s == null || s.length () < 1) { return ""; }
        NumberFormat formater = null;
        double num = Double.parseDouble (s);
        if (len == 0) {
            formater = getDecimalFormat ("###,###");
        } else {
            StringBuffer buff = new StringBuffer();
            buff.append ("###,###.");
            for ( int i = 0 ; i < len ; i++ ) {
                buff.append ("#");
            }
            formater = getDecimalFormat (buff.toString ());
        }
        String result = formater.format (num);
        if (result.indexOf (".") == -1) {
            result = "￥" + result + ".00";
        } else {
            result = "￥" + result;
        }
        return result;
    }

    static final String zhnum_0  = "零壹贰叁肆伍陆柒捌玖";

    static final String zhnum    = "零一二三四五六七八九";

    static final String[] zhnum1   = { "", "十", "百", "千" };

    static final String[] zhnum1_0 = { "", "拾", "佰", "仟" };

    static final String[] zhnum2   = { "", "万", "亿", "万亿", "亿亿" };

    private static String numberToZH4(String s, boolean fan){
        StringBuilder sb = new StringBuilder();
        if (s.length () != 4) return null;
        for ( int i = 0 ; i < 4 ; i++ ) {
            char c1 = s.charAt (i);
            if (c1 == '0' && i > 1 && s.charAt (i - 1) == '0') continue;
            if (c1 != '0' && i > 1 && s.charAt (i - 1) == '0') sb.append ('零');
            if (c1 != '0') {
                if (fan) {
                    sb.append (zhnum_0.charAt (c1 - 48));
                    sb.append (zhnum1_0[4 - i - 1]);
                } else {
                    sb.append (zhnum.charAt (c1 - 48));
                    sb.append (zhnum1[4 - i - 1]);
                }
            }
        }
        return new String(sb);
    }

    public static String numberToZH(long n, boolean fan){
        StringBuilder sb = new StringBuilder();
        String strN = "000" + n;
        int strN_L = strN.length () / 4;
        strN = strN.substring (strN.length () - strN_L * 4);
        for ( int i = 0 ; i < strN_L ; i++ ) {
            String s1 = strN.substring (i * 4, i * 4 + 4);
            String s2 = numberToZH4 (s1, fan);
            sb.append (s2);
            if (s2.length () != 0) sb.append (zhnum2[strN_L - i - 1]);
        }
        String s = new String(sb);
        if (s.length () != 0 && s.startsWith ("零")) s = s.substring (1);
        return s;
    }

    public static String numberToZH(double d, boolean fan){
        return numberToZH ("" + d, fan);
    }

    /**
     * Description: 数字转化成整数
     * 
     * @param str
     * @param fan
     * @return
     */
    public static String numberToZH(String str, boolean fan){
        StringBuilder sb = new StringBuilder();
        int dot = str.indexOf (".");
        if (dot < 0) dot = str.length ();

        String zhengshu = str.substring (0, dot);
        sb.append (numberToZH (Long.parseLong (zhengshu), fan));
        if (dot != str.length ()) {
            sb.append ("点");
            String xiaoshu = str.substring (dot + 1);
            for ( int i = 0 ; i < xiaoshu.length () ; i++ ) {
                if (fan) {
                    sb.append (zhnum_0.charAt (Integer.parseInt (xiaoshu.substring (i, i + 1))));
                } else {
                    sb.append (zhnum.charAt (Integer.parseInt (xiaoshu.substring (i, i + 1))));
                }
            }
        }
        String s = new String(sb);
        if (s.startsWith ("零")) s = s.substring (1);
        if (s.startsWith ("一十")) s = s.substring (1);
        while (s.endsWith ("零")) {
            s = s.substring (0, s.length () - 1);
        }
        if (s.endsWith ("点")) s = s.substring (0, s.length () - 1);
        return s;
    }

    public static String numberToRMB(double rmb){
        String strRMB = "" + rmb;
        DecimalFormat nf = new DecimalFormat("#.#");
        nf.setMaximumFractionDigits (2);
        strRMB = nf.format (rmb).toString ();
        strRMB = numberToZH (strRMB, true);
        if (strRMB.indexOf ("点") >= 0) {
            strRMB = strRMB + "零";
            strRMB = strRMB.replaceAll ("点", "元");
            String s1 = strRMB.substring (0, strRMB.indexOf ("元") + 1);
            String s2 = strRMB.substring (strRMB.indexOf ("元") + 1);
            strRMB = s1 + s2.charAt (0) + "角"; 
            if(s2.length()>1 && "0".equals(s2.charAt (1))) {
                strRMB += s2.charAt(1) + "分";
            }
        } else {
            strRMB = strRMB + "元整";
        }
        return  strRMB;
    }

    /**
     * 判断是否为科学计数法
     * @param value
     * @return
     */
    public static  boolean checkSCMethod(String value){
        String regx = "^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";//科学计数法正则表达式
        Pattern pattern = Pattern.compile(regx);
        return pattern.matcher(value).matches();
    }

    public static void main(String[] args){
        System.out.println(numberToRMB(214123.10));
    }


}
