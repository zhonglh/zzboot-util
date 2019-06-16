package com.zzboot.util.poi.export;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author admin
 */
public interface ExcelTypeExport {

    /**
     * 是导出数据(用于导出数据)， 还是导出为模板(用于导入的)
     * @return
     */
    public boolean isImport();

    /**
     * 设置提示信息
     * @param sheet
     * @param promptTitle
     * @param promptContent
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     */
    public void setPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) ;

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     * @param sheet
     * @param textlist
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     */
    public void setValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) ;


}
