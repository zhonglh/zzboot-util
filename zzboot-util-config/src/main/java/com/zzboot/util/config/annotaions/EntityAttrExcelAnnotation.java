package com.zzboot.util.config.annotaions;


import java.lang.annotation.*;

/**
 * 实体属性Excel的注解类
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrExcelAnnotation {





    /**
     * Excel 处理逻辑
     * 0：不导入也不导出
     * 1: 只导入
     * 2: 只导出
     * 3: 导入导出
     * 参考 EnumExcelType
     * @return
     */
    public String excelProcess() default "3";

    /**
     * 水平对齐方式
     * 参考 org.apache.poi.ss.usermodel.CellStyle
     * 初始值  0(CellStyle.ALIGN_GENERAL)
     * @return
     */
    public short alignment() default 0 ;

    /**
     * 宽度， 0 为默认默认宽度
     * @return
     */
    public int width() default 0;


    /**
     * 提示信息
     */
    public String prompt() default "";

}
