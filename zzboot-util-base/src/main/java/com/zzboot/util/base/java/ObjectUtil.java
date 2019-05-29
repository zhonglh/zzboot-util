package com.zzboot.util.base.java;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtil {

	public static boolean isEmpty(Object obj){
		if(obj == null){  
			return true;  
		}

		if(obj.getClass().isArray()){  
			if(Array.getLength(obj) == 0){
				return true;  
			}
		}

		if(obj instanceof Collection<?>){
			Collection<?> collection = (Collection<?>) obj ;
			if(collection.isEmpty()){  
				return true;  
			}  
		}  

		if(obj instanceof Map<?,?>){
			Map<?,?> map = (Map<?,?>) obj ;
			if(map.isEmpty()){  
				return true;  
			}  
		}  
		return false;
	}

	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	/**
	 * 获取对象数量 普通对象返回0,数组或集合或Map对象返回元素个数,其他返回0
	 * @param obj
	 * @return
	 */
	public static int objSize(Object obj){
		if(obj == null){  
			return 0;  
		}

		if(obj.getClass().isArray()){  
			return Array.getLength(obj);
		}

		if(obj instanceof Collection<?>){
			Collection<?> collection = (Collection<?>) obj ;
			return collection.size();
		}  

		if(obj instanceof Map<?,?>){
			Map<?,?> map = (Map<?,?>) obj ;
			return map.size(); 
		}
		return 0;
	}

}
