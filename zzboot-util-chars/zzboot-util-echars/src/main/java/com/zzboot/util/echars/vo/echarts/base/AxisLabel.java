package com.zzboot.util.echars.vo.echarts.base;

public class AxisLabel implements java.io.Serializable{

    public AxisLabel() {
    }

    public AxisLabel(int interval, int rotate) {
        this.interval = interval;
        this.rotate = rotate;
    }

    private  int interval ;
    private  int rotate ;

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
