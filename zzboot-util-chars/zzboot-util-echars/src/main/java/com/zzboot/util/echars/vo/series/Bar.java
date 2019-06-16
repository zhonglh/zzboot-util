package com.zzboot.util.echars.vo.series;


import com.zzboot.util.echars.vo.base.Label;
import com.zzboot.util.echars.vo.base.MarkArea;
import com.zzboot.util.echars.vo.base.MarkLine;

/**
 * 条状图表
 * @author Admin
 * @date 2016年12月7日 下午6:42:01
 */
@SuppressWarnings("serial")
public class Bar extends Series implements  java.io.Serializable{
	
	private String type = "bar";
	
	//是否启用图例 hover 时的联动高亮。
	private boolean legendHoverLink  = true;
	
	//该系列使用的坐标系
	private String coordinateSystem = "cartesian2d";
	
	//图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等，
	//label选项在 ECharts 2.x 中放置于itemStyle.normal下，在 ECharts 3 中为了让整个配置项结构更扁平合理，
	//label 被拿出来跟 itemStyle 平级，并且跟 itemStyle 一样拥有 normal, emphasis 两个状态	
	private Label label ;
	
	private Label itemStyle ;
	
	//数据堆叠，同个类目轴上系列配置相同的stack值可以堆叠放置。
	private String stack ;
	
	private String barWidth ;
	
	private String barMaxWidth ;
	
	private int barMinHeight ;
	
	private String barGap ;
	
	private String barCategoryGap ;
	
	private Data[] data;
	
	//图表标注
	private MarkLine markPoint ;
	
	//图表标线
	private MarkLine  markLine  ;
	
	//图表标域
	private MarkArea markArea  ;

	public boolean isLegendHoverLink() {
		return legendHoverLink;
	}

	public void setLegendHoverLink(boolean legendHoverLink) {
		this.legendHoverLink = legendHoverLink;
	}

	public String getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(String coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Label getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Label itemStyle) {
		this.itemStyle = itemStyle;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getBarWidth() {
		return barWidth;
	}

	public void setBarWidth(String barWidth) {
		this.barWidth = barWidth;
	}

	public String getBarMaxWidth() {
		return barMaxWidth;
	}

	public void setBarMaxWidth(String barMaxWidth) {
		this.barMaxWidth = barMaxWidth;
	}

	public int getBarMinHeight() {
		return barMinHeight;
	}

	public void setBarMinHeight(int barMinHeight) {
		this.barMinHeight = barMinHeight;
	}

	public String getBarGap() {
		return barGap;
	}

	public void setBarGap(String barGap) {
		this.barGap = barGap;
	}

	public String getBarCategoryGap() {
		return barCategoryGap;
	}

	public void setBarCategoryGap(String barCategoryGap) {
		this.barCategoryGap = barCategoryGap;
	}

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}

	public MarkLine getMarkPoint() {
		return markPoint;
	}

	public void setMarkPoint(MarkLine markPoint) {
		this.markPoint = markPoint;
	}

	public MarkLine getMarkLine() {
		return markLine;
	}

	public void setMarkLine(MarkLine markLine) {
		this.markLine = markLine;
	}

	public MarkArea getMarkArea() {
		return markArea;
	}

	public void setMarkArea(MarkArea markArea) {
		this.markArea = markArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
