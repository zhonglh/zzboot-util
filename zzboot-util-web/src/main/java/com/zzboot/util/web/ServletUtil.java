package com.zzboot.util.web;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static com.zz.bms.util.base.data.StringKit.decode;

/**
 * @author Administrator
 */
public class ServletUtil {

    /**
     * 获取请求数据
     * @param request
     * @param fieldName
     * @return
     */
    public static String getParameter(HttpServletRequest request, String fieldName){
        return request.getParameter (fieldName);
    }


    public String[] getParameterValues(HttpServletRequest request, String fieldName){
        return request.getParameterValues(fieldName);
    }


    public static String[] getStringArray(HttpServletRequest request, String strParamName, String strDefaultValue){
        if (request == null || StringUtils.isEmpty (strParamName)) { return null; }
        String[] strParameterValues = request.getParameterValues (strParamName);
        if (strParameterValues == null) {
            strParameterValues = new String[0];
        }
        for ( int i = 0 ; i < strParameterValues.length ; i++ ) {
            if (strParameterValues[i] == null || strParameterValues[i].equals ("")) {
                strParameterValues[i] = strDefaultValue;
            }
        }
        return strParameterValues;
    }

    /**
     * 获取Cookie值
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies ();
        if (cookies == null) { return ""; }
        for ( int i = 0 ; i < cookies.length ; i++ ) {
            Cookie cookie = cookies[i];
            if (cookie.getName ().equals (name)) {
                return decode (cookie.getValue ());
            }
        }
        return "";
    }

    /**
     * 获取上一页面地址
     * @param request
     * @param defaultPage
     * @return
     */
    public static String getReferer(HttpServletRequest request, String defaultPage){
        // 前一页面的地址, 提交结束后返回此页面
        String referer = request.getHeader ("Referer");

        // 获取URL中的referer参数
        String refererParam = request.getParameter ("referer");

        if (!StringUtils.isNotEmpty(refererParam)) {
            referer = refererParam;
        }

        if (StringUtils.isEmpty (referer)) {
            referer = defaultPage;
        }

        return referer;
    }

    /**
     * 获取请求全路径
     * @param request
     * @return
     */
    public static String getFullRequestURL(HttpServletRequest request){
        StringBuffer url = request.getRequestURL ();
        String qString = request.getQueryString ();

        if (qString != null) {
            url.append ('?');
            url.append (qString);
        }

        return url.toString ();
    }



    public static String getFullRequestURI(HttpServletRequest request){
        StringBuffer url = new StringBuffer(request.getRequestURI ());
        String qString = request.getQueryString ();

        if (qString != null) {
            url.append ('?');
            url.append (qString);
        }

        return url.toString ();
    }


}
