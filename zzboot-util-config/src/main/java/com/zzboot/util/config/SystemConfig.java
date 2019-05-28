package com.zzboot.util.config;

/**
 * 系统设置
 * @author admin
 */
public interface SystemConfig {



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


}
