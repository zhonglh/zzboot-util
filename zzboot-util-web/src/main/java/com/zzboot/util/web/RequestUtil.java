package com.zzboot.util.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 */
public class RequestUtil {


    public static HttpServletRequest getRequest() {

        if(RequestContextHolder.getRequestAttributes() == null) {
            return ThreadLocalRequestHolder.getRequests();
        }else {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }


    }


    public static String getRequestSimpleURL(HttpServletRequest request) {

        return getRequestURL(request, false);
    }

    public static String getRequestURL(HttpServletRequest request) {
        return getRequestURL(request, true);
    }

    public static String getRequestURL(HttpServletRequest request, boolean isAddQueyString) {

        String context = request.getContextPath();
        String requestURI = request.getRequestURI();
        int port = request.getServerPort();
        String url = request.getScheme() + "://" + request.getServerName() +
                (port == 80 ? "" : ":" + String.valueOf(port)) + context + requestURI;
        if (isAddQueyString) {
            String query = request.getQueryString();
            if (query != null && query.length() > 0) {
                url = url + "?" + query;
            }
        }
        return url;
    }

    /**
     * 将url 中去除某个参数
     * @param url
     * @param param
     * @return
     */
    public static String urlDislodgeParam(String url, String param) {


        int codeIndex = url.indexOf(param+"=");
        String urlFirst = url.substring(0, codeIndex - 1);
        String urlLast = url.substring(codeIndex, url.length());
        if (urlLast.indexOf("&") == -1) {
            int index = urlLast.indexOf("#");
            if(index == -1) {
                url = urlFirst;
            }else {
                url = urlFirst + urlLast.substring(urlLast.indexOf("#"), urlLast.length());
            }
        } else {
            url = urlFirst + urlLast.substring(urlLast.indexOf("&"), urlLast.length());
        }
        return url;
    }


}
