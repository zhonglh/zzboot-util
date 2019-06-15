package com.zzboot.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;


	@Override
	public void setApplicationContext(ApplicationContext ctx)	throws BeansException {
		ApplicationContextUtil.context = ctx;
	}

	public static ApplicationContext getContext() {
		return context;
	}



	public static void pushEvent(ApplicationEvent event) {
		context.publishEvent(event);
	}









}
