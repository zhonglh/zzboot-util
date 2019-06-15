package com.zzboot.util.poi.exceptions;

/**
 * 数据缺少异常
 * @author Administrator
 */
public class ExcelAbsenceException extends RuntimeException {

    int sheetIndex = 0;
    int rowIndex = 0 ;

    public ExcelAbsenceException() {
    }
    public ExcelAbsenceException(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }


    @Override
    public String toString() {
        if(sheetIndex == 1) {
            return String.format("第%d行数据不全", rowIndex);
        }else {
            return String.format("第%d页%d行数据不全", sheetIndex ,rowIndex);
        }
    }
}
