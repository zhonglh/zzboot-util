package com.zzboot.util.echars.vo.base;

public class LineStyle {
	
	String[] color;
	
	private int width = 1;
	
	private String type = "solid";
	
	private String shadowBlur ;

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShadowBlur() {
		return shadowBlur;
	}

	public void setShadowBlur(String shadowBlur) {
		this.shadowBlur = shadowBlur;
	}

}
