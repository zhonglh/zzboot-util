package com.zzboot.util.base.java;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * 
 * @author Administrator
 *
 */
public class GenericsUtils extends GenericsHelper{
	/**
	 * 根据实体得到实体的所有属性
	 * 
	 * @param objClass
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static String[] getColumnNames(String objClass) throws ClassNotFoundException {
		String[] wageStrArray = null;
		if (objClass != null) {
			Class class1 = Class.forName(objClass);
			Field[] field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < field.length; i++) {// 这里不多说了

				sb.append(field[i].getName());

				// 这是分割符 是为了去掉最后那个逗号

				// 比如 如果不去最后那个逗号 最后打印出来的结果是 "id,name,"

				// 去了以后打印出来的是 "id,name"
				if (i < field.length - 1) {
					sb.append(",");

				}
			}

			// split(",");这是根据逗号来切割字符串使字符串变成一个数组

			wageStrArray = sb.toString().split(",");
			return wageStrArray;
		} else {
			return wageStrArray;
		}
	}

	public static Object[] field2Value(Field[] f, Object o) throws Exception {
		Object[] value = new Object[f.length];
		for (int i = 0; i < f.length; i++) {
			value[i] = f[i].get(o);
		}
		return value;
	}


	/**
	 * 得到实体类
	 * 
	 * @param objClass
	 *            实体类包含包名
	 * @return
	 */
	public static Class getEntityClass(String objClass) {
		Class entityClass = null;
		try {
			entityClass = Class.forName(objClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return entityClass;
	}

	/**
	 * 定义字符集
	 * 
	 * @param
	 * @return
	 */
	private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' }; // 72个字符集

	/**
	 * 
	 * @param passLength
	 *            随机密码长度
	 * @return 随机密码数组
	 */
	public static String getPasswords(int passLength) {
		String passwords = "";// 新建一个长度为指定需要密码个数的字符串数组
		Random random = new Random();
		StringBuilder password = new StringBuilder("");// 保存生成密码的变量
		for (int m = 1; m <= passLength; m++) {// 内循环 从1开始到密码长度 正式开始生成密码
			password.append(chars[random.nextInt(62)]);// 为密码变量随机增加上面字符中的一个
		}
		passwords = password.toString();// 将生成出来的密码赋值给密码数组
		return passwords;
	}
	


}
