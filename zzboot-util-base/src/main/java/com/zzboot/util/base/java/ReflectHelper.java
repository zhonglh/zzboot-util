package com.zzboot.util.base.java;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.regex.Pattern;


/**
 * @author Administrator
 * @desc 通过反射来动态调用get 和 set 方法
 */
@Slf4j
public class ReflectHelper {


	private Class cls;
	/**
	 * 传过来的对象
	 */
	private Object obj;

	/**
	 * 存放get方法
	 */
	private Hashtable<String, Method> getMethods = null;
	/**
	 * 存放set方法
	 */
	private Hashtable<String, Method> setMethods = null;
	
	
	private Hashtable<String, Field> fields = null;
	
	
	

	/**
	 * 定义构造方法 -- 一般来说是个pojo
	 * 
	 * @param o  目标对象
	 */
	public ReflectHelper(Object o) {
		obj = o;
		initMethods();
	}

	/**
	 * 
	 * @desc 初始化
	 */
	public void initMethods() {
		getMethods = new Hashtable<String, Method>();
		setMethods = new Hashtable<String, Method>();
		cls = obj.getClass();
		Method[] methods = cls.getMethods();
		// 定义正则表达式，从方法中过滤出getter / setter 函数.
		String gs = "get(\\w+)";
		Pattern getM = Pattern.compile(gs);
		String ss = "set(\\w+)";
		Pattern setM = Pattern.compile(ss);
		// 把方法中的"set" 或者 "get" 去掉
		String rapl = "$1";
		String param;
		for (int i = 0; i < methods.length; ++i) {
			Method m = methods[i];
			String methodName = m.getName();
			if (Pattern.matches(gs, methodName)) {
				param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
				getMethods.put(param, m);
			} else if (Pattern.matches(ss, methodName)) {
				param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();
				setMethods.put(param, m);
			} else {
				;
			}
		}
		  
		
		fields = new Hashtable<String,Field>();
		Field[] fs = cls.getDeclaredFields();
		if(fs != null){
			for(Field f : fs){
				fields.put(f.getName().toLowerCase(), f) ;
			}
		}
		
		
	}

	/**
	 * 
	 * @desc 调用set方法
	 */
	public boolean setMethodValue(String property, Object object) {
		Method m = setMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				// 调用目标类的setter函数
				m.invoke(obj, object);
				return true;
			} catch (Exception ex) {
				log.info("invoke getter on " + property + " error: " + ex.toString());
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * @desc 调用set方法
	 */
	public Object getMethodValue(String property) {
		Object value=null;
		Method m = getMethods.get(property.toLowerCase());
		if (m != null) {
			try {
				/**
				 * 调用obj类的setter函数
				 */
				value=m.invoke(obj, new Object[] {});
				
			} catch (Exception ex) {
				log.info("invoke getter on " + property + " error: " + ex.toString());
			}
		}else {
			return this.getFieldValue(property);
		}
		
		return value;
	}
	
	
	public Object getFieldValue(String property){
		Field f = fields.get(property.toLowerCase());
		if(f!=null){
			try {
				f.setAccessible(true);
				return f.get(obj);
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return null;
	}
	
	
	
}