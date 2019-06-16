package com.zzboot.util.poi.cell;

import com.zzboot.util.config.Global;
import com.zzboot.util.poi.export.cell.CellExport;
import com.zzboot.util.poi.export.cell.DefaultCellExport;
import com.zzboot.util.poi.export.cell.StringCellExport;
import com.zzboot.util.poi.imports.cell.CellImport;
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
        if(CellOperation.OPERATION_MODEL_STRING.equals(Global.getUserConfig().getExcelOperationModel())) {
            return new StringCellExport();
        }else {
            return new DefaultCellExport();
        }
    }


    public static CellImport buildCellImport(){

        if(CellOperation.OPERATION_MODEL_STRING.equals(Global.getUserConfig().getExcelOperationModel())) {
            return new StringCellImport();
        }else {
            return new DefaultCellImport();
        }
    }
}
