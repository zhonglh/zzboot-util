package com.zzboot.util.http.pool.jodd;

import com.alibaba.fastjson.JSONObject;
import com.zzboot.util.http.pool.MediaUploadRequestExecutor;
import com.zzboot.util.http.pool.RequestHttp;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class JoddHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddHttpMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public JSONObject execute(String uri, File file) throws  IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("media", file);
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String responseContent = response.bodyText();
    return JSONObject.parseObject(responseContent);
  }
}
