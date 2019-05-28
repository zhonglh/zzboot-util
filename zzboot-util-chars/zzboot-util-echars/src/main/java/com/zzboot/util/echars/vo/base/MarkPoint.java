package com.zzboot.util.echars.vo.base;

import com.alibaba.fastjson.JSON;

@SuppressWarnings("serial")
public class MarkPoint implements java.io.Serializable{
	
	private String symbol;
	private int[] symbolSize;
	private int symbolRotate ;
	private String[] symbolOffset ;
	private boolean silent ;
	private Label  label ;
	private JSON[] data;
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
	public int getSymbolRotate() {
		return symbolRotate;
	}
	public void setSymbolRotate(int symbolRotate) {
		this.symbolRotate = symbolRotate;
	}
	public String[] getSymbolOffset() {
		return symbolOffset;
	}
	public void setSymbolOffset(String[] symbolOffset) {
		this.symbolOffset = symbolOffset;
	}
	public boolean isSilent() {
		return silent;
	}
	public void setSilent(boolean silent) {
		this.silent = silent;
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
