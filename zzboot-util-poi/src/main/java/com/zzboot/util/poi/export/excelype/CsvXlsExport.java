package com.zzboot.util.poi.export.excelype;

import com.csvreader.CsvWriter;
import com.zzboot.util.base.data.PubMethod;
import com.zzboot.util.base.java.GenericsHelper;
import com.zzboot.util.base.java.IdUtils;
import com.zzboot.util.file.DownloadBaseUtil;
import com.zzboot.util.file.FileUtils;
import com.zzboot.util.poi.export.ExcelExport;
import com.zzboot.util.poi.export.ExcelTypeExport;
import com.zzboot.util.poi.util.ColumnUtil;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;


/**
 * @author admin
 */
public class CsvXlsExport<T> extends BaseXlsExport<T> implements ExcelExport<T>, ExcelTypeExport {

    private Class<T> entityClz = null;
    CsvWriter csvWriter = null ;
    String filePaht = null;

    public CsvXlsExport(){
        entityClz = GenericsHelper.getSuperClassGenricType(getClass(), 0);
        filePaht = System.currentTimeMillis()+ IdUtils.getId() + ".csv" ;
        csvWriter = new CsvWriter(filePaht, ',', Charset.forName("UTF-8"));
    }


    @Override
    public boolean isImport() {
        return false;
    }

    @Override
    public void setPrompt(Sheet sheet, String promptTitle, String promptContent, int firstRow, int endRow, int firstCol, int endCol) {

    }

    @Override
    public void setValidation(Sheet sheet, String[] textlist, int firstRow, int endRow, int firstCol, int endCol) {

    }

    @Override
    public void exportTitles(int headers, T t, boolean isAddNumber) {
        exportTitles(headers,t,null,isAddNumber);
    }

    @Override
    public void exportTitles(int headers, T t, List<Column> columns, boolean isAddNumber)  {

        int position = isAddNumber ? 1 : 0 ;

        if(columns == null || columns.isEmpty()) {
            Class<T> clz = this.entityClz;
            if(t != null){
                clz = (Class<T>) t.getClass();
            }
            columns = ColumnUtil.getExcelColumn(clz , isImport());
        }


        String[] titles = new String[columns.size() + position];
        if(position == 1){
            titles[0] = "序号";
        }
        int index = position;
        for(; index < titles.length ; index ++){
            titles[index] = columns.get(index - position).getName();
        }

        try {
            csvWriter.writeRecord(titles);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void exportHeaders(List<String> headers, int maxCellLength) {
        ;
    }

    @Override
    public void exportContent(List<T> contents, int rowIndex, boolean isAddNumber) {
        exportContent(contents , rowIndex , null , isAddNumber);
    }

    @Override
    public void exportContent(List<T> contents, int rowIndex, List<Column> columns, boolean isAddNumber) {


        if(contents == null || contents.isEmpty()) {
            return ;
        }

        int position = isAddNumber ? 1 : 0 ;

        if(columns == null || columns.isEmpty()) {
            columns = ColumnUtil.getExcelColumn(contents.get(0).getClass() , isImport());
        }

        try {


            int dataIndex = 0;
            for (T t : contents) {
                if (t == null) {
                    continue;
                }
                dataIndex++;
                String[] cs = new String[columns.size() + position];
                if (position == 1) {
                    cs[0] = String.valueOf(dataIndex);
                }


                int index = position;
                for (Column column : columns) {
                    column.getField().setAccessible(true);
                    try {
                        Object value = column.getField().get(t);
                        cs[index] = PubMethod.obj2String(value);
                        if (cs[index] == null) {
                            cs[index] = "";
                        }else {
                            cs[index] = cs[index] + "   ";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    index++;

                }

                csvWriter.writeRecord(cs);

            }


            csvWriter.flush();

            csvWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void exportXls(HttpServletResponse response) throws RuntimeException {
        throw new RuntimeException("该方法不能调用");
    }

    @Override
    public void exportXls(HttpServletResponse response, String fileName) throws RuntimeException {
        DownloadBaseUtil.download(new File(filePaht) , fileName ,response , true);
    }

    @Override
    public void exportXls(String xlsFileName) throws RuntimeException {
        File f = new File(filePaht);
        try {
            FileUtils.copyFile(f.getAbsolutePath() , xlsFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            f.deleteOnExit();
        }
    }

}
