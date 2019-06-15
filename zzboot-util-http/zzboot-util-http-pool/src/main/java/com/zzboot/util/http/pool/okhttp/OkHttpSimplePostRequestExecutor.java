package com.zzboot.util.http.pool.okhttp;

import com.zzboot.util.http.pool.RequestHttp;
import com.zzboot.util.http.pool.SimplePostRequestExecutor;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Administrator
 */
public class OkHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity) throws RuntimeException, IOException {
    logger.debug("OkHttpSimplePostRequestExecutor running");
    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
    RequestBody body = RequestBody.create(mediaType, postEntity);

    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    return responseContent;
  }

}
