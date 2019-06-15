package com.zzboot.util.poi.export.excelype;

import com.zzboot.util.poi.cell.CellOperation;
import com.zzboot.util.poi.enums.EnumXlsFormat;
import com.zzboot.util.poi.vo.Column;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 */
public abstract class AbstractXlsStyle {

    private static Map<String,CellStyle> styleMap = new ConcurrentHashMap<String,CellStyle>();




    /**
     * 用于处理特殊的样式处理 (处理工资表， 表头是从Excel文件读取出来的)
     * 内容和标题的样式一样 , 颜色 背景色等和标题一样， 对齐方式和格式化不一样
     * @param cellStyle
     * @param clz
     * @param columnIndex
     * @param columnClz
     * @param operationModel
     * @param <T>
     * @return
     */
    public <T> CellStyle firstCommonStyle(CellStyle cellStyle ,Class<T> clz ,  int columnIndex, Class<?> columnClz, String operationModel){

        String key = "firstCommonStyle="+ this.getWorkbook().getClass().getName()+ "=" + columnIndex;

        CellStyle firstCommonStyle = styleMap.get(key);

        if(firstCommonStyle != null) {

            EnumXlsFormat formatEm = null;
            short alignment = 0;

            if(operationModel.equals(CellOperation.OPERATION_MODEL_DEFAULT)){
                if(columnClz == Date.class || columnClz == java.sql.Date.class || columnClz == Timestamp.class){
                    formatEm = EnumXlsFormat.DATE;
                    alignment = CellStyle.ALIGN_CENTER;
                }else if(columnClz == Integer.class || columnClz == Long.class){
                    formatEm = EnumXlsFormat.DIGITS;
                    alignment = CellStyle.ALIGN_RIGHT;
                }else if(columnClz == Float.class || columnClz == Double.class || columnClz == BigDecimal.class){
                    formatEm = EnumXlsFormat.CURRENCY;
                    alignment = CellStyle.ALIGN_RIGHT;
                }else{
                    formatEm = null;
                    alignment = CellStyle.ALIGN_LEFT;
                }
            }else {

                if(columnClz == Date.class || columnClz == java.sql.Date.class || columnClz == Timestamp.class){
                    formatEm = null;
                    alignment = CellStyle.ALIGN_CENTER;
                }else if(columnClz == Integer.class || columnClz == Long.class){
                    formatEm = EnumXlsFormat.DIGITS;
                    alignment = CellStyle.ALIGN_RIGHT;
                }else if(columnClz == Float.class || columnClz == Double.class || columnClz == BigDecimal.class){
                    formatEm = EnumXlsFormat.NUMBER;
                    alignment = CellStyle.ALIGN_RIGHT;
                }else{
                    formatEm = null;
                    alignment = CellStyle.ALIGN_LEFT;
                }
            }

            //todo , 如果有问题，  需要做深拷贝
            CellStyle newCellStyle = cellStyle;
            if (formatEm != null) {
                DataFormat format = getWorkbook().createDataFormat();
                newCellStyle.setDataFormat(format.getFormat(formatEm.getPattern()));
                newCellStyle.setAlignment(alignment);
            }

            firstCommonStyle = newCellStyle;
            return newCellStyle;
        }else {
            return firstCommonStyle;
        }
    }


    public CellStyle getHeaderCellStyle1(){



            CellStyle cellStyle = getWorkbook().createCellStyle();

            // 设置背景色
            short index = 0x9;
            cellStyle.setFillForegroundColor(index);

            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            //下边框
            cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
            //左边框
            cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            //上边框
            cellStyle.setBorderTop(CellStyle.BORDER_THIN);
            //右边框
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);

            //居中
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

            //居中
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

