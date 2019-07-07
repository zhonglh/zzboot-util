package com.zzboot.util.echars.vo.echarts.series;


import com.zzboot.util.echars.vo.echarts.tooltip.Tooltip;

@SuppressWarnings("serial")
public class Series  implements  java.io.Serializable{
	
		private double sum ;
		

		//系列名称
		//用于tooltip的显示，legend 的图例筛选，在 setOption 更新数据和配置项时用于指定对应的系列
		private String name ;


		private Tooltip tooltip;

		public double getSum() {
			return sum;
		}

		public void setSum(double sum) {
			this.sum = sum;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}
}
