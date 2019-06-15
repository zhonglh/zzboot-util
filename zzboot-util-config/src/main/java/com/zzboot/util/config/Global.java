package com.zzboot.util.config;

/**
 * 全局变量
 * @author admin
 */
public class Global {

    private static SystemConfig systemConfig;


    public static SystemConfig getSystemConfig() {
        return Global.systemConfig;
    }

    public static void setSystemConfig(SystemConfig systemConfig) {
        Global.systemConfig = systemConfig;
    }
}
