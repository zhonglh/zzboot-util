package com.zzboot.util.config.annotaions;

import java.lang.annotation.*;

/**
 * 实体属性数据库的注解类
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrDBAnnotation {


    /**
     * 属性名称
     * 如:用户名
     * @return
     */
    public String attrName();


    /**
     * 属性列名
     * 如:user_name
     * @return
     */
    public String attrColumn();

    /**
     * 属性类型(字段类型)
     * 如:CHAR
     * @return
     */
    public String type() ;

    /**
     * 属性长度
     * 如:32
     * @return
     */
    public int attrLength() default 100000 ;

    /**
     * 属性小数点长度
     * 如:0
     * @return
     */
    public int attrDecimals() default 0;

    /**
     * 属性是否不能为空
     * @return
     */
    public boolean notNull() default false;

    /**
     * 属性序号
     * @return
     */
    public int sort() default  9999;

}


