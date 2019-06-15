package com.zzboot.util.http.pool.apache;

import com.alibaba.fastjson.JSONObject;
import com.zzboot.util.http.pool.MediaUploadRequestExecutor;
import com.zzboot.util.http.pool.RequestHttp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class ApacheMediaUploadRequestExecutor extends MediaUploadRequestExecutor<CloseableHttpClient, HttpHost> {
  public ApacheMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public JSONObject execute(String uri, File file) throws RuntimeException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", file)
        .setMode(HttpMultipartMode.RFC6532)
        .build();
      httpPost.setEntity(entity);
    }
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);

      return JSONObject.parseObject(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }
}
