package com.yinyang.yy.common.exception;

import com.yinyang.yy.common.YYException;

/**
 * 转发器异常类
 * @author Administrator
 *
 */
public class TransException extends YYException
{
    private static final long serialVersionUID = 1L;

    public TransException(String message)
    {
        super(message);
    }

    public TransException(String message, Throwable e)
    {
        super(message, e);
    }
}
