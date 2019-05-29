package com.zzboot.util.base.data;

/**
 * @author Administrator
 */
public class SqlKit {

    /**
     * 数组转为In的集合
     * @param vals
     * @return
     */
    public static String toInContent(String ... vals){
        if(vals == null || vals.length == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(String val : vals){
            sb.append("'").append(val).append("',");
        }
        sb.deleteCharAt(sb.length() -1 );
        return sb.toString();
    }



}
