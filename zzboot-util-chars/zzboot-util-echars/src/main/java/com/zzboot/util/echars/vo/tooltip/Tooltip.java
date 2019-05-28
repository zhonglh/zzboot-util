package com.zzboot.util.echars.vo.tooltip;

/**
 * 提示框组件
 * @author zhonglihong
 * @date 2016年12月4日 下午10:07:47
 */
@SuppressWarnings("serial")
public class Tooltip implements  java.io.Serializable{
	
	//是否显示提示框组件，包括提示框浮层和 axisPointer。
	private boolean show  = true ;
	
	//是否显示提示框浮层，默认显示。只需tooltip触发事件或显示axisPointer而不需要显示内容时可配置该项为false。
	private boolean showContent = true ;
	
	//触发类型 , 可选：
	//'item'  数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
	//'axis'  坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
	private String trigger = "item";
	
	//提示框浮层内容格式器，支持字符串模板和回调函数两种形式
	private String formatter = "{a} <br/>{b} : {c} ({d}%)";
	
	private Integer padding;
	private String backgroundColor;
	private String borderColor;
	private Integer borderWidth;

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isShowContent() {
		return showContent;
	}

	public void setShowContent(boolean showContent) {
		this.showContent = showContent;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public Integer getPadding() {
		return padding;
	}

	public void setPadding(Integer padding) {
		this.padding = padding;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}

}
