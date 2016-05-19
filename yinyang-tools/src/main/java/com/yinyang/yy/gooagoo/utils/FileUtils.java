package com.yinyang.yy.gooagoo.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.yinyang.yy.common.YYException;

/**
 * 文件处理工具类
 * 
 * @author Administrator
 *
 */
public class FileUtils
{
    /**
     * 字节数组转化为文件
     * 
     * @param b
     * @param outputFile
     * @return
     * @throws YYException
     */
    public static File getFileFromBytes(byte[] b, String outputFile) throws YYException
    {
        AssertUtils.notNull(b, "【FileUtils-getFileFromBytes】传入参数b为NULL");
        AssertUtils.notEmpty(outputFile, "【FileUtils-getFileFromBytes】传入参数outputFile为空");
        mkDir(outputFile, false);
        File ret = null;
        BufferedOutputStream bos = null;
        try
        {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            bos = new BufferedOutputStream(fstream);
            bos.write(b);
            return ret;
        }
        catch (IOException e)
        {
            throw new YYException("【FileUtils-getFileFromBytes】IO异常", e, "b.length=" + b.length + ",outputFile=" + outputFile);
        }
        finally
        {
            IOUtils.closeOutputStream(bos);
        }
    }

    /**
     * 文件转化为字节数组
     * 
     * @param file
     * @return
     * @throws YYException
     */
    public static byte[] getBytesFromFile(File file) throws YYException
    {
        AssertUtils.notNull(file, "【FileUtils-getBytesFromFile】传入参数file为NULL");
        byte[] ret = null;
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try
        {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = in.read(b)) != -1)
            {
                out.write(b, 0, n);
            }
            ret = out.toByteArray();
            return ret;
        }
        catch (IOException e)
        {
            throw new YYException("【FileUtils-getBytesFromFile】IO异常", e);
        }
        finally
        {
            IOUtils.closeInputStream(in);
            IOUtils.closeOutputStream(out);
        }
    }

    /**
     * 根据扩展名获取文件名
     * 
     * @param exp
     * @return
     */
    public static String getFileName(String exp)
    {
        return UUID.getUUID() + "." + exp;
    }

    /**
     * 创建目录
     * 
     * @param writerPath 创建路径
     * @param isDir true-目录  false-文件路径
     * @throws YYException 
     */
    public static void mkDir(String path, boolean isDir) throws YYException
    {
        AssertUtils.notEmpty(path, "【FileUtils-mkDir】传入参数path为空");
        path = path.replace("\\", "/");
        File file = new File(path);
        if (isDir)
        {
            if (!file.exists())
            {
                file.mkdirs();
            }
        }
        else
        {
            File parent = new File(file.getParent());
            if (!parent.exists())
            {
                parent.mkdirs();
            }
        }
    }

    /**
     * 写文本文件
     * 
     * @param content
     * @param filePath
     * @param code
     * @throws YYException 
     */
    public static void writeTxt(String content, String filePath, String code) throws YYException
    {
        AssertUtils.notEmpty(content, "【FileUtils-writeTxt】传入参数content为空");
        AssertUtils.notEmpty(filePath, "【FileUtils-writeTxt】传入参数filePath为空");
        AssertUtils.notEmpty(code, "【FileUtils-writeTxt】传入参数code为空");
        mkDir(filePath, false);
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), code)));
            writer.write(content);
        }
        catch (Exception e)
        {
            throw new YYException("【FileUtils-writeTxt】写入TXT文件异常", e, "content=" + content + ",filePath=" + filePath + ",code=" + code);
        }
        finally
        {
            IOUtils.closeWriter(writer);
        }
    }

    /**
     * 读取TXT文件
     * 
     * @param filePath
     * @param code
     * @return
     * @throws YYException 
     */
    public static String readTxt(String filePath, String code) throws YYException
    {
        AssertUtils.notEmpty(filePath, "【FileUtils-readTxt】传入参数filePath为空");
        AssertUtils.notEmpty(code, "【FileUtils-readTxt】传入参数code为空");
        StringBuffer result = new StringBuffer();
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), code));
            String str = "";
            while ((str = bufferedReader.readLine()) != null)
            {
                result.append(str);
            }
        }
        catch (Exception e)
        {
            throw new YYException("【FileUtils-readTxt】读取TXT文件异常", e, "filePath=" + filePath + ",code=" + code);
        }
        finally
        {
            IOUtils.closeReader(bufferedReader);
        }
        return result.toString();
    }

    /**
     * 删除 目录
     * @param file 删除的目录文件
     */
    public static void deletDir(File file)
    {
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    deletDir(files[i]);
                }
                else
                {
                    files[i].delete();
                }
            }
        }

        file.delete();
    }
}
