package com.zzboot.util.poi.export.filetype;

import com.zzboot.util.poi.export.excelype.AbstractXlsExport;
import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.enums.EnumExcelFileType;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Excel2007 格式
 * 用于大数据量
 * @author Administrator
 */
public class SxssfExport<T> implements ExcelExport<T>  {

    //内存中保留的行数，超出后会写到磁盘
    //应该是在窗口中的行数
    private static int rowAccessWindowSize = 1000;

    //每个sheet 10w条
    private static int maxSheetRows = 100000;



    private AbstractXlsExport<T> axe ;


    public SxssfExport(AbstractXlsExport<T> axe) {
        this.axe = axe;

        SXSSFWorkbook workbook = new SXSSFWorkbook(rowAccessWindowSize);
        //生成的临时文件将进行gzip压缩
        workbook.setCompressTempFiles(true);
        axe.setWorkbook( workbook );
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
        axe.exportXls(response,axe.getExcelFileName()+"."+ EnumExcelFileType.SXSSF.getFileType());
    }

    @Override
    public void exportXls(HttpServletResponse response , String fileName) throws RuntimeException {
        axe.exportXls(response , fileName);
    }

    @Override
    public void exportXls(String xlsFileName) throws RuntimeException {
        axe.exportXls(xlsFileName);
    }


}
