package com.zzboot.util.poi.imports.cell;

import com.zzboot.util.base.data.DateKit;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * 字符串格式导入处理
 * @author Administrator
 */
public class StringCellImport extends AbstractCellImport implements CellImport {

    @Override
    public String getCellContent(Cell cell) {

        String cellValue = null;
        DecimalFormat df=new DecimalFormat("0.00");

        if (cell != null) {
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                //数值类型,日期类型
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    if(date != null) {
                        cellValue = DateKit.fmtDateToYMD(date);
                    }
                }else{
                    double d = cell.getNumericCellValue();
                    cellValue = String.valueOf(df.format(d));
                }
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                //字符串类型
                cellValue = cell.getStringCellValue();
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                //有公式的单元格
                Object val = this.getFormulaCellVal(cell);
                if(val != null){
                    if(val instanceof Double || val.getClass() == double.class){
                        cellValue = String.valueOf(df.format((Double)val));
                    }else if(val instanceof String){
                        cellValue = (String)val;
                    }else {
                        cellValue =  val.toString();
                    }
                }
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                ;
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                boolean b = cell.getBooleanCellValue();
                cellValue = String.valueOf(b);
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                throw new RuntimeException("Excel文件内容有错误，请先处理");
            }else{
                cellValue = null;
            }

            if(cellValue != null ) {
                cellValue = cellValue.trim();
            }
            return cellValue;
        } else {
            return null;
        }
    }
}
