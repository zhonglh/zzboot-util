package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class VisualMap implements java.io.Serializable{	

	private String left;
	private String top;
	private String bottom;
	private int dimension = 2;
	private int min;
	private int max;
	private int itemWidth;
	private int itemHeight;
	private boolean calculable = true; 
	private int precision = 0;
	private String[] text;
	private int textGap = 30;
	private TextStyle textStyle;
	

	private InRange inRange;
	private OutRange outRange;
	private Controller controller;
	

	private String[] color;


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


	public int getDimension() {
		return dimension;
	}


	public void setDimension(int dimension) {
		this.dimension = dimension;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
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


	public boolean isCalculable() {
		return calculable;
	}


	public void setCalculable(boolean calculable) {
		this.calculable = calculable;
	}


	public int getPrecision() {
		return precision;
	}


	public void setPrecision(int precision) {
		this.precision = precision;
	}


	public String[] getText() {
		return text;
	}


	public void setText(String[] text) {
		this.text = text;
	}


	public int getTextGap() {
		return textGap;
	}


	public void setTextGap(int textGap) {
		this.textGap = textGap;
	}


	public TextStyle getTextStyle() {
		return textStyle;
	}


	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}


	public InRange getInRange() {
		return inRange;
	}


	public void setInRange(InRange inRange) {
		this.inRange = inRange;
	}


	public OutRange getOutRange() {
		return outRange;
	}


	public void setOutRange(OutRange outRange) {
		this.outRange = outRange;
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public String[] getColor() {
		return color;
	}


	public void setColor(String[] color) {
		this.color = color;
	}


	public String getBottom() {
		return bottom;
	}


	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

}
