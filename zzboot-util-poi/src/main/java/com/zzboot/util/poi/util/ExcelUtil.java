package com.zzboot.util.poi.util;

import com.zzboot.util.base.data.PubMethod;
import com.zzboot.util.poi.exceptions.ExcelAbsenceException;
import com.zzboot.util.poi.exceptions.ExcelFormatException;
import com.zzboot.util.poi.exceptions.ExcelTypeMatchingException;
import com.zzboot.util.poi.vo.Column;
import com.zzboot.util.spring.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Excel工具类
 * @author Administrator
 */
public class ExcelUtil {

    private static Map<Class,Method> setErrorMethodMap = new ConcurrentHashMap<Class,Method>();
    private static Map<Class,Method> getErrorMethodMap = new ConcurrentHashMap<Class,Method>();



    public static Method getErrorMethod(Class clz){
        Method getError = getErrorMethodMap.get(clz);
        if(getError != null){
            return getError;
        }else {
            try {
                getError =  clz.getMethod("getErrorInfo");
                return getError;
            } catch (NoSuchMethodException e) {

            }
        }
        return null;
    }

    public static Method setErrorMethod(Class clz){
        Method setError = setErrorMethodMap.get(clz);
        if(setError != null){
            return setError;
        }else {
            try {
                setError =  clz.getMethod("setErrorInfo" , String.class);
                return setError;
            } catch (NoSuchMethodException e) {

            }
        }
        return null;
    }

    /**
     * Excel Row 转为对应的对象
     * @param fieldVlaues
     * @param columns
     * @param obj
     * @return
     */
    public static void row2Object(Object[] fieldVlaues , List<Column> columns , Object obj , boolean isAddNumber) throws ExcelAbsenceException,ExcelAbsenceException,ExcelTypeMatchingException{
        if(fieldVlaues == null || fieldVlaues.length == 0 || columns == null || columns.isEmpty() || obj == null){
            return ;
        }
        if(fieldVlaues.length < columns.size()){
            throw new ExcelAbsenceException();
        }
        int index = isAddNumber?1:0;


        Method setErrorMethod = setErrorMethod(obj.getClass());

        for(Column column : columns){
            ReflectionUtil.makeAccessible(column.getField());
            try {
                Object val = PubMethod.getObject(column.getField() , fieldVlaues[index]);
                column.getField().set(obj, val);
            }catch(RuntimeException e){
                if(setErrorMethod == null) {
                    throw new ExcelFormatException(0, index);
                }else {
                    try {
                        setErrorMethod.invoke(obj , column.getName()+" 列格式错误;");
                    } catch (Exception e1) {
                    }
                }
            }catch(Exception e){
                if(setErrorMethod == null) {
                    throw new ExcelTypeMatchingException(0,index);
                }else {
                    try {
                        setErrorMethod.invoke(obj , column.getName()+" 列匹配错误;");
                    } catch (Exception e1) {
                    }
                }
            }
            index++ ;
        }


    }
}
