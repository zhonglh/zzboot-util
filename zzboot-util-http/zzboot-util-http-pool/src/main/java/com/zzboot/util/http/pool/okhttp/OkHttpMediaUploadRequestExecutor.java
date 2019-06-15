package com.zzboot.util.http.pool.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.zzboot.util.http.pool.MediaUploadRequestExecutor;
import com.zzboot.util.http.pool.RequestHttp;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Administrator
 */
public class OkHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkHttpMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public JSONObject execute(String uri, File file) throws RuntimeException, IOException {
    logger.debug("OkHttpMediaUploadRequestExecutor is running");
    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    RequestBody body = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();
    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    return JSONObject.parseObject(responseContent);
  }

}
