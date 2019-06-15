package com.zzboot.util.http.pool.okhttp;

import com.zzboot.util.http.pool.BaseMediaDownloadRequestExecutor;
import com.zzboot.util.http.pool.HttpResponseProxy;
import com.zzboot.util.http.pool.RequestHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 */
public class OkHttpMediaDownloadRequestExecutor extends BaseMediaDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkHttpMediaDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public File execute(String uri, String queryParam) throws RuntimeException, IOException {
    logger.debug("OkHttpMediaDownloadRequestExecutor is running");
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    Request request = new Request.Builder().url(uri).get().build();

    Response response = client.newCall(request).execute();

    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new RuntimeException(response.body().string());
    }

    String fileName = new HttpResponseProxy(response).getFileName();
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    File file = File.createTempFile(
      FilenameUtils.getBaseName(fileName), "." + FilenameUtils.getExtension(fileName), super.tmpDirFile
    );

    try (BufferedSink sink = Okio.buffer(Okio.sink(file))) {
      sink.writeAll(response.body().source());
    }

    file.deleteOnExit();
    return file;
  }

}
