package com.zzboot.util.config.annotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体注解类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityAnnotation {


    /**
     * 资源
     * 如用户的 资源为 sys.user
     * @return
     */
    public String resource() default "" ;


    /**
     * 实体名称
     * 如用户
     * @return
     */
    public String value() default "";


    /**
     * 该实体的业务名称列的列名称， 如果是组合， 多个列用 ,; 分割
     * 如 "phone" , 或者 "user_name"  ,  或者 "id_type , id_no"
     * @return
     */
    public String businessName() default "";


    /**
     * 该实体的业务唯一识别列的列名称, 可以有多个， 如果是组合， 用 ,; 分割
     * 如用户实体 ， 可以是登录名  身份证号码， 手机号 , {"login_name","idNumber","phone"}
     * 如账户册， 是通过 账户册类型+编号来识别  {"account_type_id,account_codde"}
     * @return
     */
    public String[] businessKey() default {};

    /**
     * 是否有租户列
     * @return
     */
    public boolean haveTenant() default false;


    /**
     * 是否有机构列
     * @return
     */
    public boolean haveOrgan() default false;




    /**
     * 上级ID的列名称
     * 如 pid , parent_id
     * 树状态结构才需要设置
     * @return
     */
    public String parentColumnName() default "";


    /**
     * 树节点显示的列名称
     * 如 dep_name , menu_name
     * 树状态结构才需要设置
     * @return
     */
    public String textColumnName() default "";

}
