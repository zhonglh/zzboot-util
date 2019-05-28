package com.zzboot.util.config.annotaions;

import java.lang.annotation.*;

/**
 * 实体属性是字典的注解类
 */


@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrDictAnnotation {

    /**
     * 组编号
     * 例如 USER_STATUS
     * 比如 user_status  和 user_status_name
     * @return
     */
    public String group() ;

    /**
     * 组名称
     * 例如 用户状态
     * @return
     */
    public String groupName() ;


    /**
     * 组对应的列名称 ,
     * dict_name , 或者  dict_val
     * @return
     */
    public String dbColumnName() default "dict_val";


    /**
     * 对应列的类型
     * 如 char , varchar , date
     * @return
     */
    public String dbColumnType() default "varchar" ;

    /**
     * 对应列的长度
     * @return
     */
    public int dbColumnLength() default 2;


    /**
     * 对应列是否可以为空
     * @return
     */
    public boolean dbColumnNotNull() default true;




    /**
     * 是否值列
     * 比如 userStatus 为 true
     * @return
     */
    public boolean isValueField() default false;


    /**
     * 是否名称列
     * 比如 userStatusName 为 true
     * @return
     */
    public boolean isNameField() default false;

    /**
     * 字典类型
     * 如 yes_no
     * @return
     */
    public String dictType() ;



    /**
     * 外键对应的实体类全称
     * 例： com.zz.bms.system.VsUser
     * @return
     */
    public String dictEntity() default "com.zz.bms.system.bo.TsDictBO" ;



}


