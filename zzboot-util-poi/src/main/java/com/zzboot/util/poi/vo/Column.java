package com.zzboot.util.poi.vo;

import com.zzboot.util.base.sorts.ISort;
import org.apache.poi.ss.usermodel.CellStyle;

import java.lang.reflect.Field;

/**
 * 列实体类型
 * @author Administrator
 */
public class Column implements ISort{


	/**
	 * 列序号 列所处的顺序号
	 * 对应Excel的列(从0开始)
	 * 比如12
	 */
	private int number;


	/**
	 * 对应列字符长度
	 */
	private int length;

	/**
	 * 对应列代码
	 * 例如 User 的  userName
	 */
	private String code;


	/**
	 * 对应列名称
	 * 例如 User 的  用户名
	 */
	private String name;

	/**
	 * 水平对齐方式
	 */
	private short alignment = CellStyle.ALIGN_GENERAL;


	/**
	 * 是否必填
	 */
	private boolean isRequired ;





	/**
	 * Excel 列的宽度
	 */
	private int width ;

	/**
	 * 列的下拉内容
	 */
	private String[] combo;



	/**
	 * 对应列属性
	 * 例如 User 实体类中的 String userName ;
	 */
	private Field field;





	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public short getAlignment() {
		return alignment;
	}

	public void setAlignment(short alignment) {
		this.alignment = alignment;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean required) {
		isRequired = required;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String[] getCombo() {
		return combo;
	}

	public void setCombo(String[] combo) {
		this.combo = combo;
	}

	@Override
	public int getSort() {
		return number;
	}

	@Override
	public String getSort2() {
		return code;
	}


}
