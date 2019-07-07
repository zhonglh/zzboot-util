package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class Grid implements java.io.Serializable{
	
	private String left = "5%";
	private String right = "5%";
	private String top  = "5%";
	private String bottom  = "5%";
	private String width = "100%";
	private boolean containLabel = true;
	public String getLeft() {
		return left;
	}
	public void setLeft(String left) {
		this.left = left;
	}
	public String getRight() {
		return right;
	}
	public void setRight(String right) {
		this.right = right;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getBottom() {
		return bottom;
	}
	public void setBottom(String bottom) {
		this.bottom = bottom;
	}


	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public boolean isContainLabel() {
		return containLabel;
	}

	public void setContainLabel(boolean containLabel) {
		this.containLabel = containLabel;
	}
}
