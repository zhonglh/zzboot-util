package com.zzboot.util.poi.export.excelype;

import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.export.ExcelTypeExport;
import com.zzboot.util.poi.util.ColumnUtil;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 */
public class BaseXlsExport<T> extends AbstractXlsExport<T> implements ExcelExport<T> , ExcelTypeExport {


	public BaseXlsExport(){
		super();
	}


	@Override
	public boolean isImport() {
		return false;
	}

	@Override
	public void setPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
		DataValidationHelper dvHelper = sheet.getDataValidationHelper();
		DataValidationConstraint constraint = dvHelper.createCustomConstraint("DD1");
		CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
		DataValidation dataValidation = dvHelper.createValidation(constraint, regions);
		dataValidation.createPromptBox(promptTitle, promptContent);
		dataValidation.setShowPromptBox(true);
		sheet.addValidationData(dataValidation);
	}

	@Override
	public void setValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {

	}


	/**
	 * 导出标题	
	 * @param headers		头信息行数
	 * @param t  			数据类型
	 * @param isAddNumber	是否增加序号
	 */

	@Override
	public void exportTitles(int headers ,  T t, boolean isAddNumber)  {
		exportTitles(headers,t,null,isAddNumber);
	}
	@Override
	public void exportTitles(int headers ,  T t, List<Column> columns ,boolean isAddNumber)  {
		

		int rowIndex = 0;
		int headLength = headers;

		rowIndex = headLength;

		this.createSheet(t , 0 , headLength+1);

		int position = isAddNumber ? 1 : 0 ;
		Row[] titleRows = null;

		if(this.isWriteTitle()) {

			// header
			this.createRow(this.getCurrSheet(),rowIndex++);

			this.getCurrRow().setHeight((short)titleColumnHeight);
			if(isAddNumber) {
				this.setTitleCell(0, getNumberName() , null);
			}

			if(columns == null || columns.isEmpty()) {
				Class<T> clz = this.entityClz;
				if(t != null){
					clz = (Class<T>) t.getClass();
				}
				columns = ColumnUtil.getExcelColumn(clz , isImport());
			}
			for (Column column : columns) {
				this.setTitleCell(column.getNumber() + position , column.getName() , column);
				if(column.getWidth() > 0) {
					this.getCurrSheet().setColumnWidth(column.getNumber() + position, (int) ((column.getWidth() + 0.72) * 256));
				}
			}

			titleRows = new Row[1];
			titleRows[0] =this.getCurrRow();

		}else {
			rowIndex = this.getCurrSheet().getLastRowNum() + 1;
			if(rowIndex > 0){
				titleRows = new Row[rowIndex-headLength];
				for(int i=0;i<rowIndex-headLength ; i++){
					titleRows[i] = this.getCurrSheet().getRow(i+headLength);
				}
			}
		}

	}


	/**
	 * 导出头信息
	 * @param headers
	 */
	@Override
	public void exportHeaders(List<String> headers , int maxCellLength) {

		if(headers == null || headers.isEmpty() ) {
			return ;
		}

		CellStyle cellStyle1 = getHeaderCellStyle1();
		CellStyle cellStyle2 = getHeaderCellStyle2();
		Row row = this.getCurrSheet().getRow(this.getCurrSheet().getLastRowNum());
		int cellLength = maxCellLength ;
		if(row != null) {
			cellLength = row.getLastCellNum();
		}
		if(cellLength > maxCellLength) {
			cellLength = maxCellLength;
		}


		for(int i=0;i<headers.size();i++){
			this.createRow(this.getCurrSheet(),i);
			Cell cell = this.getCurrRow().createCell(0);
			if(i == 0) {
				cell.setCellStyle(cellStyle1);
				this.getCurrRow().setHeightInPoints(20);
				this.getCurrRow().setHeight((short)700);
			} else {
				cell.setCellStyle(cellStyle2);
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if(headers.get(i) != null) {
				cell.setCellValue(headers.get(i));
			}

			for(int index = 1 ; index<cellLength;index ++){
				cell = this.getCurrRow().createCell(index);
				cell.setCellStyle(cellStyle2);
			}

			CellRangeAddress cellRangeAddress = new CellRangeAddress(i,i,0,(short)(cellLength-1));
			this.getCurrSheet().addMergedRegion(cellRangeAddress);

		}
	}


	/**
	 * 导出内容
	 * @param contents      内容
	 * @param rowIndex      行索引
	 * @param isAddNumber   是否增加序号
	 */
	@Override
	public void exportContent(List<T> contents, int rowIndex,  boolean isAddNumber) {
		exportContent( contents,  rowIndex, null, isAddNumber) ;
	}
	@Override
	public void exportContent(List<T> contents, int rowIndex, List<Column> columns, boolean isAddNumber) {


		if(contents == null || contents.isEmpty()) {
			return ;
		}

		int position = isAddNumber ? 1 : 0;


		Class<T> mclz = this.entityClz;
		if(mclz == null || mclz == Object.class){
			mclz = (Class<T>)contents.get(0).getClass();
		}

		if(columns == null) {
			columns = ColumnUtil.getExcelColumn(mclz ,isImport());
		}

		int dataIndex = 0;

		for(T t : contents){
			if(t == null) {
				continue;
			}

			dataIndex ++;

			this.createRow(rowIndex);

			if(this.specialHand(t, columns, isAddNumber , dataIndex)) {
				rowIndex ++ ;
				continue;
			}

			if(isAddNumber) {
				this.setCell(  mclz ,  0 , dataIndex , CellStyle.ALIGN_RIGHT);
			}


			for (Column column : columns) {
				column.getField().setAccessible(true);
				try {
					Object value = column.getField().get(t);
					if(value == null) {
						this.setCell(mclz, column.getNumber() + position , "" , column.getAlignment());
					}else {
						this.setCell( mclz ,column.getNumber() + position, value , column.getAlignment());
					}


					// 如果设置了combo属性则本列只能选择不能输入
					if (column.getCombo() != null &&column.getCombo().length > 0) {
						setValidation(sheet, column.getCombo(), rowIndex, rowIndex, column.getNumber() + position , column.getNumber() + position );
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			rowIndex ++ ;
		}

	}



	/**
	 * 导出Excel文件
	 *
	 * @throws RuntimeException
	 */
	@Override
	public void exportXls(String xlsFileName) throws RuntimeException {
		exportXls(xlsFileName,workbook);
	}
	public void exportXls(String xlsFileName, Workbook workbook) throws RuntimeException {
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("生成导出Excel文件出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("写入Excel文件出错!", e);
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (IOException e) {

			}


		}
	}



	@Override
	public void exportXls(HttpServletResponse response) throws RuntimeException {
		throw new RuntimeException("该方法不能调用");
	}


	/**
	 * 下载文件
	 * @param response
	 * @param fileName
	 * @throws RuntimeException
	 */
	@Override
	public void exportXls(HttpServletResponse response , String fileName) throws RuntimeException {
		exportXls(response,workbook , fileName );
	}
	public void exportXls(HttpServletResponse response, Workbook workbook, String fileName) throws RuntimeException {
		ServletOutputStream os = null;
		try {
			response.setContentType ("application/vnd.ms-excel;charset=utf-8");

			String excelName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

			response.addHeader("Content-Disposition","attachment;filename="+excelName);
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("导出Excel文件出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("导出Excel文件出错!", e);
		} finally {
			try{
				if(workbook != null) {
					workbook.close();
				}
			}catch(Exception e){

			}
			try {
				if (os != null)	{
					os.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
