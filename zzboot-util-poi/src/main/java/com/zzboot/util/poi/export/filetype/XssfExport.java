package com.zzboot.util.poi.export.filetype;

import com.zzboot.util.poi.export.excelype.AbstractXlsExport;
import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.enums.EnumExcelFileType;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Excel2007 格式
 * 不适用大数据量
 * @author Administrator
 */
public class XssfExport<T> implements ExcelExport<T>  {

    private AbstractXlsExport<T> axe ;

    public XssfExport(AbstractXlsExport<T> axe) {
        this.axe = axe;
        axe.setWorkbook( new XSSFWorkbook() );
    }




    @Override
    public void exportTitles(int headers, T t, boolean isAddNumber) {
        axe.exportTitles(headers , t , isAddNumber);
    }

    @Override
    public void exportTitles(int headers, T t, List<Column> columns, boolean isAddNumber) {
        axe.exportTitles(headers , t , columns ,isAddNumber);
    }

    @Override
    public void exportHeaders(List<String> headers , int maxCellLength) {
        axe.exportHeaders(headers,maxCellLength);
    }

    @Override
    public void exportContent(List<T> contents, int rowIndex, boolean isAddNumber) {
        axe.exportContent(contents , rowIndex , isAddNumber);
    }

    @Override
    public void exportContent(List<T> contents, int rowIndex, List<Column> columns, boolean isAddNumber) {
        axe.exportContent(contents , rowIndex , columns , isAddNumber);
    }


    @Override
    public void exportXls(HttpServletResponse response ) throws RuntimeException {
        axe.exportXls(response,axe.getExcelFileName()+"."+ EnumExcelFileType.XSSF.getFileType());
    }

    @Override
    public void exportXls(HttpServletResponse response , String fileName) throws RuntimeException {
        axe.exportXls(response,fileName);
    }

    @Override
    public void exportXls(String xlsFileName) throws RuntimeException {
        axe.exportXls(xlsFileName);
    }

}
