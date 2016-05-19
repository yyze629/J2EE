package com.yinyang.yy.gooagoo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring 工具类
 * 
 * @author Administrator
 *
 */
public class SpringBeanUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name)
    {
        return applicationContext.getBean(name);
    }
}
