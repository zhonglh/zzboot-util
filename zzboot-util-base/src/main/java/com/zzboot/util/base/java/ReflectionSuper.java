package com.zzboot.util.base.java;

import com.zz.bms.util.base.data.StringUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 反射工具类.
 * 提供访问私有变量,获取泛型类型Class, 提取集合中元素的属性, 转换字符串到对象等Util函数.
 * @author zhonglh
 */
public class ReflectionSuper {

	private static Logger logger = Logger.getLogger(ReflectionSuper.class);


	public static void main(String[] args) {
		HashMap<String,Object> hm = new HashMap<String,Object>();
		ReflectionSuper.getSuperClassGenricType(hm.getClass());
	}

	/**
	 * 判断两个类之间 接口是否相同，或是否是其超类或超接口
	 * @param p
	 * @param c
	 * @return
	 */
	public static boolean isAssignableFrom(Class p , Class c){
		if(p == null || c == null){
			return false;
		}

		if(p.isAssignableFrom(c) || c.isAssignableFrom(p)){
			return true;
		}else {
			return false;
		}
	}

	
	
	@SuppressWarnings("rawtypes")
	public static List<Class> getAllParentClass(Class class_){
		return getAllParentClass(class_ , Object.class);
	}


	@SuppressWarnings("rawtypes")
	public static List<Class> getAllParentClass(Class class_ , Class stop){

		Class clz = class_;

		List<Class> list = new ArrayList<Class>();
		list.add(clz);

		while(true){
			clz = clz.getSuperclass();
			if(clz == null) {
				return list;
			}
			if(clz == Object.class) {
				return list;
			}
			if(clz.getName().equals(stop.getName())){
				return list;
			}
			list.add(clz);

		}
	}



	public static Map<Class,Class> getAllInterfaceClass(List<Class> list){
		Map<Class,Class> sets = new HashMap<Class,Class>();
		if(list == null || list.isEmpty()) {
			return sets;
		}
		try {
			for (Class c : list) {
				Class<?>[] is = c.getInterfaces();
				if (is != null && is.length > 0) {
					for (Class cla : is) {
						sets.put(cla, cla);
					}
				}
			}
			return sets;
		}catch(Exception e){
			return sets;
		}
	}


