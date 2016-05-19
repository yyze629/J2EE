package com.yinyang.yy.gooagoo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 
 * @author zhangmh
 * 
 */
public class ModificationFileUtils
{
    /**
     * 将文件中指定的一行内容替换为其它内容.
     * 
     * @param oldStr 要被替换的一行内容
     * @param replaceStr 替换后的一行内容
     * @param file
     */
    public static void replaceTxtByStr(String oldStr, String replaceStr, File file)
    {
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer buf = null;
        FileOutputStream fos = null;
        OutputStreamWriter outWrite = null;
        BufferedWriter bw = null;
        try
        {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis, "UTF-8");
            br = new BufferedReader(isr);
            buf = new StringBuffer();

            // 保存该行前面的内容
            while ((temp = br.readLine()) != null && !temp.equals(oldStr))
            {
                buf = buf.append(temp);
                buf = buf.append("\n");
            }

            // 将内容插入
            buf = buf.append(replaceStr);

            // 保存该行后面的内容
            while ((temp = br.readLine()) != null)
            {
                buf = buf.append(temp);
                buf = buf.append("\n");
            }

            br.close();
            fos = new FileOutputStream(file);
            outWrite = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(outWrite);
            bw.write(buf.toString().toCharArray());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }
                if (outWrite != null)
                {
                    outWrite.close();
                }
                if (fos != null)
                {
                    fos.close();
                }
                if (br != null)
                {
                    br.close();
                }
                if (isr != null)
                {
                    isr.close();
                }
                if (fis != null)
                {
                    fis.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
