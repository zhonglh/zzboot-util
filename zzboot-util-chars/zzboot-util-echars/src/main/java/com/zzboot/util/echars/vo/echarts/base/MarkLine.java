package com.zzboot.util.echars.vo.echarts.base;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class MarkLine implements java.io.Serializable{

	private boolean silent ;

	private String symbol;
	private int[] symbolSize;
	private int precision = 2;
	private Label  label ;
	private JSON[] data;
	public boolean isSilent() {
		return silent;
	}
	public void setSilent(boolean silent) {
		this.silent = silent;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int[] getSymbolSize() {
		return symbolSize;
	}
	public void setSymbolSize(int[] symbolSize) {
		this.symbolSize = symbolSize;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public JSON[] getData() {
		return data;
	}
	public void setData(JSON[] data) {
		this.data = data;
	}

}
