package com.zzboot.util.base.data;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ListUtils {

	/**
	 * 按新的Class对象复制
	 */
	public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass)
			throws Exception {
		if (source.size() == 0) {
			return new ArrayList();
		}
		List<E> res = new ArrayList<E>(source.size());
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(e, o);
			res.add(e);
		}
		return res;
	}
	
	public static boolean isNullOrEmpty(List list)
	{
		return list == null || list.isEmpty();
	}
	
	/**
	 * List 转 Map
	 * @param list
	 * @param keyName	作为Map中Key的属性名称
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static <T> Map<String, T> list2Map(List<T> list , String keyName) {
		if(list == null) {
			return null;
		}
		Map<String, T> map = new HashMap<String, T>();
		for(T t : list){
			Object key = null;
			try {
				key = PropertyUtils.getSimpleProperty(t, keyName);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			} catch (NoSuchMethodException e) {
			}
			if(key != null) {
				map.put(key.toString(), t);
			}
		}
		return map;
	}
	
	
	/**
	 * 去重
	 * 比较两个列表， 相同的都去除
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T>  List<T>[] removeDuplicate(List<T> list1 , List<T> list2) {
		List<T>[] array = new List[2];
		array[0] = list1;
		array[1] = list2;		
		
		if(array[0] == null || array[1] == null || array[0].isEmpty() || array[1].isEmpty()) {
			return array;
		}else {
			
			array[0] = new ArrayList<T>(list1);
			array[1] = new ArrayList<T>(list2);
			
			Collections.copy(array[0], list1);
			Collections.copy(array[1], list2);
			
		}
		
		Iterator<T> iter = array[0].iterator();
		while(iter.hasNext()){  
		    T t = iter.next(); 		    
		    if(array[1].contains(t)){
		    	removeCollection(array[1],t);
		    	iter.remove();
		    }
		    
		} 
		
		return array;
		
	}
	
	/**
	 * 在列表中移除一个元素
	 * @param list
	 * @param removeObj
	 */
	public static  <T> void removeCollection(Collection<T> list , T removeObj){
		
		if(list == null || list.isEmpty()) {
			return ;
		}
		if(removeObj  == null) {
			return ;
		}
		
		Iterator<T> iter = list.iterator();
		
		while(iter.hasNext()){  
			
		    T t = iter.next();  
		    if(t == null) {
		    	iter.remove();
			}
		    
		    else if(t.equals(removeObj)){
		    	iter.remove();		    	
		    }
		    
		} 
	}
	
	
	
}
