package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class Yaxis  implements java.io.Serializable{
	

	private String type = "value";
	private String[] data;

	private SplitLine splitLine;
	private boolean scale;
	
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
	

}
