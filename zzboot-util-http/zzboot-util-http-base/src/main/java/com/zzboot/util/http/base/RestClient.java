package com.zzboot.util.http.base;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: RestClient
 * @Title:
 * @Description:REST client 具体实现
 * @Version:1.0
 */
public class RestClient extends AbstractClient {


    /**
     * @param url
     *            请求URL
     */
    public RestClient(String url) {
        super (url);
    }

    /**
     * @param url
     *            请求URL
     * @param header
     *            请求属性
     */
    public RestClient(String url, HeaderEntity header) {
        super (url, header);
    }

    /**
     * @param url
     *            请求URL
     * @param header
     *            请求属性
     */
    public RestClient(String url, HeaderEntity header, ParamEntity param) {
        super (url, header, param);
    }

    boolean isRetry = true;

    /**
     * @Title: rest
     * @Description: rest 请求
     * @param parameter
     * @param restMethod
     * @return String
     */
    private String rest(String parameter,String restMethod){
        String result = "";
        try {
            con.setRequestMethod (restMethod);
            logger.debug ("Request :" + con.getURL ().toString () + " | Method :" + restMethod);
            Map<String, List<String>> requestMap = con.getRequestProperties ();
            for ( Map.Entry<String, List<String>> entry : requestMap.entrySet () ) {
                logger.debug (entry.getKey () + " ： " + entry.getValue ());
            }

            // 如果请求方法为PUT,POST和DELETE设置DoOutput为真
            if (!HttpConstants.METHOD_GET.equals (restMethod)) {
                con.setUseCaches (false);
                logger.debug ("Parameter: " + parameter);
                con.setDoOutput (true);
                con.setDoInput (true);
                if (!HttpConstants.METHOD_DELETE.equals (restMethod) && parameter != null) { // 请求方法为PUT或POST时执行
                    OutputStream os = null;
                    os = con.getOutputStream ();
                    os.write (parameter.getBytes ("UTF-8"));
                    os.close ();
                }
            }

            status = Status.valueOf (responseCode ());
            InputStream in = null;
            if (status.isSuccess ()) {
                in = con.getInputStream ();
            } else {
                in = con.getErrorStream ();
            }
            String contextType = con.getContentType ().toLowerCase ();
            if ("image/jpg".indexOf (contextType) > -1) {// 图片
                savePic (in);
            } else {
                if (null != in) {
                    BufferedReader br = new BufferedReader (new InputStreamReader (in,getCharSet (contextType)));
                    StringBuffer buffer = new StringBuffer ();
                    String line = "";
                    while ((line = br.readLine ()) != null) {
                        buffer.append (line);
                    }
                    in.close ();
                    result = buffer.toString ();
                }
            }
            if (!this.getStatus ().isSuccess ()) {
                String errorDesc = getStatus ().getDescription ();
                int errorCode = this.getStatus ().getCode ();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new RuntimeException (e1);
        } finally {
            if (null != con) {
                con.disconnect ();
            }
        }
        logger.debug ("Result: " + result);
        return result;
    }

    @Override
    public String get(){
        return rest (null, HttpConstants.METHOD_GET);
    }

    @Override
    public String delete(){
        return rest (null, HttpConstants.METHOD_DELETE);
    }

    @Override
    public String put(Object obj){
        return rest ((null != obj) ? (String) obj : "", HttpConstants.METHOD_PUT);
    }

    @Override
    public String post(Object obj){
        return rest ((null != obj) ? (String) obj : "", HttpConstants.METHOD_POST);
    }

    @Override
    public int responseCode(){
        try {
            return con.getResponseCode ();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getCharSet(String contextType){
        if (StringUtils.isBlank (contextType)) { return "utf-8"; }
        String[] contextTypes = contextType.split (";");
        if (contextTypes.length > 0) {
            for ( String string : contextTypes ) {
                if (string.trim ().startsWith ("charset")) {
                    return string.trim ().split ("=")[1].trim ();
                }
            }
        }
        return "utf-8";
    }

    private String savePic(InputStream inputStream){
        byte[] data = new byte[1024];
        int len = 0;
        String filepath = "g:\\qiyi\\ok.png";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream (filepath);
            while ((len = inputStream.read (data)) != -1) {
                fileOutputStream.write (data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        }
        return filepath;
    }

    public static void main(String[] args){
        String url = "http://192.168.1.116:8080/crp/rest/interim_tocken";
        AbstractClient client = new RestClient (url);
        String result = client.get ();
        System.out.println (result);
    }
}
