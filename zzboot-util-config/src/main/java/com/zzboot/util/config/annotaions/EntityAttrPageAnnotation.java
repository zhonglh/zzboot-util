package com.zzboot.util.config.annotaions;


import java.lang.annotation.*;

/**
 * 实体属性页面的注解类
 * group 和 groupField ,
 * 比如对应用户，有的对应用户的ID, UserName ,Code ,LoginName等
 * 那么都是对应 User组， 有的列对应用户的ID , UserName
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAttrPageAnnotation {


    /**
     * 标题
     * 例如 user_name 的标题是 用户姓名
     * @return
     */
    public String title();

    /**
     * 页面元素, 如 text , select , check , file , lookup 等
     * 参考 EnumPageElement 或 PageElementConstant
     * @return
     */
    public String pageElement() default "text";

    /**
     * 是否在编辑界面中
     * @return
     */
    public boolean existEditPage() default false;

    /**
     * 是否隐藏
     * @return
     */
    public boolean hidden() default false;

    /**
     * 是否只读
     * @return
     */
    public boolean readonly() default false;

    /**
     * 是否必须的
     * @return
     */
    public boolean required() default false;

    /**
     * 如果是数字， 最小值
     * @return
     */
    public int min() default 0 ;


    /**
     * 如果是数字， 最大值
     * @return
     */
    public int max() default Integer.MAX_VALUE ;


    /**
     * 界面上最小输入长度
     * @return
     */
    public int minLength() default 0 ;


    /**
     * 界面上最大输入长度
     * @return
     */
    public int maxLength() default Integer.MAX_VALUE ;


    /**
     * 是否在列表中显示
     * @return
     */
    public boolean listShowable() default false;



    /**
     * 顺序
     * @return
     */
    public int sort() default 999999999;


    /**
     * 默认值 , 会有一些特殊的值需要解析(如当前日期， 当前登录人ID)
     * 参考 EnumDefaultType
     * @return
     */
    public String defaultType() default  "" ;


    public String defaultValue() default  "" ;

}
