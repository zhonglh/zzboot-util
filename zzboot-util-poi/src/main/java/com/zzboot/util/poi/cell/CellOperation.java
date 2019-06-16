package com.zzboot.util.poi.cell;

/**
 * @author Administrator
 */
public interface CellOperation {


    public final String OPERATION_MODEL_DEFAULT = "DefaultCellOperation";
    public final String OPERATION_MODEL_STRING = "StringCellOperation";

    /**
     * 获取的操作类型
     * @return
     */
    public String getOperationModel();



}
