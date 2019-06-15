package com.zzboot.util.http.pool.jodd;

import com.zzboot.util.http.pool.RequestHttp;
import com.zzboot.util.http.pool.SimplePostRequestExecutor;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import java.io.IOException;

/**
 * @author Administrator
 */
public class JoddHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity) throws RuntimeException, IOException {
    HttpConnectionProvider provider = requestHttp.getRequestHttpClient();
    ProxyInfo proxyInfo = requestHttp.getRequestHttpProxy();

    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);
    if (postEntity != null) {
      request.bodyText(postEntity);
    }
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);

    String responseContent = response.bodyText();
    if (responseContent.isEmpty()) {
      throw new RuntimeException("无响应内容");
    }

    if (responseContent.startsWith("<xml>")) {
      //xml格式输出直接返回
      return responseContent;
    }

    return responseContent;
  }

}
