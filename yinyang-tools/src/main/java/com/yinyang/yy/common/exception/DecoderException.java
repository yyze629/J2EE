package com.yinyang.yy.common.exception;

import com.yinyang.yy.common.YYException;

/**
 * 解码器常类
 * 
 * @author Administrator
 *
 */
public class DecoderException extends YYException
{
    private static final long serialVersionUID = 1L;

    public DecoderException(String message)
    {
        super(message);
    }

    public DecoderException(String message, Throwable e)
    {
        super(message, e);
    }
}
