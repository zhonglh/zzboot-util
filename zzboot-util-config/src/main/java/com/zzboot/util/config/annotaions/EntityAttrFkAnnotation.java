package com.zzboot.util.config.annotaions;

import java.lang.annotation.*;

/**
 * 实体属性是字典的注解类
 */


@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrFkAnnotation {

    /**
     * 组编号
     * 例如 project
     * 比如 project_id , project_name , project_code 是一组
     * @return
     */
    public String group() ;

    /**
     * 组名称
     * 例如 项目
     * @return
     */
    public String groupName();




    /**
     * 是否为外键值 ,  比如 project_id 为 true
     * @return
     */
    public boolean isFkId() default false;



    /**
     * 组对应的列名称 ,
     * 例如 project_id ,  project_code , project_name
     * @return
     */
    public String dbColumnName() default "id";


    /**
     * 对应列的类型
     * 如 char , varchar , date
     * @return
     */
    public String dbColumnType() default "char";

    /**
     * 对应列的长度
     * @return
     */
    public int dbColumnLength() default 32;

    /**
     * 小数点长度
     * @return
     */
    public int dbColumnDecimals() default 0;

    /**
     * 对应列是否可以为空
     * @return
     */
    public boolean dbColumnNotNull() default false;



    /**
     * 外键对应的实体类全称
     * fkClassName  fkClass 两者必填一个
     * 例： "com.zz.bms.system.bo.TsUserBO"
     * @return
     */
    public String fkClassName() default "";

    /**
     * 外键对应的实体类全称
     * fkClassName  fkClass 两者必填一个
     * 例： com.zz.bms.system.bo.TsUserBO.class
     * @return
     */
    public Class fkClass() default Object.class;

}


