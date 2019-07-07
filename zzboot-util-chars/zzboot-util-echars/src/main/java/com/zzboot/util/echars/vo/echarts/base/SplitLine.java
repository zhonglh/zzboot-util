package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class SplitLine implements java.io.Serializable{
	
	private boolean show = false;
	
	private LineStyle lineStyle;

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	
	
	

}
