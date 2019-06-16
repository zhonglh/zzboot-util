package com.zzboot.util.poi.export.excelype;

import com.zzboot.util.base.data.DateKit;
import com.zzboot.util.base.java.GenericsHelper;
import com.zzboot.util.poi.cell.CellBuild;
import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.export.ExcelTypeExport;
import com.zzboot.util.poi.export.cell.CellExport;
import com.zzboot.util.poi.enums.EnumXlsFormat;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public abstract class AbstractXlsExport<T> extends AbstractXlsStyle implements ExcelExport<T>  , ExcelTypeExport {

	public static int defaultColumnWidth = 15;
	public static int defaultColumnHeight = 250;
	public static int titleColumnHeight = 400;


	CellExport cellExport = CellBuild.buildCellExport();

	/**
	 * 实体类型
	 */
	protected Class<T> entityClz = null;


	/**
	 * Excel workbook
	 */
	protected Workbook workbook;

	/**
	 * 当前Sheet
	 */
	protected Sheet sheet;

	/**
	 * 当前行
	 */
	protected Row row;

	/**
	 * 头信息
	 */
	protected Row[] headerRow;

	/**
	 * 标题信息
	 */
	protected Row[] titleRow;

	/**
	 * 是否自定义标题信息
	 * 例如  标题的信息是从其它excel获取的
	 */
	public boolean isCustomTitleInfo = false ;


	public AbstractXlsExport(){
		entityClz = GenericsHelper.getSuperClassGenricType(getClass(), 0);
	}


	public boolean isWriteTitle(){
		return true;
	}



	public String getNumberName(){
		return "序号";
	}

	public String getExcelFileName(){

		String currTime = DateKit.formatDate(new Date() , "yyyyMMddHHmmss");
		if(entityClz == null || entityClz == Object.class){
			return currTime;
		}else {

			EntityAnnotation ea = entityClz.getAnnotation(EntityAnnotation.class);
			if(ea == null){
				ea = entityClz.getSuperclass().getAnnotation(EntityAnnotation.class);
			}
			if(ea == null){
				return currTime;
			}else {
				return ea.value()+currTime;
			}
		}
	}

	public Class<T> getEntityClz() {
		return entityClz;
	}

	public void setEntityClz(Class<T> entityClz) {
		this.entityClz = entityClz;
	}

	public void createSheet(T t ) {
		createSheet(t , defaultColumnWidth);
	}


	public void createSheet(T t  , int columnWidth) {
		createSheet(t ,columnWidth , 0 , 1);
	}


	public void createSheet(T t,  int cols, int rows) {
		createSheet(t ,defaultColumnWidth , cols , rows);
	}

	public void createSheet(T t, int columnWidth , int cols, int rows) {
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(columnWidth);
		this.sheet.setDefaultRowHeight((short)defaultColumnHeight);
		this.sheet.createFreezePane(cols, rows);
	}	


	/**
	 * 增加一行
	 * @param index 行号
	 */
	public void createRow( int index) {
		createRow(this.getCurrSheet(),index);
	}


	public void createRow(Sheet sheet, int index) {
		setCurrRow(sheet.createRow(index));
	}




	public void setTitleCell(int cellIndex, String value, Column column) {
		Cell cell = getCurrRow().createCell(cellIndex);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		CellStyle cellStyle = commonTitleStyle(column);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, String value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , String.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, int value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Integer.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}
	public void setCell(Class<T> tClass ,int cellIndex, Integer value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Integer.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}


	public void setCell(Class<T> tClass ,int cellIndex, long value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Long.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}
	public void setCell(Class<T> tClass ,int cellIndex, Long value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Long.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, double value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Double.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}
	public void setCell(Class<T> tClass ,int cellIndex, Double value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Double.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, float value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Float.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}
	public void setCell(Class<T> tClass ,int cellIndex, Float value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Float.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, BigDecimal value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , BigDecimal.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , value);
	}

	public void setCell(Class<T> tClass ,int cellIndex, Date value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Date.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , DateProcess.getFirst(value));
	}

	public void setCell(Class<T> tClass ,int cellIndex, Timestamp value , short definAlignment) {
		CellStyle cellStyle = commonStyle(tClass , cellIndex , Timestamp.class , definAlignment ,cellExport.getOperationModel());
		cellExport.setCell(cellIndex , getCurrRow() , cellStyle , DateProcess.getFirst(value));
	}


	public void setCell(Class<T> tClass ,int cellIndex, Object value , short definAlignment) {
		if(value instanceof String){
			setCell(tClass ,cellIndex, ((String) value ) , definAlignment) ;
		}else if(value instanceof Integer){
			setCell(tClass ,cellIndex, ((Integer) value ) , definAlignment) ;
		}else if(value instanceof Long){
			setCell(tClass ,cellIndex, ((Long) value ) , definAlignment) ;
		}else if(value instanceof Double){
			setCell(tClass ,cellIndex, ((Double) value ) , definAlignment) ;
		}else if(value instanceof Float){
			setCell(tClass ,cellIndex, ((Float) value ) , definAlignment) ;
		}else if(value instanceof BigDecimal){
			setCell(tClass ,cellIndex, ((BigDecimal) value ) , definAlignment) ;
		}else if(value instanceof Date){
			setCell(tClass ,cellIndex, ((Date) value ) , definAlignment) ;
		}else if(value instanceof java.sql.Date){
			setCell(tClass ,cellIndex, ((java.sql.Date) value ) , definAlignment) ;
		}else if(value instanceof Timestamp){
			setCell(tClass ,cellIndex, ((Timestamp) value ) , definAlignment) ;
		}else {
			setCell(tClass ,cellIndex, value.toString() , definAlignment) ;
		}
	}


	


	public CellStyle firstCommonStyle(Class<T> tClass ,int cellIndex , Class<?> columnClz) {
		try{
			if(titleRow != null && isCustomTitleInfo()){
				CellStyle cellStyle =  this.titleRow[titleRow.length-1].getCell(cellIndex).getCellStyle();
				return firstCommonStyle(cellStyle , tClass  ,cellIndex ,   columnClz , cellExport.getOperationModel());
			}
		}catch(Exception e){
			;
		}
		return commonStyle();
	}



	
	
	
	
	/**
	 * 设置单元格内容
	 * @param cellIndex
	 * @param value
	 * @param alignment
	 * @param boldweight
	 */
	public void setCell(int cellIndex, String value, short alignment, boolean boldweight) {
		
		Cell cell = getCurrRow().createCell(cellIndex);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		CellStyle cellStyle = getCellStyle(alignment, boldweight);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}



	/**
	 * 设置单元格内容
	 * @param cellIndex
	 * @param value
	 * @param alignment
	 * @param boldweight
	 * @param formatEm
	 */
	public void setCell(int cellIndex, Double value, short alignment, boolean boldweight, EnumXlsFormat formatEm) {
		
		Cell cell = getCurrRow().createCell(cellIndex);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		CellStyle cellStyle = getCellStyle(alignment, boldweight, formatEm);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}


	public void setNumberCell(int cellIndex, Double value, short alignment, boolean boldweight) {
		this.setCell(cellIndex, value, alignment,boldweight , EnumXlsFormat.NUMBER);
	}

	public void setCurrencyCell(int cellIndex, Double value, short alignment, boolean boldweight) {
		this.setCell(cellIndex, value, alignment,boldweight , EnumXlsFormat.CURRENCY);
	}

	public void setPercentCell(int cellIndex, Double value, short alignment, boolean boldweight) {
		this.setCell(cellIndex, value, alignment,boldweight , EnumXlsFormat.PERCENT);
	}


	/**
	 * 处理特殊行
	 * @param t
	 * @param columns
	 * @param addNumber
	 * @param index			数据记录号 , 从1开始
	 * @param <T>
	 * @return
	 */
	public <T> boolean specialHand(T t, List<Column> columns, boolean addNumber , int index){
		return false;
	}


	public Sheet getCurrSheet() {
		return this.sheet;
	}

	public void setCurrSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public Row getCurrRow() {
		return this.row;
	}

	public void setCurrRow(Row row) {
		this.row = row;
	}



	@Override
	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}


	public Row[] getTitleRow() {
		return titleRow;
	}

	public void setTitleRow(Row[] titleRow) {
		this.titleRow = titleRow;
	}

	public Row[] getHeaderRow() {
		return headerRow;
	}

	public void setHeaderRow(Row[] headerRow) {
		this.headerRow = headerRow;
	}

	public boolean isCustomTitleInfo() {
		return isCustomTitleInfo;
	}
}
