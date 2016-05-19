package com.yinyang.yy.common;

/**
 * YY异常类
 * 
 * @Title: YYException.java
 * @Package com.yinyang.yy.common
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 上午11:46:12
 * @version V1.0
 */
public class YYException extends Exception
{

    private static final long serialVersionUID = 1L;

    public YYException(String message)
    {
        super(message);
    }

    public YYException(String message, Throwable e)
    {
        super(message, e);
    }

    public YYException(String message, String paramInfo)
    {
        super(message + " === 【DEBUG参考信息】" + paramInfo);
    }

    public YYException(String message, Throwable e, String paramInfo)
    {
        super(message + " === 【DEBUG参考信息】" + paramInfo, e);
    }
}
