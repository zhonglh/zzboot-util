package com.zzboot.util.base.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 泛型工具类
 */

public class GenericsHelper {
	/**
	 * 通过反射,获得指定类的父类的泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<Buyer>
	 * 
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承范型父类
	 * @param index
	 *            泛型参数所在索引,从0开始.
	 * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		if (!(genType instanceof ParameterizedType)) {
			genType = clazz.getSuperclass().getGenericSuperclass();
		}

		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
		// DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		if(params[index] instanceof Class)
			return (Class) params[index];
		else return Object.class;
	}

	/**
	 * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如BuyerServiceBean extends DaoSupport<Buyer>
	 * 
	 * @param clazz
	 *            clazz 需要反射的类,该类必须继承泛型父类
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得方法返回值泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
	 * 
	 * @param method
	 *            method 方法
	 * @param index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getMethodGenericReturnType(Method method, int index) {
		Type returnType = method.getGenericReturnType();
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			if (index >= typeArguments.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			if(typeArguments[index] instanceof Class)
			return (Class) typeArguments[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得方法返回值第一个泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
	 * 
	 * @param method
	 *            method 方法
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getMethodGenericReturnType(Method method) {
		return getMethodGenericReturnType(method, 0);
	}

	/**
	 * 通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String,
	 * Buyer> maps, List<String> names){}
	 * @param method 方法
	 * @param index 第几个输入参数
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> getMethodGenericParameterTypes(Method method, int index) {
		List<Class> results = new ArrayList<Class>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (index >= genericParameterTypes.length || index < 0) {
			throw new RuntimeException("你输入的索引"
					+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
		}
		Type genericParameterType = genericParameterTypes[index];
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				Class parameterArgClass = (Class) parameterArgType;
				results.add(parameterArgClass);
			}
			return results;
		}
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List< List<Class>> getMethodAllParameterTypes(Method method) {
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if(genericParameterTypes == null) return null;
		
		List< List<Class>> allResults = new ArrayList<List<Class>>();
		
		for(int index=0; index<genericParameterTypes.length;index++){

			List<Class> results = new ArrayList<Class>();
			Type genericParameterType = genericParameterTypes[index];
			

			
			putParameterTypes(genericParameterType,results);

			allResults.add(results);
		}
		return allResults;
	}
	

	@SuppressWarnings("unchecked")
	public static List<Class> getMethodAllParameterTypes(Method method, int index) {
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if(genericParameterTypes == null) return null;		
		List< List<Class>> allResults = new ArrayList<List<Class>>();
		List<Class> results = new ArrayList<Class>();
		Type genericParameterType = genericParameterTypes[index];
		putParameterTypes(genericParameterType,results);
		return results;
	}
	
	private static void putParameterTypes(Type genericParameterType, List<Class> results){
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				putParameterTypes(parameterArgType,results);
				//Class parameterArgClass = (Class) parameterArgType;
				//results.add(parameterArgClass);
			}
		}else {
			if(genericParameterType instanceof Class){
				results.add((Class)genericParameterType);
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Type> getMethodParameterTypes(Method method) {
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if(genericParameterTypes == null) return null;

		List<Type> results = new ArrayList<Type>();
		
		for(int index=0; index<genericParameterTypes.length;index++){

			Type genericParameterType = genericParameterTypes[index];
			
				
				
			results.add(genericParameterType);
			

		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public static Type getMethodReturnTypes(Method method) {
		Type genericReturnTypes = method.getGenericReturnType();
		return genericReturnTypes;
	}
	
	public static List<Class> getMethodReturnAllTypes(Method method) {
		Type genericReturnTypes = method.getGenericReturnType();
		if(genericReturnTypes == null || genericReturnTypes.toString().equals("void")) return null;
		List<Class> results = new ArrayList<Class>();
		putParameterTypes(genericReturnTypes,results);
		return results;
	}		
	
	
	
	
	
	public static List<Integer> calculation(Map<String, Object> parameters, String prefix){
		List<Integer> list = new ArrayList<Integer>();

		for(String key : parameters.keySet()){
			if(key.startsWith(prefix)){
				if(key.length()-prefix.length()>=3){
					key = key.substring(prefix.length());
					if(key.charAt(0)=='['){
						if(key.indexOf("]")>=2){
							key = key.substring(1,key.indexOf("]"));
							try{
								Integer number = new Integer(key);
								if(number.intValue()>=0) {
									if(!list.contains(number)) list.add(number);
								}
							}catch(Exception e){
								
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 根据类型获取泛型类型
	 * @param genericParameterType
	 * @return List<Type>
	 */
	public static List<Type> getTypeGenericParameterType(Type genericParameterType){
		List<Type> results = new ArrayList<Type>();
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				results.add(parameterArgType);
			}
		}else {
			results.add(genericParameterType);
		}
		return results; 
		
	}
	
	public static boolean isGenericParameter(Type genericParameterType){
		return genericParameterType instanceof ParameterizedType;
	}
	

	/**
	 * 通过反射,获得方法输入参数第一个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, Buyer>
	 * maps, List<String> names){}
	 * 
	 * @param Method  方法
	 * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
	 */
	@SuppressWarnings("unchecked")
	public static List<Class> getMethodGenericParameterTypes(Method method) {
		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {
				throw new RuntimeException("你输入的索引"
						+ (index < 0 ? "不能小于0" : "超出了参数的总数"));
			}
			if(fieldArgTypes[index] instanceof Class)
			return (Class) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
	 * 
	 * @param Field
	 *            field 字段
	 * @param int index 泛型参数所在索引,从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
	 *         <code>Object.class</code>
	 */
	@SuppressWarnings("unchecked")
	public static Class getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}
	
	
	
	
	

}
