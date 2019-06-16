package com.zzboot.util.poi.imports.cell;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 列导入接口
 * @author Administrator
 */
public interface CellImport {

    /**
     * 获取列的内容
     * @param cell
     * @return
     */
    public Object getCellContent(Cell cell) ;


}
