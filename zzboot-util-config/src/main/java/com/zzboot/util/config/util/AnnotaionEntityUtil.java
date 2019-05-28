package com.zzboot.util.config.util;


import com.zzboot.util.config.annotaions.*;

/**
 * @author Administrator
 */
public class AnnotaionEntityUtil {

    /**
     * 获取属性(字段)是否必填
     * @param dbAnnotation
     * @param fkAnnotation
     * @param dictAnnotation
     * @param pageAnnotation
     * @return
     */
    public static boolean isRequired(EntityAttrDBAnnotation dbAnnotation ,
                                     EntityAttrFkAnnotation fkAnnotation ,
                                     EntityAttrDictAnnotation dictAnnotation,
                                     EntityAttrPageAnnotation pageAnnotation){
        if(!pageAnnotation.required()){
            return false;
        }
        if(dbAnnotation != null){
            return dbAnnotation.notNull();
        }
        if(fkAnnotation != null){
            return fkAnnotation.dbColumnNotNull();
        }
        if(dictAnnotation != null){
            return dictAnnotation.dbColumnNotNull();
        }

        throw new RuntimeException("代码编写错误， 没有在属性中配置注解( EntityAttrDBAnnotation/EntityAttrFkAnnotation/EntityAttrDictAnnotation/EntityAttrPageAnnotation )");
    }


    /**
     * 获取属性(字段)最大长度
     * @param dbAnnotation
     * @param fkAnnotation
     * @param dictAnnotation
     * @param pageAnnotation
     * @return
     */
    public static int  maxLength(EntityAttrDBAnnotation dbAnnotation ,
                              EntityAttrFkAnnotation fkAnnotation ,
                              EntityAttrDictAnnotation dictAnnotation,
                              EntityAttrPageAnnotation pageAnnotation){

        int pageMaxLength = pageAnnotation.maxLength();

        if(dbAnnotation != null){
            return Math.min(pageMaxLength , dbAnnotation.attrLength());
        }
        if(fkAnnotation != null){
            return Math.min(pageMaxLength , fkAnnotation.dbColumnLength());
        }
        if(dictAnnotation != null){
            return Math.min(pageMaxLength , dictAnnotation.dbColumnLength());
        }

        return pageMaxLength;

    }

    /**
     * 获取小数点位数
     * @param dbAnnotation
     * @param fkAnnotation
     * @return
     */
    public static int  decimalsLength(EntityAttrDBAnnotation dbAnnotation ,
                          EntityAttrFkAnnotation fkAnnotation ){



        if(dbAnnotation != null){
            return dbAnnotation.attrDecimals();
        }
        if(fkAnnotation != null){
            return fkAnnotation.dbColumnDecimals();
        }
        throw new RuntimeException("代码编写错误， 没有在属性中配置注解( EntityAttrDBAnnotation/EntityAttrFkAnnotation/EntityAttrDictAnnotation/EntityAttrPageAnnotation )");

    }





}
