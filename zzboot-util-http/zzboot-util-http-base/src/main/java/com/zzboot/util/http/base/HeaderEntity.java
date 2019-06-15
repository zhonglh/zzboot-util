package com.zzboot.util.http.base;

import java.util.HashMap;

/**
 * @author Administrator
 */
public class HeaderEntity extends HashMap<String, String> {

    private static final long serialVersionUID = 1L;


    //适用于取临时Token
    public HeaderEntity(long currTime,String machineCode) {
        put ("Content-Type", "application/json");
        put (HttpConstants.X_AUTH_ACCESS_TIME, String.valueOf(currTime));
        put (HttpConstants.X_AUTH_MACHINE_CODE, machineCode);
    }

    //适用于取访问Token
    public HeaderEntity(long currTime,String machineCode,String interimToken) {
        put ("Content-Type", "application/json");
        put (HttpConstants.X_AUTH_ACCESS_TIME, String.valueOf(currTime));
        put (HttpConstants.X_AUTH_MACHINE_CODE, machineCode);
        put (HttpConstants.X_AUTH_INTERIM_TOKEN, interimToken);
    }

    //适用于具体的API
    public HeaderEntity(long currTime,String machineCode,String interimToken,String accessToken) {
        put ("Content-Type", "application/json");
        put (HttpConstants.X_AUTH_ACCESS_TIME, String.valueOf(currTime));
        put (HttpConstants.X_AUTH_MACHINE_CODE, machineCode);
        put (HttpConstants.X_AUTH_INTERIM_TOKEN, interimToken);
        put (HttpConstants.X_AUTH_ACCESS_TOKEN, accessToken);
    }

}