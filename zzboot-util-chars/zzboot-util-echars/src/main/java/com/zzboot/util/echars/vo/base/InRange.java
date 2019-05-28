package com.zzboot.util.echars.vo.base;

public class InRange {
	
	private String symbol;
	private int[] symbolSize = {10,70};
	private String[] color = {"rgba(255,255,255,0.2)"};
	private double colorAlpha = 1;
	
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
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public double getColorAlpha() {
		return colorAlpha;
	}
	public void setColorAlpha(double colorAlpha) {
		this.colorAlpha = colorAlpha;
	}
	
	

}
