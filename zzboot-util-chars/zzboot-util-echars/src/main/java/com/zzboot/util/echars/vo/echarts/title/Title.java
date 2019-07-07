package com.zzboot.util.echars.vo.echarts.title;


import com.zzboot.util.echars.vo.echarts.base.TextStyle;

@SuppressWarnings("serial")
public class Title implements  java.io.Serializable{
	
	//是否显示标题组件
	private boolean show  = true;
	
	//主标题文本，支持使用 \n 换行
	private String text ;
	
	//主标题文本超链接
	private String link ;
	
	//指定窗口打开主标题超链接
	//可选：	'self' 当前窗口打开    	'blank' 新窗口打开
	private String target = "blank";
	
	private TextStyle textStyle ;
	
	//标题文本水平对齐，支持 'left', 'center', 'right'，默认根据标题位置决定
	private String textAlign;
	
	//标题文本垂直对齐，支持 'top', 'middle', 'bottom'，默认根据标题位置决定
	private String textBaseline ;
	
	//副标题文本，支持使用 \n 换行
	private String subtext ;

	private String sublink  ;

	private String subtarget  ;

	private TextStyle  subtextStyle ;
	
	//标题内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距
	private int padding  = 5;
	
	//主副标题之间的间距
	private int itemGap  = 10;
	
	//grid 组件离容器左侧的距离
	//left 的值可以是像 20 这样的具体像素值，可以是像 '20%' 这样相对于容器高宽的百分比，也可以是 'left', 'center', 'right'。
	//如果 left 的值为'left', 'center', 'right'，组件会根据相应的位置自动对齐。
	private String left = "center";

	private String top  ;

	private String right ;

	private String bottom ;
	
	//标题背景色，默认透明   transparent
	private String backgroundColor  ;
	

	//图例的边框颜色 #ccc
	private String borderColor ;
	
	//图例的边框线宽。
	private int borderWidth ;
	
	//图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果
	private int shadowBlur ;
	
	//阴影颜色
	private String shadowColor ;
	
	//阴影水平方向上的偏移距离
	private int shadowOffsetX ;
	
	//阴影垂直方向上的偏移距离。
	private int shadowOffsetY ;

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public String getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	public String getTextBaseline() {
		return textBaseline;
	}

	public void setTextBaseline(String textBaseline) {
		this.textBaseline = textBaseline;
	}

	public String getSubtext() {
		return subtext;
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}

	public String getSublink() {
		return sublink;
	}

	public void setSublink(String sublink) {
		this.sublink = sublink;
	}

	public String getSubtarget() {
		return subtarget;
	}

	public void setSubtarget(String subtarget) {
		this.subtarget = subtarget;
	}

	public TextStyle getSubtextStyle() {
		return subtextStyle;
	}

	public void setSubtextStyle(TextStyle subtextStyle) {
		this.subtextStyle = subtextStyle;
	}

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getItemGap() {
		return itemGap;
	}

	public void setItemGap(int itemGap) {
		this.itemGap = itemGap;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
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

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	public int getShadowBlur() {
		return shadowBlur;
	}

	public void setShadowBlur(int shadowBlur) {
		this.shadowBlur = shadowBlur;
	}

	public String getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(String shadowColor) {
		this.shadowColor = shadowColor;
	}

	public int getShadowOffsetX() {
		return shadowOffsetX;
	}

	public void setShadowOffsetX(int shadowOffsetX) {
		this.shadowOffsetX = shadowOffsetX;
	}

	public int getShadowOffsetY() {
		return shadowOffsetY;
	}

	public void setShadowOffsetY(int shadowOffsetY) {
		this.shadowOffsetY = shadowOffsetY;
	}
	
	
	 

}
