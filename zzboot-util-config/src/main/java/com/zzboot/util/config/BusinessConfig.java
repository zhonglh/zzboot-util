package com.zzboot.util.config;


/**
 * 业务设置
 * @author Administrator
 */
public interface BusinessConfig {

    /**
     * 是否启用租户模式，即多企业
     * 该属性的内容如果要修改，在需求开始阶段就要确定, 在设计表时需要
     */
    public boolean getUseTenant();



    /**
     * 是否使用机构
     * 该属性的内容如果要修改，在需求开始阶段就要确定, 在设计表时需要
     */
    public boolean getUseOrgan();


    /**
     * 是否要启动租户拦截器
     */
    public boolean getInterceptTenant();


    /**
     * 是否要启动机构拦截器
     */
    public boolean getInterceptOrgan();



}
