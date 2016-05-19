package com.yinyang.yy.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.yinyang.yy.common.YYException;


/**
 * 图片处理工具
 * 
 * @author Administrator
 *
 */
public class PictureUtil
{
    /**
     * 剪切图片
     * 
     * @param is
     * @param lastdir
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @throws YYException
     */
    public static byte[] cut(byte[] inBytes, String lastdir, int x, int y, int width, int height) throws YYException
    {
        ImageInputStream iis = null;
        InputStream inputstream = null;
        try
        {
            inputstream = new ByteArrayInputStream(inBytes);

            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(lastdir);
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(inputstream);
            reader.setInput(iis, true);

            ImageReadParam param = reader.getDefaultReadParam();

            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);

            BufferedImage bi = reader.read(0, param);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bi, lastdir, bos);
            return bos.toByteArray();
        }
        catch (IOException e)
        {
            throw new YYException("【PicUtil-cut】IO异常", e, "inBytes.length=" + inBytes.length + ",lastdir=" + lastdir + ",x=" + x + ",y=" + y + ",width=" + width + ",height=" + height);
        }
        finally
        {
            IOCloseUtils.closeInputStream(inputstream);
            IOCloseUtils.closeImageInputStream(iis);
        }

    }
}