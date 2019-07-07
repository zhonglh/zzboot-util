package com.zzboot.util.echars;

import com.zzboot.util.echars.vo.echarts.series.Data;
import com.zzboot.util.echars.vo.reports.SmallItem;

import java.util.List;


/**
 * @author Administrator
 */
public class TypeConversion {
	
	public static Data toData(SmallItem smallItem){
		if(smallItem == null) {
			return null;
		}
		Data data = new Data();
		data.setName(smallItem.getName());
		data.setValue(smallItem.getValue());
		return data;
	}


	public static Data[] toDatas(List<SmallItem> smallItems){
		if(smallItems == null || smallItems.size() < 0) {
			return null;
		}
		Data[] datas = new Data[smallItems.size()];
		int index = 0;
		for(SmallItem smallItem : smallItems){
			datas[index] = toData(smallItem);
			index ++;
		}
		return datas;
	}

}
