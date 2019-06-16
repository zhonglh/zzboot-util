package com.zzboot.util.spring;

import java.util.Locale;
import java.util.Map;


/**
 * Spring Bean 获取
 * @author Admin
 * @date 2016年5月30日 下午3:18:27
 */
public class SpringUtil {


	public static Object getBean(String name) {
		return ApplicationContextUtil.getContext().getBean(name);
	}



	public static <T> T getBean(Class<T> requiredType){
		T bean = ApplicationContextUtil.getContext().getBean(requiredType);
		if(bean != null ) {
			return bean;
		}
		else {
			return null;
		}
	}
	
	
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		Map<String, T> beansOfType = ApplicationContextUtil.getContext().getBeansOfType(type);
		if(beansOfType == null || beansOfType.isEmpty()) {
			return null;
		}else {
			return beansOfType;
		}
	}

	/**
	 * 获取国际化信息
	 * @param key
	 * @param args
     * @return
     */

	public static String getMessage(String key, Object... args){
		return getMessage(null ,key ,args);
	}

	public static String getMessage(Locale locale, String key, Object... args){


		if (null == locale) {
			locale = Locale.CHINA;
		}
		return ApplicationContextUtil.getContext().getMessage (key, args, locale);
	}



	/**
	 * 获取国际化信息
	 * @param locale
	 * @param key
	 * @param defaultMessage
	 * @param args
     * @return
     */
	public static String getMessage(Locale locale, String key, String defaultMessage, Object... args){
		if (null == locale) {
			locale = Locale.CHINA;
		}
		return ApplicationContextUtil.getContext().getMessage (key, args, defaultMessage, locale);
	}



	public static boolean containsBean(String name) {
		return ApplicationContextUtil.getContext().containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return ApplicationContextUtil.getContext().isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return ApplicationContextUtil.getContext().getType(name);
	}

}
