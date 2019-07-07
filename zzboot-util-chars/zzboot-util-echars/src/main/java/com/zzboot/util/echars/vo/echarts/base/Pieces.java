package com.zzboot.util.echars.vo.echarts.base;

public class Pieces implements java.io.Serializable{
	private double min;
	private double max;
	private String label;
	private double value;
	private String color;
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
