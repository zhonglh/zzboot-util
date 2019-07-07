package com.zzboot.util.echars.vo.echarts.base;

@SuppressWarnings("serial")
public class Normal implements java.io.Serializable{
	
	private boolean show = true;
	
	//标签的位置,	可选：
	//'outside' 饼图扇区外侧，通过视觉引导线连到相应的扇区。
	//'inside' 饼图扇区内部。
	//'inner' 同 'inside'。
	//'center' 在饼图中心位置
	private String position = "outside";
	
	//标签内容格式器，支持字符串模板和回调函数两种形式，字符串模板与回调函数返回的字符串均支持用 \n 换行
	//字符串模板,	模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比。
	//示例：	  formatter: '{b}: {d}'
	//回调函数,	回调函数格式：
	//  (params: Object|Array) => string
	/*参数 params 是 formatter 需要的单个数据集。格式如下：
	  {
	      componentType: 'series',
	      // 系列类型
	      seriesType: string,
	      // 系列在传入的 option.series 中的 index
	      seriesIndex: number,
	      // 系列名称
	      seriesName: string,
	      // 数据名，类目名
	      name: string,
	      // 数据在传入的 data 数组中的 index
	      dataIndex: number,
	      // 传入的原始数据项
	      data: Object,
	      // 传入的数据值
	      value: number|Array,
	      // 数据图形的颜色
	      color: string,

	      // 百分比
	      percent: number,

	  }*/
	private String formatter ;
	
	
	
	private double opacity ;
	private double shadowBlur ;
	private double shadowOffsetX;
	private double shadowOffsetY ;
	private String shadowColor ;
	
	//标签的字体样式
	private TextStyle  textStyle ;

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public TextStyle getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(TextStyle textStyle) {
		this.textStyle = textStyle;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	public double getShadowBlur() {
		return shadowBlur;
	}

	public void setShadowBlur(double shadowBlur) {
		this.shadowBlur = shadowBlur;
	}

	public double getShadowOffsetX() {
		return shadowOffsetX;
	}

	public void setShadowOffsetX(double shadowOffsetX) {
		this.shadowOffsetX = shadowOffsetX;
	}

	public double getShadowOffsetY() {
		return shadowOffsetY;
	}

	public void setShadowOffsetY(double shadowOffsetY) {
		this.shadowOffsetY = shadowOffsetY;
	}

	public String getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(String shadowColor) {
		this.shadowColor = shadowColor;
	}

	
}
