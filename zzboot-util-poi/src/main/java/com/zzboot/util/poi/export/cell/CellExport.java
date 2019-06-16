package com.zzboot.util.poi.export.cell;

import com.zzboot.util.poi.cell.CellOperation;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 列导出接口
 * @author Administrator
 */
public interface CellExport extends CellOperation {



    public void setCell(int index, Row row, CellStyle cellStyle, String value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Integer value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Long value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Double value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Float value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, BigDecimal value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Boolean value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Date value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Timestamp value)  ;
    public void setCell(int index, Row row, CellStyle cellStyle, Object value)  ;

}
