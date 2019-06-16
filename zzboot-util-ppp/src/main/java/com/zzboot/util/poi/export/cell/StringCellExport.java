package com.zzboot.util.poi.export.cell;

import com.zzboot.util.base.data.DateKit;
import com.zzboot.util.base.data.DateProcess;
import com.zzboot.util.poi.cell.CellOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 导出CELL
 * 全部转换为字符串导出， 字符的导出格式为文本， 日期的导出格式为日期
 * @author Administrator
 */
public class StringCellExport extends DefaultCellExport implements CellExport {


    @Override
    public String getOperationModel() {
        return CellOperation.OPERATION_MODEL_STRING;
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, String value) {
        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
    }


    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Boolean value) {
        setCell(index, row, cellStyle, value.toString());

    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Date value) {
        setCell(index, row, cellStyle, DateKit.fmtDateToYMD(DateProcess.getFirst(value)));
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Timestamp value) {
        setCell(index, row, cellStyle, DateKit.dateTo19String(value));
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Object value) {
        setCell(index , row , cellStyle , value.toString());
    }

}
