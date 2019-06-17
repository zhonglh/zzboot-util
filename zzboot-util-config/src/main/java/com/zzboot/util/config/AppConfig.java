package com.zzboot.util.config;

import com.zzboot.util.config.enums.EnumAppRunModel;

/**
 * 系统设置
 * @author admin
 */
public interface AppConfig {

    public EnumAppRunModel getRunModel();

    /**
     * 验证方式
     * 0: 不用验证码 ， 1：使用验证码 ， 2：失败一次后使用验证码
     */
    public String getCaptchaType() ;

    /**
     * 用户最大并发登陆个数
     * @return
     */
    public int getOneUserMaxConnections() ;

    /**
     * 密码重试次数
     * @return
     */
    public int getMaxPasswordRetry();


    /**
     * 登陆失败，用户锁定时间
     * 单位：分钟
     * @return
     */
    public int getMaxLockTime();

    /**
     * Session 失效时间
     * @return
     */
    public int getSessionTimeout() ;


    /**
     * 系统是否可以自助注册
     * @return
     */
    public String getSelfHelpRegistration();

    /**
     * 是否需要计算IP所在地址
     * @return
     */
    public boolean isAddressEnabled();



    /**
     * 是否有面包屑(导航)功能
     */
    public boolean useCrumb();


    /**
     * 菜单的最大层级
     */
    public int getMenuMaxLevel();

}
