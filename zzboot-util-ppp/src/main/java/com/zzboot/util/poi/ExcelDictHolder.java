package com.zzboot.util.poi;

import java.util.List;
import java.util.Map;

/**
 * 用来存储所有的字段信息
 * @author admin
 */
public class ExcelDictHolder {

    private static ThreadLocal<Map<String, List<String>>> dictMap = new ThreadLocal<Map<String, List<String>>>(){
        @Override
        public Map<String, List<String>> initialValue(){
            return null;
        }
    };

    public static void setDictMap(Map<String, List<String>>  map){
        dictMap.set(null);
        dictMap.set(map);
    }


    public static Map<String, List<String>> getDictMap(){
        return dictMap.get();
    }

}
