package com.zzboot.util.base.data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class BusinessUtil {

	/**
	 * 整理提示信息
	 * @param messages
	 * @return
     */
	public static String reorganizeMessage(List<String> messages){
		if(messages == null || messages.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		for(String message : messages){
			if(StringUtil.isNotEmpty(message)){
				sb =  sb.append(message).append("<br>");
			}
		}
		return sb.toString();
	}

	/**
	 * 批次号生成
	 * @return
	 */
	public static String getBatchNo() {
		return String.valueOf(System.currentTimeMillis()) + UUID.randomUUID().toString();
	}

	/**
	 * 验证字符串
	 * @param regex		验证规则
	 * @param str		验证字符串
	 * @return			是否验证通过
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 对数字处理， 去除excel中容易将字符串读为数字
	 * 对于数字， 直接返回， 如果不是数字，将小数点及后面的全部删除
	 * @param data			数据
	 * @return				
	 */
	public static String processNumber(String data){
		return processNumber(false,data);
	}
	/**
	 *BigDecimal四舍五入且保留两位小数
	 * @param  e		数据
	 * @param      b	              小数(用法说明：如果是乘以100，则b="100";如果是除以100，则b="0.01")
	 * @return	
	 */
	public static BigDecimal runBigDecimal(BigDecimal e, String b){
		BigDecimal number = new BigDecimal(b);
		if(e!=null){
			return e.multiply(number).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else{
			return null; 
		}
	}
	/**
	 *BigDecimal乘以100后，四舍五入且保留任意小数位数
	 * @param  e		数据
	 * @param  c	              正整数(用法说明：如果对结果保留4位小数，则c=4)
	 * @return	
	 */
	public static BigDecimal MulBigDecimal(BigDecimal e, int c){
		BigDecimal number = new BigDecimal(100);
		if(e!=null){
			return e.multiply(number).setScale(c, BigDecimal.ROUND_HALF_UP);
		}else{
			return null; 
		}
	}
	/**
	 *BigDecimal除以100后，四舍五入且保留任意小数位数
	 * @param e		数据
	 * @param        c	              正整数(用法说明：如果对结果保留4位小数，则c=4)
	 * @return	
	 */
	public static BigDecimal DivBigDecimal(BigDecimal e, int c){
		BigDecimal number = new BigDecimal(0.01);
		if(e!=null){
			return e.multiply(number).setScale(c, BigDecimal.ROUND_HALF_UP);
		}else{
			return null; 
		}
	}
	public static String processNumber(boolean isNumber, String data){
		if(isNumber) {
			return data;
		}
		
		if(data != null){
			if(data.indexOf(".")>0 &&data.indexOf(",")<=0){
				String text = data.substring(data.indexOf(".")+1);
				if(!text.isEmpty()){
					if(!BusinessUtil.match("^[0]{1,}$",text)) {
						return data;
					}
				}
				return data.substring(0,data.indexOf("."));
			}
			
		}
		return data;
	}
	
	
	public static String processJson(String val){
		if(val == null || val.isEmpty()) {
			return val;
		}
		else {
			return val.replace("\\","\\\\").replace("\"", "\\\"");
		}
	}


	
	



}
