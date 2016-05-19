package com.yinyang.yy.gooagoo.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.yinyang.yy.gooagoo.log.GooagooLog;

/**
 * 文字生成图片 
 * 
 * @Title: DrawImage.java
 * @Package com.yinyang.gooagoo.common
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 上午11:32:15
 * @version V1.0
 */
public class DrawImage
{

    /**
     * 根据提供的文字生成图片 
     * @param file 文字文件
     * @param outPutPath 图片存放路径，含文件名
     * @param encoding 文件字符编码
     * @param fontSize 图片文件大小
     * @throws Exception
     */
    public static void createImage(File file, String outPutPath, int fontSize, String encoding) throws Exception
    {
        if (file == null)
        {
            throw new IllegalArgumentException("DrawImage,but file is null.");
        }

        if (StringUtils.isBlank(outPutPath))
        {
            throw new IllegalArgumentException("DrawImage,but outPutPath is empty.");
        }

        encoding = encoding == null ? "GBK" : encoding;

        byte[] arr = FileUtils.getBytesFromFile(file);
        if (arr == null || arr.length <= 0)
        {
            throw new IllegalArgumentException("DrawImage,but file's content is empty.");
        }

        List<Integer> indexs = new ArrayList<Integer>(); //处理换行符
        for (int i = 0; i < arr.length - 1; i++)
        {
            if (arr[i] == '\r')
            {
                indexs.add(i);
            }
            if (arr[i + 1] == '\n')
            {
                i++;
                indexs.add(i);
            }
        }

        List<byte[]> arrByteList = new ArrayList<byte[]>();//byte[]即为一行的数据
        int length = 0;
        int width = 0;//图片高度
        if (!indexs.isEmpty())
        {
            for (int i = 0; i < indexs.size() - 1; i++)
            {
                byte[] a = new byte[indexs.get(i) - length];
                System.arraycopy(arr, length, a, 0, indexs.get(i) - length);
                arrByteList.add(a);
                if (a.length > width)
                {
                    width = a.length;
                }
                if (indexs.get(i + 1) == indexs.get(i) + 1)
                {
                    i++;
                    length = indexs.get(i) + 1;
                    continue;
                }
                length = indexs.get(i);
            }
        }
        else
        {
            width = arr.length;
            arrByteList.add(arr);
        }
        int height = arrByteList.size();//图片宽度

        try
        {
            // 宽度 高度  
            int wFontSize = (int) (fontSize * 0.5);
            width = width * wFontSize;
            height = (height + 1) * fontSize;
            BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D g = bimage.createGraphics();
            g.setColor(Color.WHITE); // 背景色  
            g.fillRect(0, 0, width, height); // 画一个矩形  
            g.setColor(Color.BLACK); // 字的颜色  
            Font font = new Font(null, Font.PLAIN, fontSize);
            g.setFont(font); // 字体字形字号

            int index = 0;
            for (byte[] sr : arrByteList)
            {
                index++;
                g.drawString(new String(sr, encoding), 0, index * fontSize);
                //g.drawBytes(sr, 0, sr.length, 0, index * fontSize);
            }

            g.dispose();
            ImageIO.write(bimage, "png", new File(outPutPath));

        }
        catch (Exception e)
        {
            String msg = "Create bill image Failed! file is[" + file + "]";
            GooagooLog.info(msg);
            throw new Exception(msg, e);
        }
    }

    /* public static void main(String[] args) throws Exception
     {
         long a = System.currentTimeMillis();
         //createImage(new File("D:/wd/tp/1.txt"), "D:/wd/tp/1.png", null, 12);
         createImage(new File("D:/wd/tp/5.spc"), "D:/wd/tp/5.png", 12, null);
         System.out.println("耗时：" + (System.currentTimeMillis() - a));
     }*/
}
