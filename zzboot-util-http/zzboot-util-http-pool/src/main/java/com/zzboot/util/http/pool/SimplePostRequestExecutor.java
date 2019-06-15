package com.zzboot.util.http.pool;


import com.zzboot.util.http.pool.apache.ApacheSimplePostRequestExecutor;
import com.zzboot.util.http.pool.jodd.JoddHttpSimplePostRequestExecutor;
import com.zzboot.util.http.pool.okhttp.OkHttpSimplePostRequestExecutor;

import java.io.IOException;

/**
 * 用装饰模式实现
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 * @author Administrator
 */
public abstract class SimplePostRequestExecutor<H, P> implements RequestExecutor<String, String> {
  protected RequestHttp<H, P> requestHttp;

  public SimplePostRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<String> handler) throws  IOException {
    handler.handle(this.execute(uri, data));
  }

  public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheSimplePostRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpSimplePostRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpSimplePostRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
