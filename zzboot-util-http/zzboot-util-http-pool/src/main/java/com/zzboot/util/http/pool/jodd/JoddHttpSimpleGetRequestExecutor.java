package com.zzboot.util.http.pool.jodd;

import com.zzboot.util.http.pool.RequestHttp;
import com.zzboot.util.http.pool.SimpleGetRequestExecutor;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.IOException;

/**
 * @author Administrator
 */
public class JoddHttpSimpleGetRequestExecutor extends SimpleGetRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpSimpleGetRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String queryParam) throws  IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpRequest request = HttpRequest.get(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String responseContent = response.bodyText();

    return responseContent;
  }

}
