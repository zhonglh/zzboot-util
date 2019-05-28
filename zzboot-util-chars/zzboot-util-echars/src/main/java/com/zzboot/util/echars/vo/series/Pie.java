package com.zzboot.util.echars.vo.series;


import com.zzboot.util.echars.vo.base.Label;
import com.zzboot.util.echars.vo.base.LableLine;

/**
 * 饼图图表
 * @author zhonglihong
 * @date 2016年12月4日 下午9:47:24
 */
@SuppressWarnings("serial")
public class Pie extends Series implements  java.io.Serializable{
	
	
	
	private String type = "pie";
	
	
	//是否启用图例 hover 时的联动高亮
	private boolean legendHoverLink = true ;
	
	//是否开启 hover 在扇区上的放大动画效果。
	private boolean hoverAnimation = true;
	
	//选中模式，表示是否支持多个选中，默认关闭，
	private boolean selectedMode ;
	
	//选中扇区的偏移距离
	private double selectedOffset = 10;
	
	//饼图的扇区是否是顺时针排布
	private boolean clockwise = true;
	
	//是否展示成南丁格尔图，通过半径区分数据大小 , area , radius
	private String roseType ;
	
	//饼图的中心（圆心）坐标，数组的第一项是横坐标，第二项是纵坐标。
	//支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
	private String[] center ;
	
	//饼图的半径，数组的第一项是内半径，第二项是外半径。
	//支持设置成百分比，相对于容器高宽中较小的一项的一半。
	//可以将内半径设大显示成圆环图
	private String[] radius ;
	
	//系列中的数据内容数组
	private Data[] data;

	//饼图图形上的文本标签，可用于说明图形的一些数据信息，
	//比如值，名称等，label选项在 ECharts 2.x 中放置于itemStyle.normal下，
	//在 ECharts 3 中为了让整个配置项结构更扁平合理，label 被拿出来跟 itemStyle 平级，
	//并且跟 itemStyle 一样拥有 normal, emphasis 两个状态。
	private Label label = new Label();
	
	//标签的视觉引导线样式，在 label 位置 设置为'outside'的时候会显示视觉引导线
	private LableLine lableLine = new LableLine();
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public boolean isLegendHoverLink() {
		return legendHoverLink;
	}

	public void setLegendHoverLink(boolean legendHoverLink) {
		this.legendHoverLink = legendHoverLink;
	}

	public boolean isHoverAnimation() {
		return hoverAnimation;
	}

	public void setHoverAnimation(boolean hoverAnimation) {
		this.hoverAnimation = hoverAnimation;
	}

	public boolean isSelectedMode() {
		return selectedMode;
	}

	public void setSelectedMode(boolean selectedMode) {
		this.selectedMode = selectedMode;
	}

	public double getSelectedOffset() {
		return selectedOffset;
	}

	public void setSelectedOffset(double selectedOffset) {
		this.selectedOffset = selectedOffset;
	}

	public boolean isClockwise() {
		return clockwise;
	}

	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}



	public String[] getCenter() {
		return center;
	}

	public void setCenter(String[] center) {
		this.center = center;
	}

	public String[] getRadius() {
		return radius;
	}

	public void setRadius(String[] radius) {
		this.radius = radius;
	}

	public Data[] getData() {
		return data;
	}

	public void setData(Data[] data) {
		this.data = data;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public LableLine getLableLine() {
		return lableLine;
	}

	public void setLableLine(LableLine lableLine) {
		this.lableLine = lableLine;
	}

	public String getRoseType() {
		return roseType;
	}

	public void setRoseType(String roseType) {
		this.roseType = roseType;
	}


	

}
