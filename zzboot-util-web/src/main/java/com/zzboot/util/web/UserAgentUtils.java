
package com.zzboot.util.web;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户代理实用程序工具类
 */
public class UserAgentUtils {

	/**
	 * 获取用户代理对象
	 *
	 * @param request
	 * @return
	 */
	public static UserAgent getUserAgent(HttpServletRequest request) {
		return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	}

	/**
	 * 获取设备类型
	 *
	 * @param request
	 * @return
	 */
	public static DeviceType getDeviceType(HttpServletRequest request) {
		return getUserAgent(request).getOperatingSystem().getDeviceType();
	}

	/**
	 * 是否是PC
	 *
	 * @param request
	 * @return
	 */
	public static boolean isComputer(HttpServletRequest request) {
		return DeviceType.COMPUTER.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机
	 *
	 * @param request
	 * @return
	 */
	public static boolean isMobile(HttpServletRequest request) {
		return DeviceType.MOBILE.equals(getDeviceType(request));
	}

	/**
	 * 是否是平板
	 *
	 * @param request
	 * @return
	 */
	public static boolean isTablet(HttpServletRequest request) {
		return DeviceType.TABLET.equals(getDeviceType(request));
	}

	/**
	 * 是否是手机和平板
	 *
	 * @param request
	 * @return
	 */
	public static boolean isMobileOrTablet(HttpServletRequest request) {
		DeviceType deviceType = getDeviceType(request);
		return DeviceType.MOBILE.equals(deviceType) || DeviceType.TABLET.equals(deviceType);
	}


}

