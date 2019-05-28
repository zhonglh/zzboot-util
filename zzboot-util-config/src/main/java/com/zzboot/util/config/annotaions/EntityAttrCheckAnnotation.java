package com.zzboot.util.config.annotaions;

import java.lang.annotation.*;

/**
 * 实体属性验证的注解类
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrCheckAnnotation {


    //检查规则  正则表达式
    //检查内容是否符合该表达式
    public String[] checkRule() default {};

    /**
     * 提示的内容
     * @return
     */
    public String[] checkTipMsg() default {};


}
