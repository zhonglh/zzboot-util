package com.zzboot.util.poi.export;

import com.zzboot.util.configs.AppConfig;
import com.zzboot.util.poi.vo.Column;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Administrator
 */
public interface ExcelExport<T> {



    /**
     * 导出标题信息
     * @param headers
     * @param t
     * @param isAddNumber
     */
    public void exportTitles(int headers, T t, boolean isAddNumber);
    public void exportTitles(int headers, T t, List<Column> columns, boolean isAddNumber) ;



    /**
     * 导出头信息
     * @param headers           头信息内容
     */
    public default void exportHeaders(List<String> headers){
        exportHeaders(headers  , AppConfig.HEADER_DEFAULT_CELLS);
    }

    /**
     * 导出头信息
     * @param headers           头信息内容
     * @param maxCellLength     头信息 最多合并的列数
     */
    public void exportHeaders(List<String> headers, int maxCellLength);


    /**
     * 导出内容
     * @param contents      内容
     * @param rowIndex      行索引
     * @param isAddNumber   是否增加序号
     */
    public void exportContent(List<T> contents, int rowIndex, boolean isAddNumber) ;
    public void exportContent(List<T> contents, int rowIndex, List<Column> columns, boolean isAddNumber) ;


    /**
     * 下载
     * @param response
     * @throws RuntimeException
     */
    public void exportXls(HttpServletResponse response) throws RuntimeException  ;
    public void exportXls(HttpServletResponse response, String fileName) throws RuntimeException  ;


    /**
     * 导出 Excel 文件
     * @param xlsFileName
     * @throws RuntimeException
     */
    public void exportXls(String xlsFileName) throws RuntimeException  ;



}
