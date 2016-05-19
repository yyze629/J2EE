package com.yinyang.yy.gooagoo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DownLoadUtil
{
	
	private static Log log = LogFactory.getLog(DownLoadUtil.class);
	
    /**
     * 下载资源文件，写入本地
     * @param urlPath http地址
     * @param storFile 保存在本地的文件
     * @return mac 转发器mac，可以为空
     * @throws IOException 
     */
    public static void downloadFile(String urlPath, File storFile, String mac) throws IOException
    {
        if (StringUtils.isBlank(urlPath))
        {
            throw new IllegalArgumentException(String.format("下载文件,url参数为空.url[%s],mac[%s]", urlPath, mac));
        }
        if (storFile == null)
        {
            throw new IllegalArgumentException(String.format("下载文件,保存本地文件参数为空.localFile[%s],mac[%s]", storFile, mac));
        }

        URLConnection conn = null;
        InputStream in = null;
        FileOutputStream fos = null;
        String name = storFile.getName();
        long fileSize = 0;
        try
        {
            File parentFile = storFile.getParentFile();
            if (!parentFile.exists())
            {
                parentFile.mkdirs();
            }

            int begin = urlPath.indexOf("?");
            if (begin != -1)
            {
                urlPath = urlPath.substring(0, begin);
            }
            int index = urlPath.lastIndexOf("/");
            String baseUrl = urlPath.substring(0, index + 1);
            String lastUrl = URLEncoder.encode(urlPath.substring(index + 1), "utf-8");

            URL url = new URL(baseUrl + lastUrl);
            conn = url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setUseCaches(false);
            in = conn.getInputStream();
            fos = new FileOutputStream(new File(parentFile.getAbsoluteFile(), storFile.getName()));

            byte[] buffer = new byte[1024];
            int temp = 0;
            while ((temp = in.read(buffer)) != -1)
            {
                fos.write(buffer, 0, temp);
            }
            fos.flush();

            fileSize = storFile.length();
            if (fileSize <= 0)
            {
                throw new IllegalArgumentException(String.format("下载文件[%s]不完整.文件大小是[%s]", name, fileSize));
            }

            log.debug(String.format("下载文件成功.localFile[%s],fileSize[%s],mac[%s],url[%s]", storFile.getAbsoluteFile(), fileSize, mac, urlPath));
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                storFile.delete();//下载不成功，删除不完整文件
                log.debug(String.format("[%s]配置文件数据完整,删除.文件大小[%s]", name, fileSize));
            }
            catch (Exception e2)
            {
            }
            throw new IOException(String.format("下载文件异常.mac[%s],localFile[%s],url[%s]", mac, storFile.getAbsoluteFile(), urlPath), e);
        }
        catch (Exception e)
        {
            throw new IOException(String.format("下载文件异常.mac[%s],localFile[%s],url[%s]", mac, storFile.getAbsoluteFile(), urlPath), e);
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                }
            }
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }

}
