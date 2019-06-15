package com.zzboot.util.poi.imports.cell;

import org.apache.poi.ss.usermodel.Cell;

public class AbstractCellImport {

    public Object getFormulaCellVal(Cell cell){
        try{
            //公式内容
            //cellFormula = cell.getCellFormula();

            //列的值
            return cell.getNumericCellValue();
        }catch(Exception e){
            return  cell.getStringCellValue();
        }
    }

}
