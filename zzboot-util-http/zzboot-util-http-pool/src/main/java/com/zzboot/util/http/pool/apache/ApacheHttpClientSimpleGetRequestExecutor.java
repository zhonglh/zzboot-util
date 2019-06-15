package com.zzboot.util.http.pool.apache;

import com.zzboot.util.http.pool.RequestHttp;
import com.zzboot.util.http.pool.SimpleGetRequestExecutor;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @author Administrator
 */
public class ApacheHttpClientSimpleGetRequestExecutor extends SimpleGetRequestExecutor<CloseableHttpClient, HttpHost> {

  public ApacheHttpClientSimpleGetRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String queryParam) throws RuntimeException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }
    HttpGet httpGet = new HttpGet(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);

      return responseContent;
    } finally {
      httpGet.releaseConnection();
    }
  }

}
