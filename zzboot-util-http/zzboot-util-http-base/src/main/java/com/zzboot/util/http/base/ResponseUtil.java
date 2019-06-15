package com.zzboot.util.http.base;

import com.zzboot.util.base.java.ObjectUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class ResponseUtil {
	
	private static final int BUFFER_SIZE= 4096;
	
	/**
	 * 断点续传下载时接收客户端请求头部的RANGE参数值匹配正则
	 * bytes=1000-
	 * bytes=1000-1024
	 */
	private static final Pattern RANGE_PATTERN= Pattern.compile("^bytes=\\d+\\-(\\d+)*$");
	
	/**
	 * 请求头RANGE参数匹配成功后抽取起始字节的正则
	 */
	private static final Pattern RANGE_BYTE_PATTERN= Pattern.compile("\\d+");
	
	public static boolean sendMessageNoCache(HttpServletResponse response, String message) {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
		response.setDateHeader(HttpHeaders.EXPIRES, 0);
		response.setHeader(HttpHeaders.PRAGMA, "no-cache");
		StringBuffer sb = new StringBuffer();
		try {
			PrintWriter writer = response.getWriter();
			sb.append(message);
			writer.write(sb.toString());
			response.flushBuffer();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean sendJSON(HttpServletResponse response, String json){
		response.setContentType("text/json;charset=utf-8");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
		response.setDateHeader(HttpHeaders.EXPIRES, 0);
		response.setHeader(HttpHeaders.PRAGMA, "no-cache");
		StringBuffer sb = new StringBuffer();
		try {
			PrintWriter writer = response.getWriter();
			sb.append(json);
			writer.write(sb.toString());
			response.flushBuffer();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 字节流中继,支持断点续传
	 * @param request
	 * @param response
	 * @param httpUtil http组件
	 * @param url 远程url
	 * @param queryParams 查询参数
	 * @return
	 * @throws URISyntaxException 
	 */
	public static boolean streamRelay(HttpServletRequest request, HttpServletResponse response, HttpUtil httpUtil, String url, Map<String, String> queryParams) throws URISyntaxException{
		if (StringUtils.isBlank(url)){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		//提取分段下载的参数
		Long rangeStart= null;
		Long rangeMax= null;
		do{
			String range= request.getHeader(HttpHeaders.RANGE);
			if (StringUtils.isBlank(range)){
				break;
			}
			Matcher rangeMatcher= RANGE_PATTERN.matcher(range);
			if (!rangeMatcher.matches()){
				break;
			}
			Matcher byteMatcher= RANGE_BYTE_PATTERN.matcher(range);
			boolean findStart= false;
			while(byteMatcher.find()){
				//第一个匹配到的是起始字节
				if (!findStart){
					rangeStart= Long.valueOf(byteMatcher.group());
					findStart= true;
					continue;
				}
				//第二个匹配到的是终止字节
				if (findStart){
					rangeMax= Long.valueOf(byteMatcher.group());
					break;
				}
			}
		}while(false);
		HttpResponse relayResp = httpUtil.doGetWithRetry(url, queryParams, 1, rangeStart, rangeMax);
		if (ObjectUtil.isEmpty(relayResp)){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		int status= relayResp.getStatusLine().getStatusCode();
		if (status== HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE){
			response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return false;
		}
		if (status== HttpServletResponse.SC_PARTIAL_CONTENT){
			Header contentRange= relayResp.getFirstHeader(HttpHeaders.CONTENT_RANGE);
			if (ObjectUtil.isNotEmpty(contentRange)){
				response.setHeader(HttpHeaders.CONTENT_RANGE, contentRange.getValue());
			}
		}
		response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(relayResp.getEntity().getContentLength()));
		Header contentType= relayResp.getFirstHeader(HttpHeaders.CONTENT_TYPE);
		if (ObjectUtil.isNotEmpty(contentType)){
			response.setContentType(contentType.getValue());
		}
		response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
		response.setStatus(status);
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
		response.setDateHeader(HttpHeaders.EXPIRES, 0);
		response.setHeader(HttpHeaders.PRAGMA, "no-cache");
		OutputStream out= null;
		InputStream in= null;
		try {
			out= response.getOutputStream();
			in= relayResp.getEntity().getContent();
			byte[] buffer= new byte[BUFFER_SIZE];
			int readLen= 0;
			while((readLen= in.read(buffer))>=0){
				out.write(ArrayUtils.subarray(buffer, 0, readLen));
			}
			out.flush();
		} catch (IOException e) {
			return false;
		} finally{
			if (ObjectUtil.isNotEmpty(in)){
				try {
					in.close();
				} catch (IOException e) {
					return false;
				}
			}
			if (ObjectUtil.isNotEmpty(out)){
				try {
					out.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 支持断点续传的文件发送
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	public static boolean sendFile(HttpServletRequest request, HttpServletResponse response, String fileName){
		File file= new File(fileName);
		if (!file.exists() || !file.isFile()){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}else{
			String range= request.getHeader(HttpHeaders.RANGE);
			long offset= 0L;
			long maxPos= Long.MAX_VALUE -1;
			if (StringUtils.isNotBlank(range)){
				Matcher rangeMatcher= RANGE_PATTERN.matcher(range);
				if (rangeMatcher.matches()){
					Matcher byteMatcher= RANGE_BYTE_PATTERN.matcher(range);
					if (byteMatcher.find()){
						int gc= byteMatcher.groupCount();
						for (int i = 0; i < gc; i++) {
							//第一个匹配到的是起始字节
							if (i==0){
								offset= Long.valueOf(byteMatcher.group(i));
								if (offset> file.length() - 1){
									response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
									return false;
								}
							}
							//第二个匹配到的是终止字节
							if (i==1){
								maxPos= Long.valueOf(byteMatcher.group(i));
								maxPos= Math.min(maxPos, file.length() - 1);
								break;
							}
						}
						if (maxPos<offset){
							offset= 0L;
							maxPos= file.length() - 1;
						}
					}
				}
			}
			if (offset==0){
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
			}else{
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				response.setHeader(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d", offset, maxPos, file.length()));
				response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(maxPos - offset + 1));
			}
			response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
			response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
			response.setDateHeader(HttpHeaders.EXPIRES, 0);
			response.setHeader(HttpHeaders.PRAGMA, "no-cache");
			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", file.getName()));
			response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
			
			BufferedOutputStream out= null;
			RandomAccessFile in= null;
			try {
				out= new BufferedOutputStream(response.getOutputStream());
				in= new RandomAccessFile(file, "r");
				in.seek(offset);
				byte[] buffer= new byte[BUFFER_SIZE];
				int readLen= 0;
				long leftSize= maxPos - offset + 1 + readLen;
				while(leftSize>0 && (readLen= in.read(buffer, 0, (int)Math.min(BUFFER_SIZE, leftSize)))>0){
					out.write(ArrayUtils.subarray(buffer, 0, readLen));
					leftSize-=readLen;
				}
				out.flush();
			} catch (IOException e) {
				return false;
			} finally{
				if (ObjectUtil.isNotEmpty(in)){
					try {
						in.close();
					} catch (IOException e) {
						return false;
					}
				}
				if (ObjectUtil.isNotEmpty(out)){
					try {
						out.close();
					} catch (IOException e) {
						return false;
					}
				}
			}
			return true;
		}
	}

}
