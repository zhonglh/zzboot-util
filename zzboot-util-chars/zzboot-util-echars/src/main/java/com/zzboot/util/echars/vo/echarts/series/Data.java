package com.zzboot.util.echars.vo.echarts.series;

@SuppressWarnings("serial")
public class Data implements java.io.Serializable{
	
	private String name;
	private double value;
	

	public Data( ) {
		super();
	}

	public Data( double value) {
		super();
		this.value = value;
	}
	
	public Data(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	
	
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
	
	
}
