package com.zzboot.util.http.pool;

/**
 * <pre>
 * http请求响应回调处理接口
 * </pre>
 *
 * @author Administrator
 * @param <T> 返回值类型
 */
public interface ResponseHandler<T> {
  /**
   * 响应结果处理.
   *
   * @param t 要处理的对象
   */
  void handle(T t);
}
