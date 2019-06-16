package com.zzboot.util.poi.exceptions;


/**
 * Excel 类型匹配错误
 * @author Administrator
 */
public class ExcelTypeMatchingException extends RuntimeException {

    int sheetIndex = 0;
    int rowIndex = 0 ;
    int cellIndex = 0;

    public ExcelTypeMatchingException(int rowIndex, int cellIndex) {
        this.rowIndex = rowIndex;
        this.cellIndex = cellIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }


    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    @Override
    public String toString() {
        if(sheetIndex == 1) {
            return String.format("第%d行%d列数据格式不匹配", rowIndex, rowIndex);
        }else {
            return String.format("第%d页%d行%d列数据格式不匹配", sheetIndex, rowIndex, rowIndex);
        }
    }
}
