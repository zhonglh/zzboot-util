package com.zzboot.util.poi.export.excelype;

import com.zzboot.util.configs.annotaions.EntityAnnotation;
import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.export.ExcelTypeExport;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

/**
 * Excel 模板
 * @author Administrator
 */
public class BaseXlsTemplet<T> extends BaseXlsExport<T> implements ExcelExport<T> , ExcelTypeExport {



    @Override
    public boolean isImport() {
        return true;
    }

    @Override
    public void setPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
        super.setPrompt(sheet, promptTitle, promptContent, firstRow, endRow, firstCol, endCol);
    }

    @Override
    public void setValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        DataValidation dataValidation = helper.createValidation(constraint, regions);
        // 处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation){
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        }else{
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
    }

    @Override
    public String getExcelFileName(){
        if(entityClz == null || entityClz == Object.class){
            return "模板";
        }else {

            EntityAnnotation ea = entityClz.getAnnotation(EntityAnnotation.class);
            if(ea == null){
                ea = entityClz.getSuperclass().getAnnotation(EntityAnnotation.class);
            }
            if(ea == null){
                return "模板";
            }else {
                return ea.value()+"模板";
            }
        }
    }

    @Override
    public CellStyle commonTitleStyle(Column column) {

        CellStyle cellStyle = getWorkbook().createCellStyle();
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderRight((short) 1);

        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);

        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
        cellStyle.setFont(getTitleFont(column));


        return cellStyle;



    }



    @Override
    protected Font getTitleFont(Column column) {


        Font newFont = getWorkbook().createFont();
        //粗体显示
        newFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        if(column != null && column.isRequired()) {
            newFont.setColor(HSSFColor.RED.index);
        }
        return newFont;

    }


}




