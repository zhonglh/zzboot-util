package com.zzboot.util.poi.cell;


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
