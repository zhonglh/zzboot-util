package com.zzboot.util.echars.vo.base;

@SuppressWarnings("serial")
public class VisualMapByPiecewise  extends VisualMap implements java.io.Serializable{

	private String type  = "piecewise";
	private int splitNumber = 5;
	private Pieces[] Pieces;
	private String[] categories ;
	private String selectedMode = "multiple";
	
	//水平（'horizontal'）或者竖直（'vertical'） 
	private String orient  = "horizontal";
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSplitNumber() {
		return splitNumber;
	}
	public void setSplitNumber(int splitNumber) {
		this.splitNumber = splitNumber;
	}
	public Pieces[] getPieces() {
		return Pieces;
	}
	public void setPieces(Pieces[] pieces) {
		Pieces = pieces;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public String getSelectedMode() {
		return selectedMode;
	}
	public void setSelectedMode(String selectedMode) {
		this.selectedMode = selectedMode;
	}
	public String getOrient() {
		return orient;
	}
	public void setOrient(String orient) {
		this.orient = orient;
	}


	
	
	
}
