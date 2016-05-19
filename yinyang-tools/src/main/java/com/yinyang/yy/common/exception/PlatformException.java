package com.yinyang.yy.common.exception;

import com.yinyang.yy.common.YYException;

/**
 * 平台异常类
 * @author Administrator
 *
 */
public class PlatformException extends YYException
{
    private static final long serialVersionUID = 1L;

    public PlatformException(String message)
    {
        super(message);
    }

    public PlatformException(String message, Throwable e)
    {
        super(message, e);
    }
}
