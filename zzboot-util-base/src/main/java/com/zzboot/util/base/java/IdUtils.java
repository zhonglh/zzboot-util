package com.zzboot.util.base.java;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by work on 2017/2/7.
 */
public class IdUtils {
    public static final String getId(){
        return  UUID.randomUUID().toString().replace("-","");
    }
    public static final String getGoodsSn(){
        return  UUID.randomUUID().toString().replace("-","").substring(20);
    }


    public static final boolean isEmpty(Serializable id){
        if(id == null){
            return true;
        }
        if(id instanceof String){
            return ((String)id).isEmpty();
        }else if(id instanceof Integer){
            return ((Integer)id).intValue() == 0;
        }else if(id instanceof Long){
            return ((Long)id).longValue() == 0;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(getId().length());
    }
}
