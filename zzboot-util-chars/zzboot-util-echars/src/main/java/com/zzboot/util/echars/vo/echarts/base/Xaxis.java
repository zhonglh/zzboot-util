package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class Xaxis implements java.io.Serializable{
	
	private String type = "value";
	private String[] data;
	private SplitLine splitLine;
	private boolean scale; 
	private int nameGap = 16;
	private AxisLabel axisLabel;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public SplitLine getSplitLine() {
		return splitLine;
	}
	public void setSplitLine(SplitLine splitLine) {
		this.splitLine = splitLine;
	}
	public boolean isScale() {
		return scale;
	}
	public void setScale(boolean scale) {
		this.scale = scale;
	}
	public int getNameGap() {
		return nameGap;
	}
	public void setNameGap(int nameGap) {
		this.nameGap = nameGap;
	}

	public AxisLabel getAxisLabel() {
		return axisLabel;
	}

	public void setAxisLabel(AxisLabel axisLabel) {
		this.axisLabel = axisLabel;
	}
}
