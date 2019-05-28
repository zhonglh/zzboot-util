package com.zzboot.util.echars.vo.base;

@SuppressWarnings("serial")
public class LableLine implements java.io.Serializable{

	private Normal normal = new Normal();
	private Emphasis emphasis = new Emphasis();
	
	public Normal getNormal() {
		return normal;
	}
	public void setNormal(Normal normal) {
		this.normal = normal;
	}
	public Emphasis getEmphasis() {
		return emphasis;
	}
	public void setEmphasis(Emphasis emphasis) {
		this.emphasis = emphasis;
	}
}
