package com.zzboot.util.echars.vo.legend;


import com.zzboot.util.echars.vo.base.TextStyle;

@SuppressWarnings("serial")
public class LegendData implements  java.io.Serializable{

	//图例项的名称，应等于某系列的name值（如果是饼图，也可以是饼图单个数据的 name）
	private String name;
	
	//图例项的 icon
	//ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
	//也可以通过 'image://url' 设置为图片，其中 url 为图片的链接
	private String icon;
	
	//图例项的文本样式
	private TextStyle textStyle ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}
	
	
	
	
}
