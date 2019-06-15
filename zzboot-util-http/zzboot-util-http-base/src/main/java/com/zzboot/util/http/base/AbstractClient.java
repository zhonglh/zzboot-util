package com.zzboot.util.http.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author Administrator
 */
abstract public class AbstractClient {

    protected String       url;
    protected HeaderEntity header;
    protected ParamEntity  param;

    public Logger logger = LoggerFactory.getLogger (AbstractClient.class.getName ());

    /**
     * REST GET
     * 
     * @Title: get
     * @Description: REST GET
     * @return String
     */
    public abstract String get();

    /**
     * REST DELETE
     * 
     * @Title: delete
     * @Description: REST DELETE
     * @return String
     */
    public abstract String delete();

    /**
     * REST PUT
     * 
     * @Title: put
     * @Description: REST PUT
     * @param obj
     * @return String
     */
    public abstract String put(Object obj);

    /**
     * REST POST
     * 
     * @Title: post
     * @Description: REST POST
     * @param obj
     * @return String
     */
    public abstract String post(Object obj);

    /**
     * HTTP 请求返回码
     * 
     * @Title: responseCode
     * @Description: HTTP 请求返回码
     * @return
     */
    public abstract int responseCode();

    protected HttpURLConnection con    = null;

    /**
     * @Fields status : 状态码,描述,参考RESTLET Status
     */
    protected Status            status = Status.SUCCESS_OK;

    private URL                 rooturl;

    protected AbstractClient(String url) {
        this (url, null);
    }

    protected AbstractClient(String url, HeaderEntity header) {
        this (url, header, null);
    }

    protected AbstractClient(String url, HeaderEntity header, ParamEntity param) {
        this.header = header;
        this.param = param;
        this.url = url;
        try {
            String path = setParams (url, param);
            rooturl = new URL (path);
            if ("https".equals (rooturl.getProtocol ())) {
                setDefaultCert ();
                con = (HttpsURLConnection) rooturl.openConnection ();
            } else {
                con = (HttpURLConnection) rooturl.openConnection ();
            }

            setHeader (header);
        } catch (Exception e) {
            if (null != con) {
                con.disconnect ();
            }
            e.printStackTrace();
        }
        con.setRequestProperty ("Accept-Charset", "utf-8");
        con.setReadTimeout (HttpConstants.TIMEOUT);
        System.setProperty ("jsse.enableSNIExtension", "false");
    }

    /**
     * @Title: setHeader
     * @Description:设置 请求头
     * @param header
     */
    private void setHeader(HeaderEntity header){
        if (null != con) {
            boolean isContainsXcat = false;
            if (null == header) { return; }
            Iterator<Map.Entry<String, String>> pairs = header.entrySet ().iterator ();
            while (pairs.hasNext ()) {
                Map.Entry<String, String> pair = pairs.next ();
                con.setRequestProperty (pair.getKey (), pair.getValue ());
            }

        } else {
            // throw new PDException (ExceptionConstants.CLIENT_VALIDATION,"Connection is empty");
        }
    }

    private String setParams(String url,ParamEntity params){
        if (null == params || params.size () == 0) { return url; }
        Map<String, String> sorted = new TreeMap<String, String> (String.CASE_INSENSITIVE_ORDER);
        sorted.putAll (params);
        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet ().iterator ();
        while (pairs.hasNext ()) {
            String[] strs = url.split ("\\?");
            Map.Entry<String, String> pair = pairs.next ();
            if (strs.length > 1) {
                if (strs[1].indexOf (pair.getKey () + "=") > -1) {
                    continue;
                }
                url = url + "&" + pair.getKey () + "=" + pair.getValue ();
            } else {
                url = url + "?" + pair.getKey () + "=" + pair.getValue ();
            }
        }
        return url;
    }

    /**
     * @Title: close
     * @Description: 关闭连接
     */
    public void close(){
        if (null != con) {
            con.disconnect ();
        }
    }

    /**
     * @Title: setTimeout
     * @Description:设置超时
     */
    public void setTimeout(int timeout){
        if (null != con) {
            if (timeout > HttpConstants.TIMEOUT) {
                con.setReadTimeout (timeout);
            }
            else {
                con.setReadTimeout (HttpConstants.TIMEOUT);
            }
        }
    }

    /**
     * @Title: getStatus
     * @Description: 取执行状态
     * @return
     */
    public Status getStatus(){
        return status;
    }

    private void trustAllHttpsCertificates() throws Exception{
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM ();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance ("SSL");
        sc.init (null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory (sc.getSocketFactory ());

    }

    public class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers(){
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs){
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs){
            return true;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException{
            return;
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException{
            return;
        }
    }

    private void setDefaultCert() throws Exception{
        HostnameVerifier hv = new HostnameVerifier () {

            @Override
            public boolean verify(String urlHostName, SSLSession session){
                return true;
            }
        };
        trustAllHttpsCertificates ();
        HttpsURLConnection.setDefaultHostnameVerifier (hv);
    }
}
