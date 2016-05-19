package com.yinyang.yy.gooagoo.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import com.yinyang.yy.common.YYException;

/**
 * 加载工具类
 * 
 * @author Administrator
 *
 */
public class LoadUtils
{
    /**目录分隔符*/
    public final static String FILE_SEP;
    /**系统默认文件夹*/
    public final static String SYSTEM_TEMPDIR;
    static
    {
        FILE_SEP = "/";
        SYSTEM_TEMPDIR = System.getProperty("java.io.tmpdir");
    }

    /**
     * 获取类路径下某一个文件夹所有的文件名
     * @param packageName 包名
     * @param ext 扩展名
     * @return
     * @throws YCSMException
     */
    public static List<String> getPackageAllFileName(String packageName, String ext) throws YYException
    {
        if (StringUtils.isEmpty(packageName))
        {
            throw new YYException("包名为空");
        }

        List<String> resultList = new ArrayList<String>();
        URL url = UrlUtils.getUrl(packageName.replace(".", "/"));
        if (url == null)
        {
            throw new YYException("包" + packageName + "对应的URL为空");
        }

        String path = url.getPath();
        if (path.indexOf(".jar!") == -1)
        {
            File packeageDir = null;
            try
            {
                packeageDir = new File(url.toURI());
            }
            catch (URISyntaxException e)
            {
                throw new YYException("扫描包" + packageName + "发生异常", e);
            }
            List<String> list = new ArrayList<String>();
            getALLClassName(packeageDir, ext, list);
            for (int i = 0; i < list.size(); i++)
            {
                String className = list.get(i);
                resultList.add(className.substring(className.indexOf(packageName)));
            }
        }
        else
        {
            String jarPath = path.replace("file:/", "");
            try
            {
                JarFile jarFile = new JarFile(jarPath.substring(0, jarPath.indexOf(".jar") + 4));
                resultList = getALLJarClassName(jarFile, packageName);
            }
            catch (IOException e)
            {
                throw new YYException("扫描包" + packageName + "发生异常", e);
            }

        }
        return resultList;
    }

    /**
     * 获取系统临时文件目录
     * @param userDir
     * @return
     */
    public static String getDefaultTempDir(String userDir)
    {
        if (StringUtils.isNotEmpty(userDir))
        {
            return SYSTEM_TEMPDIR + userDir + FILE_SEP;
        }
        else
        {
            return SYSTEM_TEMPDIR;
        }
    }

    /**
     * 获取文件名
     * @param url
     * @return
     */
    public static String getFileName(String url)
    {
        if (StringUtils.isEmpty(url))
        {
            return "";
        }
        String new_url = url.replaceAll("\\", "/");
        return StringUtils.substringAfterLast(new_url, "/");
    }

    /**
     * 获取非JAR包所有类名 
     * @param dir
     * @param list
     */
    private static void getALLClassName(File dir, String ext, List<String> list)
    {
        if (dir.isDirectory())
        {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    getALLClassName(files[i], ext, list);
                }
                else
                {
                    String fileName = files[i].getAbsolutePath().replace("/", ".").replace("\\", ".");
                    if (fileName.endsWith(ext))
                    {
                        list.add(fileName.substring(0, fileName.length() - ext.length()));
                    }
                }
            }
        }
    }

    /**
     * 获取JAR包所有类名
     * @param jarFile
     * @param packageName
     * @return
     */
    private static List<String> getALLJarClassName(JarFile jarFile, String packageName)
    {
        List<String> list = new ArrayList<String>();
        Enumeration<JarEntry> entrys = jarFile.entries();
        while (entrys.hasMoreElements())
        {
            JarEntry jar = entrys.nextElement();
            String fileName = jar.getName().replace("/", ".").replace("\\", ".");
            if (fileName.indexOf(packageName) != -1 && fileName.endsWith(".class"))
            {
                list.add(fileName.substring(0, fileName.indexOf(".class")));
            }
        }
        return list;
    }

    public static void main(String[] args) throws YYException
    {
        System.out.println(getDefaultTempDir("citylife"));
    }
}
