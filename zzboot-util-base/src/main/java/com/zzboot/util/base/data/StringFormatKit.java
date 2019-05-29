package com.zzboot.util.base.data;

import java.util.ArrayList;

/**
 * @author Administrator
 */
public class StringFormatKit {

    /**
     * @Fields CARRIAGE_RETURN : 回车
     */
    public static String CARRIAGE_RETURN = "\n";
    /**
     * @Fields TAB : 制表符
     */
    public static String TAB             = "\u0009";
    /**
     * @Fields SPACE : 空格
     */
    public static String SPACE           = "\u0008";
    /**
     * @Fields LINE_FEED : 换行
     */
    public static String LINE_FEED       = "\r";


    /**
     * json字符串的格式化
     * 
     * @author peiyuxin
     * @param json
     *            需要格式的json串
     * @param fillStringUnit 每一层之前的占位符号比如空格
     *            制表符
     * @return
     */
    public static String formatJson(String json, String fillStringUnit){
        if (json == null || json.trim ().length () == 0) { return null; }

        int fixedLenth = 0;
        ArrayList<String> tokenList = new ArrayList<String>();
        {
            String jsonTemp = json;
            // 预读取
            while (jsonTemp.length () > 0) {
                String token = getToken (jsonTemp);
                jsonTemp = jsonTemp.substring (token.length ());
                token = token.trim ();
                tokenList.add (token);
            }
        }

        for ( int i = 0 ; i < tokenList.size () ; i++ ) {
            String token = tokenList.get (i);
            int length = token.getBytes ().length;
            if (length > fixedLenth && i < tokenList.size () - 1 && tokenList.get (i + 1).equals (":")) {
                fixedLenth = length;
            }
        }

        StringBuilder buf = new StringBuilder();
        int count = 0;
        for ( int i = 0 ; i < tokenList.size () ; i++ ) {

            String token = tokenList.get (i);

            if (token.equals (",")) {
                buf.append (token);
                doFill (buf, count, fillStringUnit);
                continue;
            }
            if (token.equals (":")) {
                buf.append (" ").append (token).append (" ");
                continue;
            }
            if (token.equals ("{")) {
                String nextToken = tokenList.get (i + 1);
                if (nextToken.equals ("}")) {
                    i++;
                    buf.append ("{ }");
                } else {
                    count++;
                    buf.append (token);
                    doFill (buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals ("}")) {
                count--;
                doFill (buf, count, fillStringUnit);
                buf.append (token);
                continue;
            }
            if (token.equals ("[")) {
                String nextToken = tokenList.get (i + 1);
                if (nextToken.equals ("]")) {
                    i++;
                    buf.append ("[ ]");
                } else {
                    count++;
                    buf.append (token);
                    doFill (buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals ("]")) {
                count--;
                doFill (buf, count, fillStringUnit);
                buf.append (token);
                continue;
            }

            buf.append (token);
            // 左对齐
            if (i < tokenList.size () - 1 && tokenList.get (i + 1).equals (":")) {
                int fillLength = fixedLenth - token.getBytes ().length;
                if (fillLength > 0) {
                    for ( int j = 0 ; j < fillLength ; j++ ) {
                        buf.append (" ");
                    }
                }
            }
        }
        return buf.toString ();
    }

    private static String getToken(String json){
        StringBuilder buf = new StringBuilder();
        boolean isInYinHao = false;
        while (json.length () > 0) {
            String token = json.substring (0, 1);
            json = json.substring (1);

            if (!isInYinHao && (token.equals (":") || token.equals ("{") || token.equals ("}") || token.equals ("[") || token.equals ("]") || token.equals (","))) {
                if (buf.toString ().trim ().length () == 0) {
                    buf.append (token);
                }

                break;
            }

            if (token.equals ("\\")) {
                buf.append (token);
                buf.append (json.substring (0, 1));
                json = json.substring (1);
                continue;
            }
            if (token.equals ("\"")) {
                buf.append (token);
                if (isInYinHao) {
                    break;
                } else {
                    isInYinHao = true;
                    continue;
                }
            }
            buf.append (token);
        }
        return buf.toString ();
    }

    private static void doFill(StringBuilder buf, int count, String fillStringUnit){
        buf.append ("\n");
        for ( int i = 0 ; i < count ; i++ ) {
            buf.append (fillStringUnit);
        }
    }

    public final static char SEPARATOR = '_';


    public static void main(String[] args) {
        System.out.println(toUnderlineName("userNoAbc"));
    }

    /**
     * @Title: toUnderlineName
     * @Description: 驼峰转下划线方法
     * @param s
     * @return
     */
    public static String toUnderlineName(String s){
        if (s == null) { return null; }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for ( int i = 0 ; i < s.length () ; i++ ) {
            char c = s.charAt (i);

            boolean nextUpperCase = true;

            if (i < (s.length () - 1)) {
                nextUpperCase = Character.isUpperCase (s.charAt (i + 1));
            }

            if ((i >= 0) && Character.isUpperCase (c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append (SEPARATOR);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append (Character.toLowerCase (c));
        }
        return sb.toString ();
    }

    /**
     * @Title: toCamelCase
     * @Description: 下划线转驼峰
     * @param s
     * @return
     */
    public static String toCamelCase(String s){
        if (s == null) { return null; }
        // s = s.toLowerCase ();
        StringBuilder sb = new StringBuilder(s.length ());
        boolean upperCase = false;
        for ( int i = 0 ; i < s.length () ; i++ ) {
            char c = s.charAt (i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append (Character.toUpperCase (c));
                upperCase = false;
            } else {
                sb.append (c);
            }
        }
        return sb.toString ();
    }

    public static String toCapitalizeCamelCase(String s){
        if (s == null) { return null; }
        s = toCamelCase (s);
        return s.substring (0, 1).toUpperCase () + s.substring (1);
    }

}
