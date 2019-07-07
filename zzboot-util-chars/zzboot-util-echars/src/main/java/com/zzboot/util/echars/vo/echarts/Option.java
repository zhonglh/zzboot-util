package com.zzboot.util.echars.vo.echarts;


import com.zzboot.util.echars.vo.echarts.base.*;
import com.zzboot.util.echars.vo.echarts.legend.*;
import com.zzboot.util.echars.vo.echarts.series.*;
import com.zzboot.util.echars.vo.echarts.title.*;
import com.zzboot.util.echars.vo.echarts.tooltip.*;

@SuppressWarnings("serial")
public class Option implements  java.io.Serializable{
	
	private String backgroundColor;
	private String[] color;
	
	private Title[] title;
	private Legend[] legend;
	private Series[] series;
	private Tooltip[] tooltip;
	private VisualMap[] visualMap;
	
	private Grid[] grid;
	
	private Xaxis[] xaxis ;
	private Yaxis[] yaxis ;
	
	
	public Legend[] getLegend() {
		return legend;
	}
	public void setLegend(Legend[] legend) {
		this.legend = legend;
	}
	public Series[] getSeries() {
		return series;
	}
	public void setSeries(Series[] series) {
		this.series = series;
	}
	public Tooltip[] getTooltip() {
		return tooltip;
	}
	public void setTooltip(Tooltip[] tooltip) {
		this.tooltip = tooltip;
	}
	public Title[] getTitle() {
		return title;
	}
	public void setTitle(Title[] title) {
		this.title = title;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public Grid[] getGrid() {
		return grid;
	}
	public void setGrid(Grid[] grid) {
		this.grid = grid;
	}
	public VisualMap[] getVisualMap() {
		return visualMap;
	}
	public void setVisualMap(VisualMap[] visualMap) {
		this.visualMap = visualMap;
	}
	public Xaxis[] getXaxis() {
		return xaxis;
	}
	public void setXaxis(Xaxis[] xaxis) {
		this.xaxis = xaxis;
	}
	public Yaxis[] getYaxis() {
		return yaxis;
	}
	public void setYaxis(Yaxis[] yaxis) {
		this.yaxis = yaxis;
	}

	
	
	

}
