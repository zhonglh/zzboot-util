package com.zzboot.util.poi.export.cell;

import com.zzboot.util.poi.cell.CellOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * 导出CELL
 * 常规的导出， 字符的导出格式为文本， 日期的导出格式为日期
 * @author Administrator
 */
public class DefaultCellExport implements CellExport {


    @Override
    public String getOperationModel() {
        return CellOperation.OPERATION_MODEL_DEFAULT;
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, String value) {
        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Integer value) {
        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Long value) {
        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Double value) {

        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }

    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Float value) {


        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }


    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, BigDecimal value) {
        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value.doubleValue());
        }
    }


    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Boolean value) {

        Cell cell = row.createCell(index);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }

    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Date value) {

        Cell cell = row.createCell(index);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Timestamp value) {
        Cell cell = row.createCell(index);
        cell.setCellStyle(cellStyle);
        if(value != null) {
            cell.setCellValue(value);
        }
    }

    @Override
    public void setCell(int index, Row row, CellStyle cellStyle, Object value) {
        if(value == null){
            setCell(index, row, cellStyle, (String)null);
        }else {
            setCell(index, row, cellStyle, value.toString());
        }
    }
}
