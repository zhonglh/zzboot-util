package com.zzboot.util.http.pool;

import java.io.IOException;


/**
 * http请求执行器.
 *
 * @author Administrator
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 */
public interface RequestExecutor<T, E> {

  /**
   * 执行http请求.
   *
   * @param uri  uri
   * @param data 数据
   * @return 响应结果
   * @throws IOException      io异常
   */
  T execute(String uri, E data) throws RuntimeException, IOException;


  /**
   * 执行http请求.
   *
   * @param uri      uri
   * @param data     数据
   * @param handler http响应处理器
   * @throws IOException      io异常
   */
  void execute(String uri, E data, ResponseHandler<T> handler) throws RuntimeException, IOException;
}
