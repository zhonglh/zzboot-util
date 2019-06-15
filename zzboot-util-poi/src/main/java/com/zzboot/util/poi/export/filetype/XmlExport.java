package com.zzboot.util.poi.export.filetype;

import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.vo.Column;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * XML格式导出(Excel 2003)
 * @author Administrator
 */
public class XmlExport<T> implements ExcelExport<T>  {



    @Override
    public void exportTitles(int headers, T t, boolean isAddNumber) {

    }


    @Override
    public void exportTitles(int headers, T t, List<Column> columns, boolean isAddNumber) {

    }

    @Override
    public void exportHeaders(List<String> headers , int maxCellLength) {
    }


    @Override
    public void exportContent(List<T> contents, int rowIndex, boolean isAddNumber) {

    }

    @Override
    public void exportContent(List<T> contents, int rowIndex, List<Column> columns, boolean isAddNumber) {

    }

    @Override
    public void exportXls(HttpServletResponse response ) throws RuntimeException {

    }


    @Override
    public void exportXls(HttpServletResponse response , String fileName ) throws RuntimeException {

    }

    @Override
    public void exportXls(String xlsFileName) throws RuntimeException {

    }
}