            Font font = getHeaderFont1();
            cellStyle.setFont(font);


            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.index);

            return cellStyle;

    }



    public CellStyle getHeaderCellStyle2(){



        CellStyle cellStyle = getWorkbook().createCellStyle();

        //设置背景色
        short index = 0x9;
        cellStyle.setFillForegroundColor(index);


        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);


        //下边框
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        //左边框
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        //上边框
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        //右边框
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        // 居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);


        Font font = getHeaderFont2();
        cellStyle.setFont(font);

        return cellStyle;

    }



    public CellStyle commonTitleStyle(Column column) {

            CellStyle cellStyle = getWorkbook().createCellStyle();
            cellStyle.setBorderBottom((short) 1);
            cellStyle.setBorderTop((short) 1);
            cellStyle.setBorderLeft((short) 1);
            cellStyle.setBorderRight((short) 1);


            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);

            cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
            cellStyle.setFont(getTitleFont(column));


            return cellStyle;


    }



    protected CellStyle getCellStyle(short alignment, boolean boldweight) {


        CellStyle cellStyle = getWorkbook().createCellStyle();
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderRight((short) 1);
        if (boldweight) {
            Font font = getFont();
            //选择需要用到的字体格式
            cellStyle.setFont(font);
        }

        cellStyle.setAlignment(alignment);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);

        return cellStyle;

    }


    protected CellStyle getCellStyle(short alignment, boolean boldweight, EnumXlsFormat formatEm) {



        CellStyle cellStyle = getWorkbook().createCellStyle();
        cellStyle.setBorderBottom((short) 1);
        cellStyle.setBorderTop((short) 1);
        cellStyle.setBorderLeft((short) 1);
        cellStyle.setBorderRight((short) 1);

        if (boldweight) {
            cellStyle.setFont(getFont());
        }

        cellStyle.setAlignment(alignment);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        if(formatEm != null) {
            if(formatEm.getCode() > 0){
                cellStyle.setDataFormat((short)formatEm.getCode());
            }else {
                DataFormat format = getWorkbook().createDataFormat();
                cellStyle.setDataFormat(format.getFormat(formatEm.getPattern()));
            }
        }


        setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);


        return cellStyle;

    }


    /**
     * 根据列属性设置Excel cell 样式
     * @param clz
     * @param columnIndex
     * @param columnClz
     * @param definAlignment
     * @param operationModel
     * @return
     */
    public CellStyle commonStyle(Class<?> clz , int columnIndex ,  Class<?> columnClz , short definAlignment , String operationModel){



            CellStyle cellStyle = commonStyle(columnClz , definAlignment , operationModel  );
            return cellStyle;


    }

    private CellStyle commonStyle( Class<?> columnClz, short definAlignment ,  String operationModel){



        EnumXlsFormat formatEm = null;
        short alignment = 0;
        if(operationModel.equals(CellOperation.OPERATION_MODEL_DEFAULT)){
            if(columnClz == Date.class || columnClz == java.sql.Date.class || columnClz == Timestamp.class){
                formatEm = EnumXlsFormat.DATE;
                alignment = CellStyle.ALIGN_CENTER;
            }else if(columnClz == Integer.class || columnClz == Long.class){
                formatEm = EnumXlsFormat.DIGITS;
                alignment = CellStyle.ALIGN_RIGHT;
            }else if(columnClz == Float.class || columnClz == Double.class || columnClz == BigDecimal.class){
                formatEm = EnumXlsFormat.CURRENCY;
                alignment = CellStyle.ALIGN_RIGHT;
            }else{
                formatEm = null;
                alignment = CellStyle.ALIGN_LEFT;
            }
        }else {

            if(columnClz == Date.class || columnClz == java.sql.Date.class || columnClz == Timestamp.class){
                formatEm = null;
                alignment = CellStyle.ALIGN_CENTER;
            }else if(columnClz == Integer.class || columnClz == Long.class){
                formatEm = null;
                alignment = CellStyle.ALIGN_RIGHT;
            }else if(columnClz == Float.class || columnClz == Double.class || columnClz == BigDecimal.class){
                formatEm = EnumXlsFormat.NUMBER;
                alignment = CellStyle.ALIGN_RIGHT;
            }else{
                formatEm = null;
                alignment = CellStyle.ALIGN_LEFT;
            }
        }

        if(definAlignment != CellStyle.ALIGN_GENERAL){
            alignment = definAlignment;
        }
        return getCellStyle(alignment, false, formatEm);

    }




    private CellStyle commonStyle(EnumXlsFormat formatEm) {


            CellStyle cellStyle = getWorkbook().createCellStyle();
            cellStyle.setBorderBottom((short) 1);
            cellStyle.setBorderTop((short) 1);
            cellStyle.setBorderLeft((short) 1);
            cellStyle.setBorderRight((short) 1);

            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            if(formatEm != null && formatEm != EnumXlsFormat.DATE){
                cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            }

            if(formatEm != null) {
                if(formatEm.getCode() > 0){
                    cellStyle.setDataFormat((short)formatEm.getCode());
                }else {
                    DataFormat format = getWorkbook().createDataFormat();
                    cellStyle.setDataFormat(format.getFormat(formatEm.getPattern()));
                }
            }



            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);

            return cellStyle;


    }




    public CellStyle commonStyle() {



            CellStyle cellStyle = getWorkbook().createCellStyle();
            cellStyle.setBorderBottom((short) 1);
            cellStyle.setBorderTop((short) 1);
            cellStyle.setBorderLeft((short) 1);
            cellStyle.setBorderRight((short) 1);

            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            setBorder(cellStyle, HSSFColor.BLACK.index, CellStyle.BORDER_THIN);
            return cellStyle;


    }



    protected void setBorder(CellStyle style, short color, short borderType) {

        style.setBorderBottom(borderType);
        style.setBorderLeft(borderType);
        style.setBorderTop(borderType);
        style.setBorderRight(borderType);

        style.setRightBorderColor(color);
        style.setTopBorderColor(color);
        style.setLeftBorderColor(color);
        style.setBottomBorderColor(color);
    }



    protected Font getTitleFont(Column column) {

            Font newFont = getWorkbook().createFont();
            //粗体显示
            newFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            return newFont;

    }

    protected Font getFont() {

        Font newFont = getWorkbook().createFont();
        //粗体显示
        newFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        return newFont;

    }


    private Font getHeaderFont1() {


        Font font = getWorkbook().createFont();
        font.setFontName("黑体");
        //设置字体大小
        font.setFontHeightInPoints((short) 16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        return font;


    }


    private Font getHeaderFont2() {

        Font font = getWorkbook().createFont();
        font.setFontName("黑体");
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        return font;

    }



    public abstract Workbook getWorkbook();
}
