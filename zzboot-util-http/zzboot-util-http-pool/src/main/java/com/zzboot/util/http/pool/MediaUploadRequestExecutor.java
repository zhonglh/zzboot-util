package com.zzboot.util.http.pool;

import com.alibaba.fastjson.JSONObject;
import com.zzboot.util.http.pool.apache.ApacheMediaUploadRequestExecutor;
import com.zzboot.util.http.pool.jodd.JoddHttpMediaUploadRequestExecutor;
import com.zzboot.util.http.pool.okhttp.OkHttpMediaUploadRequestExecutor;

import java.io.File;
import java.io.IOException;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 * @author Administrator
 */
public abstract class MediaUploadRequestExecutor<H, P> implements RequestExecutor<JSONObject, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<JSONObject> handler) throws RuntimeException, IOException {
    handler.handle(this.execute(uri, data));
  }

  public static RequestExecutor<JSONObject, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
