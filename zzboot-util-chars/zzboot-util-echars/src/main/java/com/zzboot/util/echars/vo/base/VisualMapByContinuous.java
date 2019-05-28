package com.zzboot.util.echars.vo.base;

@SuppressWarnings("serial")
public class VisualMapByContinuous extends VisualMap implements java.io.Serializable{
	
	private String type  = "continuous";
	
	private boolean realtime = true;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRealtime() {
		return realtime;
	}

	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}


}
