package com.yinyang.yy.gooagoo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import com.yinyang.yy.common.YYException;

/**
 * zip工具类
 * 
 * @Title: ZipUtils.java
 * @Package com.yinyang.gooagoo.common
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 上午11:24:51
 * @version V1.0
 */
public class ZipUtils
{
    private static final int BUFFEREDSIZE = 1024;

    /**
     * 压缩文件
     * 
     * @param inputFile
     * @param zipFilename
     * @throws IOException
     */
    public static void zip(File inputFile, String zipFilename) throws YYException
    {
        AssertUtils.notNull(inputFile, "【ZipUtils-zip】传入参数inputFile为NULL");
        AssertUtils.notEmpty(zipFilename, "【ZipUtils-zip】传入参数zipFilename为空");

        ZipOutputStream out = null;
        try
        {
            out = new ZipOutputStream(new FileOutputStream(zipFilename));
            zip(inputFile, out, "");
        }
        catch (IOException e)
        {
            throw new YYException("【ZipUtils-zip】IO异常", e);
        }
        finally
        {
            IOUtils.closeOutputStream(out);
        }
    }

    /**
     * 压缩文件
     * 
     * @param inputFile
     * @param out
     * @param base
     * @throws IOException
     */
    private static void zip(File inputFile, ZipOutputStream out, String base) throws YYException
    {
        AssertUtils.notNull(inputFile, "【ZipUtils-zip】传入参数inputFile为NULL");
        AssertUtils.notNull(out, "【ZipUtils-zip】传入参数out为NULL");
        if (inputFile.isDirectory())
        {
            File[] inputFiles = inputFile.listFiles();
            try
            {
                out.putNextEntry(new ZipEntry(base + "/"));
            }
            catch (IOException e)
            {
                throw new YYException("【ZipUtils-zip】IO异常", e);
            }
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < inputFiles.length; i++)
            {
                zip(inputFiles[i], out, base + inputFiles[i].getName());
            }
        }
        else
        {

            FileInputStream in = null;
            try
            {
                if (base.length() > 0)
                {
                    out.putNextEntry(new ZipEntry(base));
                }
                else
                {
                    out.putNextEntry(new ZipEntry(inputFile.getName()));
                }
                in = new FileInputStream(inputFile);

                int c;
                byte[] by = new byte[BUFFEREDSIZE];
                while ((c = in.read(by)) != -1)
                {
                    out.write(by, 0, c);
                }
                out.flush();
            }
            catch (IOException e)
            {
                throw new YYException("【ZipUtils-zip】IO异常", e);
            }
            finally
            {
                IOUtils.closeInputStream(in);
            }
        }
    }

    /**
     * 解压
     * 
     * @param zipFilename
     * @param outputDirectory
     * @throws IOException
     */
    public static void unzip(String zipFilename, String outputDirectory) throws YYException
    {
        AssertUtils.notEmpty(zipFilename, "【ZipUtils-unzip】传入参数zipFilename为空");
        AssertUtils.notEmpty(outputDirectory, "【ZipUtils-unzip】传入参数outputDirectory为空");
        File outFile = new File(outputDirectory);
        if (!outFile.exists())
        {
            outFile.mkdirs();
        }

        ZipFile zipFile = null;
        try
        {
            zipFile = new ZipFile(zipFilename);
        }
        catch (IOException e)
        {
            throw new YYException("【ZipUtils-unzip】IO异常", e, "zipFilename=" + zipFilename + ",outputDirectory=" + outputDirectory);
        }
        Enumeration<?> en = zipFile.entries();
        ZipEntry zipEntry = null;
        while (en.hasMoreElements())
        {
            zipEntry = (ZipEntry) en.nextElement();
            if (zipEntry.isDirectory())
            {
                String dirName = zipEntry.getName();
                dirName = dirName.substring(0, dirName.length() - 1);
                File f = new File(outFile.getPath() + "/" + dirName);
                f.mkdirs();
            }
            else
            {
                String strFilePath = outFile.getPath() + "/" + zipEntry.getName();
                File f = new File(strFilePath);

                if (!f.exists())
                {
                    String[] arrFolderName = zipEntry.getName().split("/");
                    String strRealFolder = "";
                    for (int i = 0; i < (arrFolderName.length - 1); i++)
                    {
                        strRealFolder += arrFolderName[i] + "/";
                    }
                    strRealFolder = outFile.getPath() + "/" + strRealFolder;
                    File tempDir = new File(strRealFolder);
                    tempDir.mkdirs();
                }

                InputStream in = null;
                FileOutputStream out = null;
                try
                {
                    f.createNewFile();
                    in = zipFile.getInputStream(zipEntry);
                    out = new FileOutputStream(f);
                    int c;
                    byte[] by = new byte[BUFFEREDSIZE];
                    while ((c = in.read(by)) != -1)
                    {
                        out.write(by, 0, c);
                    }
                    out.flush();
                }
                catch (IOException e)
                {
                    throw new YYException("【ZipUtils-unzip】IO异常", e, "zipFilename=" + zipFilename + ",outputDirectory=" + outputDirectory);
                }
                finally
                {
                    IOUtils.closeInputStream(in);
                    IOUtils.closeOutputStream(out);
                }
            }
        }
        if (zipFile != null)
        {
           try
            {
                zipFile.close();
            }
            catch (IOException e)
            {
                throw new YYException("【ZipUtils-unzip】关闭压缩文件异常", e);
            }
        }
    }
}
