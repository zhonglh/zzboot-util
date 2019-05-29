package com.zzboot.util.base;


import com.zzboot.util.base.data.StringUtil;

import java.util.List;


/**
 * @author Administrator
 */
public class AssertUitil {



    public static final String paramError = "参数错误";


    public static  void assertNotNull(String srcString) throws RuntimeException {
        assertNotNull(srcString, paramError);
    }

    public static  void assertNotNull(String srcString,String alertMsg) throws RuntimeException{
        if(StringUtil.isEmpty(srcString)){
            throw new RuntimeException(alertMsg);
        }
    }


    public static  void assertNotNull(Integer srcInteger) throws RuntimeException{
        assertNotNull(srcInteger, paramError);
    }

    public static  void assertNotNull(Integer srcInteger,String alertMsg) throws RuntimeException{
        if(srcInteger == null){
            throw new RuntimeException(alertMsg);
        }
    }


    public static  void assertNotNull(Long srcLong) throws RuntimeException{
        assertNotNull(srcLong, paramError);
    }

    public static  void assertNotNull(Long srcLong,String alertMsg) throws RuntimeException{
        if(srcLong == null){
            throw new RuntimeException(alertMsg);
        }
    }


    public static  void assertNotNull(Object src) throws RuntimeException{
        assertNotNull(src, paramError);
    }

    public static  void assertNotNull(Object src,String alertMsg) throws RuntimeException{
        if(src == null){
            throw new RuntimeException(alertMsg);
        }
    }


    public static <T> void assertNotNull(List<T> list) throws RuntimeException{
        assertNotNull(list, paramError);
    }

    public static <T> void assertNotNull(List<T> list,String alertMsg) throws RuntimeException{
        if(list == null || list.isEmpty()){
            throw new RuntimeException(alertMsg);
        }
    }


    public static  void assertNotEqual(String src,String dest) throws RuntimeException{
        assertNotEqual(src,dest, paramError);
    }

    public static  void assertNotEqual(String src,String dest,String alertMsg) throws RuntimeException{
        if((StringUtil.isEmpty(src) || StringUtil.isEmpty(dest)  && !src.equals(dest))){
            throw new RuntimeException(alertMsg);
        }
    }


}
