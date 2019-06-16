package com.zzboot.util.config;

/**
 * @author admin
 */
public interface UserConfig {




    //Excel 设置


    /**
     * Excel操作模式
     * DefaultCellOperation     默认的操作， 导入导出使用和相应的类型， 比如Java属性为日期， Excel中Cell的类型也是日期
     * StringCellOperation      在Excel中所有的列属性都是文本类型
     */
    public String getExcelOperationModel();



    /**
     * Excel 是否有序号列
     */
    public boolean getExcelAddNumber();


    /**
     * Excel 是否导出头信息
     */
    public boolean getExcelExportHeader();


    /**
     * Excel 导入导出列是否相同
     * 如果设置为相同， 导出的数据可以用于导入
     */
    public boolean getExcelExportImpartSame();


    /**
     * 导入所有的Sheet数据
     */
    public boolean getExcelImportAllSheet();



    /**
     * 表头占用的列数
     */
    public int getExcelHeaderMaxCells();


    /**
     * 导入的数据都正确才保存入库
     */
    public boolean getExcelNoErrorSave();



    /**
     * 每页的最大记录数
     * @return
     */
    public Integer getPageLimit();




    //admintle 设置 ， 比如皮肤， 固定头部等

    /**
     * 固定头部内容
     * @return
     */
    public boolean getFixedLayout();


    /**
     * 箱体布局
     * @return
     */
    public boolean getBoxedLayout();


    /**
     * 获取个性设置
     * @return
     */
    public String getTheme();

}
