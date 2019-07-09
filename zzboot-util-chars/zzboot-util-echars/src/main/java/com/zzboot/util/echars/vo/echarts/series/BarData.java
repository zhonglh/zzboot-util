package com.zzboot.util.echars.vo.echarts.series;

import java.io.Serializable;

/**
 * @author admin
 */
public class BarData implements Serializable {

    private String[] names ;
    private double[] values;


    public BarData(String[] names, double[] values) {
        this.names = names;
        this.values = values;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }
}
