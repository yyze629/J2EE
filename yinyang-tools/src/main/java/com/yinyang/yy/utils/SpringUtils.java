package com.yinyang.yy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类
 *
 * @author 9iuorg@gmail.com 9iu.org
 * @date 2011-10-13
 */

@Component("springUtils")
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public SpringUtils() {

	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	/**
	 * 根据beanName 获取 spring bean
	 * 
	 * @param beanName
	 * @return Object
	 */
	public Object getBean(String beanName) {
		if (beanName == null)
			return null;
		return applicationContext.getBean(beanName);
	}

	/**
	 * 根据bean type 获取springBean
	 * 
	 * @param clazz
	 * @return
	 */
	public Object getBeanByType(Class<?> clazz) {
		return applicationContext.getBean(clazz);
	}

	/**
	 * 获取 Spring applicationContext
	 * 
	 * @return
	 */
	public ApplicationContext getContext() {
		return applicationContext;
	}

}
