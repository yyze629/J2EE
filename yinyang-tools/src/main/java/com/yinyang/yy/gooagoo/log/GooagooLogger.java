package com.yinyang.yy.gooagoo.log;

import org.apache.log4j.Logger;

/**
 * 父日志工具类
 * 
 * @author Administrator
 *
 */
public abstract class GooagooLogger
{
    private Logger log = null;

    public GooagooLogger(String name)
    {
        this.log = Logger.getLogger(name);
    }

    public GooagooLogger(Class<?> clazz)
    {
        this.log = Logger.getLogger(clazz);
    }

    public void debug(String message)
    {
        if (this.log.isDebugEnabled())
        {
            this.log.debug(message);
        }
    }

    public void info(String message)
    {
        if (this.log.isInfoEnabled())
        {
            this.log.info(message);
        }
    }

    public void warn(String message)
    {
        this.log.warn(message);
    }

    public void error(String message)
    {
        this.log.error(message);
    }

    public void error(String message, Throwable e)
    {
        this.log.error(message, e);
    }
}
