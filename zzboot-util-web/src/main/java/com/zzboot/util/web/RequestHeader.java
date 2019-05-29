package com.zzboot.util.web;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 */
public class RequestHeader {


    /**
     * 判断 移动端/PC端
     * @Title: isMobile
     * @param request
     * @return
     * @return: boolean
     */
    public static boolean isMobile(HttpServletRequest request) {
        List<String> mobileAgents = Arrays.asList("ipad", "iphone os", "rv:1.2.3.4", "ucweb", "android", "windows ce", "windows mobile");
        String ua = request.getHeader("User-Agent").toLowerCase();
        System.out.println(ua);
        for (String sua : mobileAgents) {
            if (ua.indexOf(sua) > -1) {
                //手机端
                return true;
            }
        }

        //PC端
        return false;
    }

    /**
     * 判断是否微信
     * @param req
     * @return
     */
    public static boolean isWx(HttpServletRequest req){


        String userAgent = req.getHeader("user-agent").toLowerCase();
        if(userAgent.indexOf("micromessenger")>-1){
            //微信客户端
            return true;
        }else{
            return false;
        }

    }
}
