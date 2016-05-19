package com.yinyang.yy.gooagoo.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import javax.imageio.stream.ImageInputStream;

import com.yinyang.yy.common.YYException;


/** 
 * IO工具类
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年4月3日 下午5:19:16 
 * @version 1.0  
 */
public class IOUtils
{
    /**
     * 关闭输入流
     * 
     * @param inputStream
     * @throws YYException
     */
    public static void closeInputStream(InputStream inputStream) throws YYException
    {
        if (inputStream != null)
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-closeInputStream】关闭inputStream异常", e);
            }
        }
    }
    
    /**
     * 关闭输入流
     * 
     * @param inputStream
     * @throws YYException
     */
    public static void closeInputStreamReader(InputStreamReader inputStreamReader) throws YYException
    {
    	if (inputStreamReader != null)
    	{
    		try
    		{
    			inputStreamReader.close();
    		}
    		catch (IOException e)
    		{
    			throw new YYException("【IOUtils-inputStreamReader】关闭inputStreamReader异常", e);
    		}
    	}
    }
    
    /**
     * 关闭输入流
     * 
     * @param inputStream
     * @throws YYException
     */
    public static void closeFileInputStream(FileInputStream fileInputStream) throws YYException
    {
    	if (fileInputStream != null)
    	{
    		try
    		{
    			fileInputStream.close();
    		}
    		catch (IOException e)
    		{
    			throw new YYException("【IOUtils-fileInputStream】关闭fileInputStream异常", e);
    		}
    	}
    }

    /**
     * 关闭Reader
     * 
     * @param reader
     * @throws YYException
     */
    public static void closeReader(Reader reader) throws YYException
    {
        if (reader != null)
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-closeReader】关闭reader异常", e);
            }
        }
    }

    /**
     * 关闭输出流
     * 
     * @param outputStream
     * @throws YYException
     */
    public static void closeOutputStream(OutputStream outputStream) throws YYException
    {
        if (outputStream != null)
        {
            try
            {
                outputStream.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-closeOutputStream】关闭outputStream异常", e);
            }
        }
    }

    /**
     * 关闭Writer
     * 
     * @param writer
     * @throws YYException
     */
    public static void closeWriter(Writer writer) throws YYException
    {
        if (writer != null)
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-closeWriter】关闭writer异常", e);
            }
        }
    }

    /**
     * 关闭图片输入流
     * 
     * @param outputStream
     * @throws YYException
     */
    public static void closeImageInputStream(ImageInputStream imageInputStream) throws YYException
    {
        if (imageInputStream != null)
        {
            try
            {
                imageInputStream.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-closeImageInputStream】关闭imageInputStream异常", e);
            }
        }
    }
    
    /**
     * 关闭输入流
     * 
     * @param bufferedReader
     * @throws YYException
     */
    public static void closeBufferedReader(BufferedReader bufferedReader) throws YYException
    {
        if (bufferedReader != null)
        {
            try
            {
            	bufferedReader.close();
            }
            catch (IOException e)
            {
                throw new YYException("【IOUtils-bufferedReader】关闭bufferedReader异常", e);
            }
        }
    }
}
