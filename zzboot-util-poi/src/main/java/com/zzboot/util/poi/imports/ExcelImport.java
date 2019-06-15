package com.zzboot.util.poi.imports;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ExcelImport {


    /**
     * 读取Excel第一个Sheet的数据
     * @return
     */
    public List<Object[]> readExcel() ;






    /**
     * 读取Excel所有Sheet 的数据
     * @return
     */
    public List<List<Object[]>> readExcelAllSheet() ;
}
