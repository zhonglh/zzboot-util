package com.zzboot.util.config;

/**
 * 全局变量
 * @author admin
 */
public class Global {

    private static AppConfig appConfig;


    public static AppConfig getAppConfig() {
        return Global.appConfig;
    }

    public static void setAppConfig(AppConfig appConfig) {
        Global.appConfig = appConfig;
    }


    public static UserConfig getUserConfig(){
        return null;
    }
}
