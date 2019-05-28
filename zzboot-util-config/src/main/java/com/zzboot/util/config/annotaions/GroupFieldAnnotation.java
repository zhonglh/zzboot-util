package com.zzboot.util.config.annotaions;


import java.lang.annotation.*;


/**
 * GroupBO 对象中 属性的注解
 * 只用于 xxxGroupBO中， 如TsRoleGroupBO
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface GroupFieldAnnotation {


    /**
     * 名称
     * 如基金经理，  基金负责人
     * @return
     */
    public String filedName() default "" ;



    /**
     * 子表的外键列名称
     * 如permit_id
     * @return
     */
    public String childTableColumnName() default "" ;

}
