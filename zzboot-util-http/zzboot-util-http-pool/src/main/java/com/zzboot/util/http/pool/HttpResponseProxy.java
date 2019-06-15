package com.zzboot.util.http.pool;

import jodd.http.HttpResponse;
import okhttp3.Response;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 三种http框架的response代理类，方便提取公共方法
 *
 * @author Binary Wang
 * @date 2017-8-3
 * </pre>
 */
public class HttpResponseProxy {
  private static final Pattern PATTERN = Pattern.compile(".*filename=\"(.*)\"");

  private CloseableHttpResponse apacheHttpResponse;
  private HttpResponse joddHttpResponse;
  private Response okHttpResponse;

  public HttpResponseProxy(CloseableHttpResponse apacheHttpResponse) {
    this.apacheHttpResponse = apacheHttpResponse;
  }

  public HttpResponseProxy(HttpResponse joddHttpResponse) {
    this.joddHttpResponse = joddHttpResponse;
  }

  public HttpResponseProxy(Response okHttpResponse) {
    this.okHttpResponse = okHttpResponse;
  }

  public String getFileName() throws RuntimeException {
    //由于对象只能由一个构造方法实现，因此三个response对象必定且只有一个不为空
    if (this.apacheHttpResponse != null) {
      return this.getFileName(this.apacheHttpResponse);
    }

    if (this.joddHttpResponse != null) {
      return this.getFileName(this.joddHttpResponse);
    }

    if (this.okHttpResponse != null) {
      return this.getFileName(this.okHttpResponse);
    }

    //cannot happen
    return null;
  }

  private String getFileName(CloseableHttpResponse response) throws RuntimeException {
    Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      throw new RuntimeException("无法获取到文件名");
    }

    return this.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
  }

  private String getFileName(HttpResponse response) throws RuntimeException {
    String content = response.header("Content-disposition");
    return this.extractFileNameFromContentString(content);
  }

  private String getFileName(Response response) throws RuntimeException {
    String content = response.header("Content-disposition");
    return this.extractFileNameFromContentString(content);
  }

  private String extractFileNameFromContentString(String content) throws RuntimeException {
    if (content == null || content.length() == 0) {
      throw new RuntimeException("无法获取到文件名");
    }

    Matcher m = PATTERN.matcher(content);
    if (m.matches()) {
      return m.group(1);
    }

    throw new RuntimeException("无法获取到文件名");
  }

}