	/**
	 * 调用Getter方法.
	 */
	public static Object invokeGetterMethod(Object target, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		return invokeMethod(target, getterMethodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 调用Setter方法.使用value的Class来查找Setter方法.
	 */
	public static void invokeSetterMethod(Object target, String propertyName, Object value) {
		invokeSetterMethod(target, propertyName, value, null);
	}

	/**
	 * 调用Setter方法.
	 * 
	 * @param propertyType
	 *            用于查找Setter方法,为空时使用value的Class替代.
	 */
	public static void invokeSetterMethod(Object target, String propertyName, Object value,
                                          Class<?> propertyType) {
		Class<?> type = propertyType != null ? propertyType : value.getClass();
		String setterMethodName = "set" + StringUtils.capitalize(propertyName);
		invokeMethod(target, setterMethodName, new Class[] { type }, new Object[] { value });
	}
	
	
	
	
	
	 public static Field[] getAllField(Class<?> clazz) {
	        ArrayList<Field> fieldList = new ArrayList<Field>();
	        
	        List<Class> clzs = getAllParentClass(clazz);
	        
	        for(Class clz : clzs){
		        Field[] dFields = clz.getDeclaredFields();
		        if (null != dFields && dFields.length > 0) {
		            fieldList.addAll(Arrays.asList(dFields));
		        }
	        }
	 
	     
	        Field[] result=new Field[fieldList.size()];
	        fieldList.toArray(result);
	        return result;
	    }




	public static Field[] getAllField(Class<?> clazz , Class stop) {
		ArrayList<Field> fieldList = new ArrayList<Field>();

		List<Class> clzs = getAllParentClass(clazz, stop);

		for(Class clz : clzs){
			Field[] dFields = clz.getDeclaredFields();
			if (null != dFields && dFields.length > 0) {
				fieldList.addAll(Arrays.asList(dFields));
			}
		}


		Field[] result=new Field[fieldList.size()];
		fieldList.toArray(result);
		return result;
	}

	/**
	 * 直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName
					+ "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e);
			
		}
		return result;
	}

	/**
	 * 获取对像地属性
	 */
	public static Field[] getFields(final Object object) {
		return object.getClass().getDeclaredFields();
	}

	/**
	 * 直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName
					+ "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e);
		}
	}

	/**
	 * 直接调用对象方法, 无视private/protected修饰符.
	 */
	public static Object invokeMethod(final Object object, final String methodName,
                                      final Class<?>[] parameterTypes, final Object[] parameters) {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName
					+ "] on target [" + object + "]");
		}

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField.
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强行设置Field可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredMethod.
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {


		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {

			}
		}
		return null;
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * eg.
	 * public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();


		if (!(genType instanceof ParameterizedType)) {
			genType = clazz.getSuperclass().getGenericSuperclass();
		}

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
					+ "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		if(params[index] instanceof Class) {
			return (Class) params[index];
		}else{
			return Object.class;
		}
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List convertElementPropertyToList(final Collection collection,
                                                    final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertyName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String convertElementPropertyToString(final Collection collection,
                                                        final String propertyName, final String separator) {
		List list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value
	 *            待转换的字符串
	 * @param toType
	 *            转换目标类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertStringToObject(String value, Class<T> toType) {
		try {
			return (T) ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 将反射时的checked exception转换为unchecked exception.
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		return convertReflectionExceptionToUnchecked(null, e);
	}

	public static RuntimeException convertReflectionExceptionToUnchecked(String desc, Exception e) {
		desc = (desc == null) ? "Unexpected Checked Exception." : desc;
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException(desc, e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException(desc, ((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException(desc, e);
	}

	public static final <T> T getNewInstance(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	public static boolean isHaveReturnOfMethod(String classname, String methodname){
		
		try {
			Method theMethod = getMethod(classname,methodname);
			Class genericReturnTypes = theMethod.getReturnType();
			if( void.class.equals(genericReturnTypes)  ) return false;
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}
	

	
	
	/**
	 * 根据类全名称返回类全名称和方法名称
	 * @param classMethodName		类全名称加方法名
	 * @return String[]
	 */
	public static String[] getMethodClassName(String classMethodName){

		
		String classname = "";
		String methodname = "";

		StringBuilder stringBuilder = new StringBuilder();
		String[] splitsStr = classMethodName.split("\\.");
		if(splitsStr == null || splitsStr.length<2) {return null;}
		for(int i=0;i<splitsStr.length-1;i++) {
			stringBuilder.append(splitsStr[i]).append(".");
		}

		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		

		classname = stringBuilder.toString();
		methodname = splitsStr[splitsStr.length-1];
		
		
		return new String[]{classname,methodname};
		
	}
	

	
	
	
	
	
	/**
	 * 返回类 中方法句柄
	 * @param classmethod 	类 方法全名称
	 */
	public static Method getMethod(String classmethod)throws Exception {
		return getMethod(getMethodClassName(classmethod));
	}

	public static Method getMethod(String classmethod[])throws Exception {
		return getMethod(classmethod[0],classmethod[1]);
	}
	public static Method getMethod(String classname, String methodname)throws Exception {
		
		if(StringUtil.isEmpty(classname) || StringUtil.isEmpty(methodname) ) {
			return null;
		}
		
		try {
			
			Class clz = Class.forName(classname);
			Method[] methods = clz.getMethods();
			for(Method theMethod : methods){
				String theMethodName = theMethod.getName();
				if(theMethodName.equals(methodname)){
					return theMethod;
				}
			}
			
			//throw new Exception("  method not found  " + classname+"."+methodname);
			return null;
		} catch(ClassNotFoundException e) {
			throw e;
		}catch (Exception e) {
			throw e;
		}
		
	}	
	
	
	
	
	
	
	
	
	
	public static Method[] getDeclaredMethods(Class clz, Object... param){
		boolean isSort = false;
		String regex = "";
		if(param.length>=1 && param[0]!=null) {
			isSort = ((Boolean)param[0]).booleanValue();
		}
		if(param.length>=2 && param[1]!=null) {
			regex = param[1].toString();
		}
		List<Method> list = new ArrayList<Method>();
		Method[] ms = clz.getDeclaredMethods();
		//
		if(regex!=null && regex.length()>0){
			for(Method md : ms) {
				Pattern p = Pattern.compile(regex);
				Matcher matcher = p.matcher(md.getName());
				boolean b = matcher.find();
				if (b) {
					list.add(md);
				}
			}
		}else {
			list = Arrays.asList(ms);
		}
		
		Method[] returnArr = new Method[list.size()];
		
		returnArr = list.toArray(returnArr);
		return returnArr;
	}
	
	

	public static Method[] getGetMethod(Class clz){
		List<Method> mnamelist =new ArrayList<Method>();
		Method[] ms = getDeclaredMethods(clz);
		if(ms == null || ms.length == 0) {
			return null;
		}
		for(Method md : ms){
			String theMethodname = md.getName();
			if(md.getName().startsWith("get")||md.getName().startsWith("is")){
				if(theMethodname.equals("getClass")) {
					continue;
				}
				Class[] param = md.getParameterTypes();
				if(param!=null && param.length>0) {
					continue;
				}
				if(md.getReturnType().getName().equals("void")) {
					continue;
				}
				if(theMethodname.startsWith("is")) {
					theMethodname = theMethodname.substring(2,3).toLowerCase() + theMethodname.substring(3);
				}
				else  {
					theMethodname = theMethodname.substring(3,4).toLowerCase() + theMethodname.substring(4);
				}
				mnamelist.add(md);
			}
		}
		
		
		
		if(mnamelist == null || mnamelist.size() == 0) return null;

		Method[] methodname = new Method[mnamelist.size()];
		
		return mnamelist.toArray(methodname);
	}
	
	
	public static String[] getPackageClassMethodName(String classmethodname){
		String theclassname = classmethodname.substring(0,classmethodname.lastIndexOf("."));
		String themethodname = classmethodname.substring(classmethodname.lastIndexOf(".")+1);
		String theinterfacename = theclassname.substring(theclassname.lastIndexOf(".")+1);
		String thepackage = theclassname.substring(0,theclassname.lastIndexOf("."));
		return new String[]{thepackage,theinterfacename,themethodname};
	}
	
	public static String[][] getGetMethodName(Object obj){
		
		if(obj == null) {return null;}
		Method[] ms = getDeclaredMethods(obj.getClass());
		if(ms == null || ms.length == 0) {
			return null;
		}
		
		String mns[][] = null;
		List<String[]> mnamelist =new ArrayList<String[]>();
		for(Method md : ms){
			
			if(md.getName().startsWith("get")||md.getName().startsWith("is")){
				
				String theMethodname = md.getName();
				String theMethodvalue = "";
				

				if(theMethodname.equals("getClass")) {
					continue;
				}

				Class[] param = md.getParameterTypes();
				if(param!=null && param.length>0) {
					continue;
				}
				if(md.getReturnType().getName().equals("void")) {
					continue;
				}
				
				if(theMethodname.startsWith("is")) {
					theMethodname = theMethodname.substring(2,3).toLowerCase() + theMethodname.substring(3);
				}
				else {
					theMethodname = theMethodname.substring(3,4).toLowerCase() + theMethodname.substring(4);
				}
				
				
				try {
					Object val = md.invoke(obj);
					if(val!=null) {
						theMethodvalue = val.toString();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				mnamelist.add(new String[]{theMethodname,theMethodvalue});
			}
		}
		
		if(mnamelist.size() == 0) {
			return null;
		}
		
		int index = 0;
		mns = new String[mnamelist.size()][];
		for(String[] name : mnamelist){
			mns[index++] = name;
		}
		
		return mns; 
	}

	
	

}