package com.zzboot.util.poi.imports;

import com.zzboot.util.configs.AppConfig;
import com.zzboot.util.poi.cell.CellBuild;
import com.zzboot.util.poi.imports.cell.CellImport;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 默认的Excel导入数据
 * @author Administrator
 */
public class DefaultExcelImport implements ExcelImport{

    private Workbook wb = null;
    private Sheet sheet = null;
    private int startRow ;
    private boolean isAddNumber ;

    private CellImport ci ;


    public DefaultExcelImport(InputStream is ){
        this(is, (AppConfig.EXCEL_EXPORT_HEADER?2:1) , AppConfig.EXCEL_ADD_NUMBER);
    }


    public DefaultExcelImport(InputStream is, int startRow, boolean isAddNumber){
        ci = CellBuild.buildCellImport();
        this.startRow = startRow;
        this.isAddNumber = isAddNumber;
        getWorkbook(is);
    }


    @Override
    public List<List<Object[]>> readExcelAllSheet() {
        List<List<Object[]>> result = new ArrayList<List<Object[]>>();
        int sheetNums = this.wb.getNumberOfSheets();
        for(int i=0;i<sheetNums;i++){
            getSheet(i);
            List<Object[]> sheetData = getSheetData();
            if(sheetData != null && !sheetData.isEmpty()){
                result.add(sheetData);
            }
        }
        return null;
    }

    /**
     * 读取Excel文件数据
     * @return
     */
    @Override
    public List<Object[]> readExcel() {
        getSheet(0);
        return getSheetData();
    }


    protected  List<Object[]> getSheetData(){


        Iterator<Row> rows = sheet.rowIterator();
        List<Object[]> result = new ArrayList<Object[]>();



        int columnLength = 0;

        while (rows.hasNext()) {

            Row row = (Row) rows.next();
            int rowIndex = row.getRowNum();
            if(rowIndex < startRow ) {
                continue;
            }


            Object[] valArray = getRowData(row);
            if (valArray == null) {
                continue;
            }

            result.add(valArray);
        }

        return result;


    }

    /**
     * 获取行数据
     * @param row
     * @return
     */
    protected Object[] getRowData(Row row) {
        int columnLength = row.getLastCellNum();
        if(columnLength <=0 ){
            return null;
        }


        columnLength = Math.min(columnLength , 200);


        Object[] valArray = new Object[columnLength];


        int startColumn = 0;
        if(this.isAddNumber){
            startColumn = 1;
        }

        //计算内容数据
        for(int i=startColumn; i < columnLength ; i++ ){
            try {
                Cell cell = row.getCell(i);
                Object val = ci.getCellContent(cell);
                valArray[i] = val;
            }catch(RuntimeException e){

            }catch (Exception e ){

            }
        }

        return valArray;
    }


    protected void getSheet(int index){
        sheet = wb.getSheetAt(index);
    }


    protected void getWorkbook(InputStream inputStream){

        try{
            if (!(inputStream.markSupported())) {
                inputStream = new PushbackInputStream(inputStream, 8);
            }
            if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {
                wb = new HSSFWorkbook(inputStream);
            } else if (POIXMLDocument.hasOOXMLHeader(inputStream)) {
                wb = new XSSFWorkbook(OPCPackage.open(inputStream));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
