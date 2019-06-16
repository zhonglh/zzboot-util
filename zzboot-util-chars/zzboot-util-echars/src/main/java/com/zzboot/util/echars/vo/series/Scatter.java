package com.zzboot.util.echars.vo.series;


import com.zzboot.util.echars.vo.base.Label;

/**
 * 散点图表
 * @author Admin
 * @date 2016年12月4日 下午9:47:24
 */
@SuppressWarnings("serial")
public class Scatter extends Series implements  java.io.Serializable{
	
	
	
	private String type = "scatter";
	
	private Double symbolSize = 10.00 ;
	
	private double symbolRotate ;
	
	private Label label;
	
	private Label itemStyle;
	
	
	private String[][] data;


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getSymbolSize() {
		return symbolSize;
	}


	public void setSymbolSize(double symbolSize) {
		this.symbolSize = symbolSize;
	}


	public double getSymbolRotate() {
		return symbolRotate;
	}


	public void setSymbolRotate(double symbolRotate) {
		this.symbolRotate = symbolRotate;
	}


	public Label getLabel() {
		return label;
	}


	public void setLabel(Label label) {
		this.label = label;
	}


	public Label getItemStyle() {
		return itemStyle;
	}


	public void setItemStyle(Label itemStyle) {
		this.itemStyle = itemStyle;
	}


	public String[][] getData() {
		return data;
	}


	public void setData(String[][] data) {
		this.data = data;
	}

	

}
