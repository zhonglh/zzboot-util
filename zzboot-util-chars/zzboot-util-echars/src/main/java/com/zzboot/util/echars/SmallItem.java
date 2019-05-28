package com.zzboot.util.echars;


/**
 * 图标数据项
 * @author admin
 */
@SuppressWarnings("serial")
public class SmallItem implements java.io.Serializable{
	
	private String name;
	private double value;
	private String formula;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	

}
