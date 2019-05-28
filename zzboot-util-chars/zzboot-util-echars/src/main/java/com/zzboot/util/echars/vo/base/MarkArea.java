package com.zzboot.util.echars.vo.base;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class MarkArea implements java.io.Serializable{
	

	private boolean silent ;
	private String symbol;	
	private Label itemStyle ;
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


	public Label getItemStyle() {
		return itemStyle;
	}


	public void setItemStyle(Label itemStyle) {
		this.itemStyle = itemStyle;
	}


	public JSON[] getData() {
		return data;
	}


	public void setData(JSON[] data) {
		this.data = data;
	}

}
