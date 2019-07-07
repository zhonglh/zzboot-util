package com.zzboot.util.echars.vo.echarts.legend;


import com.zzboot.util.echars.vo.echarts.base.TextStyle;
import com.zzboot.util.echars.vo.echarts.tooltip.Tooltip;

@SuppressWarnings("serial")
public class Legend implements  java.io.Serializable{
	
	private String x = "center";
	private String y = "bottom";
	
	//图例组件离容器左侧的距离
	//left 的值可以是像 20 这样的具体像素值，可以是像 '20%' 这样相对于容器高宽的百分比，也可以是 'left', 'center', 'right'
	private String left = "auto";
	
	//图例组件离容器上侧的距离
	//top 的值可以是像 20 这样的具体像素值，可以是像 '20%' 这样相对于容器高宽的百分比，也可以是 'top', 'middle', 'bottom'。
	//如果 top 的值为'top', 'middle', 'bottom'，组件会根据相应的位置自动对齐。
	private String top = "auto";
	
	//图例组件离容器右侧的距离
	//right 的值可以是像 20 这样的具体像素值，可以是像 '20%' 这样相对于容器高宽的百分比
	private String right = "auto";
	
	//图例组件离容器下侧的距离。
	//bottom 的值可以是像 20 这样的具体像素值，可以是像 '20%' 这样相对于容器高宽的百分比
	private String bottom = "auto";
	
	//图例组件的宽度。默认自适应。
	private String width = "auto";
	
	//图例组件的高度。默认自适应。
	private String height  = "auto";
	
	//图例列表的布局朝向。
	//可选：
	//'horizontal'
	//'vertical'
	private String orient  = "horizontal";
	
	//图例标记和文本的对齐。默认自动，根据组件的位置和 orient 决定，当组件的 left 值为 'right' 以及纵向布局（orient 为 'vertical'）的时候为右对齐，及为 'right'。
	//可选： 	'auto'	'left'	'right'
	private String align = "auto";
	
	//图例内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距
	private int padding = 5;
	
	//图例每项之间的间隔。横向布局时为水平间隔，纵向布局时为纵向间隔。
	private int itemGap = 10;
	
	//图例标记的图形宽度。
	private int itemWidth = 25;
	
	//图例标记的图形高度
	private int itemHeight = 14;
	
	//用来格式化图例文本，支持字符串模板和回调函数两种形式
	//示例：
	// 使用字符串模板，模板变量为图例名称 {name}
	//formatter: 'Legend {name}'
	// 使用回调函数
	//formatter: function (name) {  return 'Legend ' + name; }
	private String formatter ;
	
	//图例选择的模式，控制是否可以通过点击图例改变系列的显示状态。默认开启图例选择，可以设成 false 关闭。
	//除此之外也可以设成 'single' 或者 'multiple' 使用单选或者多选模式。
	private boolean selectedMode = true;
	
	//图例关闭时的颜色。
	private String inactiveColor = "#ccc";
	
	private Object selected ;
	
	//图例的公用文本样式。
	private TextStyle textStyle ;
	
	//图例的 tooltip 配置
	private Tooltip tooltip ;
	
	//图例的数据数组。数组项通常为一个字符串，每一项代表一个系列的 name（如果是饼图，也可以是饼图单个数据的 name）。
	//图例组件会自动获取对应系列的颜色，图形标记（symbol）作为自己绘制的颜色和标记，特殊字符串 ''（空字符串）或者 '\n'（换行字符串）用于图例的换行。
	private LegendData[] data;
	
	//图例背景色，默认透明。
	private String backgroundColor = "transparent";
	
	//图例的边框颜色
	private String borderColor = "#ccc";
	
	//图例的边框线宽。
	private int borderWidth =1;
	
	//图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果
	private int shadowBlur ;
	
	//阴影颜色
	private String shadowColor ;
	
	//阴影水平方向上的偏移距离
	private int shadowOffsetX ;
	
	//阴影垂直方向上的偏移距离。
	private int shadowOffsetY ;

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

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getOrient() {
		return orient;
	}

	public void setOrient(String orient) {
		this.orient = orient;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
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

	public int getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(int itemWidth) {
		this.itemWidth = itemWidth;
	}

	public int getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public boolean isSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(boolean selectedMode) {
		this.selectedMode = selectedMode;
	}

	public String getInactiveColor() {
		return inactiveColor;
	}

	public void setInactiveColor(String inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	public Object getSelected() {
		return selected;
	}

	public void setSelected(Object selected) {
		this.selected = selected;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public LegendData[] getData() {
		return data;
	}

	public void setData(LegendData[] data) {
		this.data = data;
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

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	
	

}
