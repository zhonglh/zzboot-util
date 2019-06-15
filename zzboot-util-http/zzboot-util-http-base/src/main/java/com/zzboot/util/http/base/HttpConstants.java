package com.zzboot.util.http.base;


/**
 * @author Administrator
 */
public final class HttpConstants {

    /**
     * @Fields METHOD_GET : GET
     */
    public final static String METHOD_GET    = "GET";
    /**
     * @Fields METHOD_PUT : PUT
     */
    public final static String METHOD_PUT    = "PUT";
    /**
     * @Fields METHOD_DELETE : DELETE
     */
    public final static String METHOD_DELETE = "DELETE";
    /**
     * @Fields METHOD_POST : POST
     */
    public final static String METHOD_POST   = "POST";
    /**
     * @Fields TIMEOUT : http 超时
     */
    public final static int    TIMEOUT       = 1000 * 60 ;

    public static final String CHARSET       = "UTF-8";
    public static final String X_AUTH_ACCESS_TIME   = "access_time";
    public static final String X_AUTH_INTERIM_TOKEN = "interim_token";
    public static final String X_AUTH_ACCESS_TOKEN  = "access_token";
    public static final String X_AUTH_MACHINE_CODE  = "machine_code";

    /**
     * <p>
     * Title: 私有构造函数
     * </p>
     * <p>
     * Description:禁止实例化
     * </p>
     */
    private HttpConstants() {}

}
