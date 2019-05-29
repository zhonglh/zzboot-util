package com.zzboot.util.base.data;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class SerializableUtil {

    public static boolean isEmpty(Serializable id){
        if(id == null){
            return true;
        }

        if(id instanceof String){
            return ((String)id).isEmpty();
        }else if(id instanceof Long){
            return (Long)id == 0L;
        }else if(id instanceof Integer){
            return (Integer)id == 0;
        }else {
            return false;
        }

    }



    public static boolean isNotEmpty(Serializable id){
        return !isEmpty(id);
    }


}
