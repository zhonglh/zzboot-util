package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class TextStyle implements java.io.Serializable{
	
	private String color = " #333";
	
	//文字字体的风格
	//可选：	'normal'	'italic'	'oblique'
	private String fontStyle = "normal";
	
	//文字字体的粗细
	//可选：'normal'  'bold'	'bolder'	'lighter'
	//100 | 200 | 300 | 400...
	private String fontWeight ;
	
	//文字的字体系列
	private String fontFamily = "sans-serif";
	
	//文字的字体大小
	private int fontSize = 12;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	
	

}
