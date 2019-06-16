package com.zzboot.util.poi.cell;

import com.zzboot.util.configs.AppConfig;
import com.zzboot.util.poi.export.cell.CellExport;
import com.zzboot.util.poi.export.cell.DefaultCellExport;
import com.zzboot.util.poi.export.cell.StringCellExport;
import com.zzboot.util.poi.imports.cell.CellImport;
import com.zzboot.util.poi.cell.CellOperation;
import com.zzboot.util.poi.imports.cell.DefaultCellImport;
import com.zzboot.util.poi.imports.cell.StringCellImport;

/**
 * @author Administrator
 */
public class CellBuild {

    /**
     * 生成一个Excel导出处理对象
     * @return
     */
    public static CellExport buildCellExport(){
        if(AppConfig.EXCEL_OPERATION_MODEL.equals(CellOperation.OPERATION_MODEL_DEFAULT)) {
            return new DefaultCellExport();
        }else {
            return new StringCellExport();
        }
    }


    public static CellImport buildCellImport(){

        if(AppConfig.EXCEL_OPERATION_MODEL.equals(CellOperation.OPERATION_MODEL_DEFAULT)) {
            return new DefaultCellImport();
        }else {
            return new StringCellImport();
        }
    }
}
