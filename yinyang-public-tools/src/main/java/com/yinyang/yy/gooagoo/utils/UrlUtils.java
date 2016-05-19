package com.yinyang.yy.gooagoo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import com.yinyang.yy.common.YYException;

/**
 * 地址及加载工具类
 * 
 * @author Administrator
 *
 */
public class UrlUtils
{

    /**
     * 读取日志配置文件
     * 
     * @throws YYException 
     */
    public static void initLog4jProperties()
    {
        //未打包时读取配置
        String file = FileUtils.class.getClassLoader().getResource("log4j.properties").getFile();
        if (new java.io.File(file).exists())
        {
            PropertyConfigurator.configure(file);
            return;
        }

        //读取jar包内配置文件
        InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("log4j.properties");
        Properties p = new Properties();
        try
        {
            p.load(in);
            PropertyConfigurator.configure(p);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 取得类所在的文件
     * @param clazz
     * @return
     */
    public static File getClassFile(Class<?> clazz)
    {
        URL path = clazz.getResource(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".classs");
        if (path == null)
        {
            String name = clazz.getName().replaceAll("[.]", "/");
            path = clazz.getResource("/" + name + ".class");
        }
        return new File(path.getFile());
    }

    /**
     * 得到类的绝对路径
     * @param clazz
     * @return
     */
    public static String getClassAbsolutePath(Class<?> clazz)
    {
        try
        {
            return java.net.URLDecoder.decode(getClassFile(clazz).getAbsolutePath(), "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 取得类所在的ClassPath目录，比如tomcat下的classes路径
     * @param clazz
     * @return
     */
    public static File getClassRootPathFile(Class<?> clazz)
    {
        File file = getClassFile(clazz);
        int count = clazz.getName().split("[.]").length;
        for (int i = 0; i < count; i++)
        {
            file = file.getParentFile();
        }
        if (file.getName().toUpperCase().endsWith(".JAR!"))
        {
            file = file.getParentFile();
        }
        return file;
    }

    /**
     * 取得当前类所在的ClassPath路径
     * @param clazz
     * @return
     */
    public static String getClassRootPath(Class<?> clazz)
    {
        try
        {
            String absolutePath = getClassRootPathFile(clazz).getAbsolutePath();
            return java.net.URLDecoder.decode(absolutePath, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取URL
     * @param fileName
     * @return
     */
    public static URL getUrl(String fileName)
    {
        return UrlUtils.class.getClassLoader().getResource(fileName);
    }
}
