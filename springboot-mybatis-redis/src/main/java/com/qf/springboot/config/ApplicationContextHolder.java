package com.qf.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Title: ApplicationContextHolder.java
 * @Package com.qf.springboot.config
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月28日 下午5:47:01
 * @version V1.0
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
	}
	
	public static<T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static<T> T getBean(String name){
		return (T)getApplicationContext().getBean(name);
	}

}
