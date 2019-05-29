package com.zzboot.util.base.data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 公共方法
 * 主要用于Excel
 * @author Administrator
 */
public class PubMethod {


    public static String obj2String(Object val){
        if(val == null){
            return null;
        }

        Class fieldClz = val.getClass() ;
        if(fieldClz == String.class){
            return (String)val;
        }else if(fieldClz == int.class || fieldClz == Integer.class){
            return String.valueOf(val);
        }else if(fieldClz == long.class || fieldClz == Long.class){
            return String.valueOf(val);
        }else if(fieldClz == float.class || fieldClz == Float.class){
            return String.valueOf(val);
        }else if(fieldClz == double.class || fieldClz == Double.class){
            return String.valueOf(val);
        }else if(fieldClz == BigDecimal.class){
            return ((BigDecimal )val).toString();
        }else if(fieldClz == boolean.class || fieldClz == Boolean.class){
            return String.valueOf(val);
        }else if(fieldClz == java.util.Date.class  || fieldClz == java.sql.Date.class ){
            return DateKit.fmtDateToYMD((java.util.Date)val);
        }else if(fieldClz == Timestamp.class){
            return DateKit.dateTo19String((java.util.Date)val);
        }else {
            throw new RuntimeException("列格式错误");
        }


    }

    /**
     * 获取转换后的类型
     * @param field
     * @param val
     * @return
     */
    public static Object getObject(Field field , Object val){
        if(field == null || val == null){
            return null;
        }

        if(val.getClass() == field.getType()){
            return val;
        }else if(val.getClass().isAssignableFrom(field.getType())){
            return val;
        }else {
            if(val instanceof String){
                return getObject(field.getType() , (String)val);
            }else if(val instanceof Double || val.getClass() == double.class){
                return getObject(field.getType() , (double)val);
            }else if(val instanceof java.util.Date){
                return getObject(field.getType() , (java.util.Date)val);
            }else {
                throw new RuntimeException("列格式错误");
            }
        }


    }




    public static Object getObject(Class fieldClz , String val){

        if(fieldClz == null || val == null || val.isEmpty()){
            return null;
        }
        if(fieldClz == String.class){
            return (String)val;
        }else if(fieldClz == int.class || fieldClz == Integer.class){
            return new Integer(distaa(val));
        }else if(fieldClz == long.class || fieldClz == Long.class){
            return new Long(distaa(val));
        }else if(fieldClz == float.class || fieldClz == Float.class){
            return new Float (val);
        }else if(fieldClz == double.class || fieldClz == Double.class){
            return new Double (val);
        }else if(fieldClz == BigDecimal.class){
            return new BigDecimal (val);
        }else if(fieldClz == boolean.class || fieldClz == Boolean.class){
            return Boolean.valueOf(val);
        }else if(fieldClz == java.util.Date.class  || fieldClz == java.sql.Date.class ){
            return DateKit.fmtStrToDate(val);
        }else if(fieldClz == Timestamp.class){
            return new Timestamp(DateKit.fmtStrToDate(val).getTime());
        }else {
            throw new RuntimeException("列格式错误");
        }
    }


    public static Object getObject(Class fieldClz , double val){

        if(fieldClz == null ){
            return null;
        }
        if(fieldClz == String.class){
            return String.valueOf(val);
        }else if(fieldClz == int.class || fieldClz == Integer.class){
            return new Integer(distaa(String.valueOf(val)));
        }else if(fieldClz == long.class || fieldClz == Long.class){
            return new Long(distaa(String.valueOf(val)));
        }else if(fieldClz == float.class || fieldClz == Float.class){
            return new Float (val);
        }else if(fieldClz == double.class || fieldClz == Double.class){
            return new Double (val);
        }else if(fieldClz == BigDecimal.class){
            return new BigDecimal (String.valueOf(val));
        }else if(fieldClz == boolean.class || fieldClz == Boolean.class){
            if(val == 1){
                return true;
            }else {
                return false;
            }
        }else if(fieldClz == java.util.Date.class  || fieldClz == java.sql.Date.class ){
            throw new RuntimeException("列格式错误");
        }else if(fieldClz == Timestamp.class){
            throw new RuntimeException("列格式错误");
        }else {
            throw new RuntimeException("列格式错误");
        }
    }



    public static Object getObject(Class fieldClz , java.util.Date val) {

        if (fieldClz == null || val == null) {
            return null;
        }

        if (fieldClz == java.util.Date.class || fieldClz == java.sql.Date.class) {
            return val;
        } else if (fieldClz == Timestamp.class) {
            return new Timestamp(val.getTime());
        }else {
            throw new RuntimeException("列格式错误");
        }
    }


    public static String distaa(String val){
        int index = val.indexOf("\\.");
        if(index != -1) {
            val = val.substring(0, index);
        }
        return val;
    }


    public static void main(String[] args) {
        System.out.println(Integer.class.getName());
        System.out.println(int.class.getName());
        System.out.println(int.class == Integer.class);
        System.out.println(int.class .isAssignableFrom( Integer.class));
    }

}
