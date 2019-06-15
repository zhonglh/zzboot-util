package com.zzboot.util.poi.imports.cell;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

/**
 * 字符串格式导入处理
 * @author Administrator
 */
public class DefaultCellImport extends AbstractCellImport implements CellImport {
    @Override
    public Object getCellContent(Cell cell) {

        if (cell != null) {
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                //数值类型,日期类型
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    return date;
                }else{
                    double d = cell.getNumericCellValue();
                    return d;
                }
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                //字符串类型
                String val =  cell.getStringCellValue();
                if(StringUtils.isNotEmpty(val)){
                    val = val.trim();
                }
                return val;
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
                //有公式的单元格
                Object val = this.getFormulaCellVal(cell);
                return val;
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
                return null;
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                boolean b = cell.getBooleanCellValue();
                return b;
            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
                throw new RuntimeException("Excel文件内容有错误，请先处理");
            }else{
                return null;
            }
        } else {
            return null;
        }

    }
}
