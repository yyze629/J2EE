package com.yinyang.yy.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.yinyang.yy.common.YYException;

/**
 * 二维码工具类
 * 
 * @Title: QRCodeUtils.java
 * @Package com.yinyang.gooagoo.common
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 上午11:13:19
 * @version V1.0
 */
public class QRCodeUtils
{
    private final static int SIZE = 160;
    private final static int REAL_SIZE = 128;
    
    /**
     * 生成二维码字节流
     * 
     * @param content
     * @return
     * @throws YYException
     */
    public static byte[] getImageByte(String content) throws YYException
    {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try
        {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, REAL_SIZE, REAL_SIZE);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
            ImageIO.write(bufferedImage, "bmp", byteArray);
            return byteArray.toByteArray();
        }
        catch (Exception e)
        {
            throw new YYException("【QRCodeUtils-getImageByte】生成二维码异常", e, "content=" + content);
        }
    }

    /**
     * 生成二维码字节流
     * 
     * @param content
     * @return
     * @throws YYException
     */
    public static byte[] getImageByteWithCut(String content) throws YYException
    {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try
        {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, SIZE, SIZE);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
            ImageIO.write(bufferedImage, "bmp", byteArray);
            return PictureUtil.cut(byteArray.toByteArray(), "bmp", (SIZE - REAL_SIZE) / 2, (SIZE - REAL_SIZE) / 2, REAL_SIZE, REAL_SIZE);
        }
        catch (Exception e)
        {
            throw new YYException("【QRCodeUtils-getImageByte】生成二维码异常", e, "content=" + content);
        }
    }

    /**
     * 生成二维码文件
     * 
     * @param content
     * @param path
     * @return
     * @throws YYException
     */
    public static byte[] getImageFile(String content, String path) throws YYException
    {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try
        {
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, SIZE, SIZE);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(matrix);
            ImageIO.write(bufferedImage, "bmp", new File(path));
        }
        catch (Exception e)
        {
            throw new YYException("【QRCodeUtils-getImageFile】生成二维码异常", e, "content=" + content + ",path" + path);
        }
        return byteArray.toByteArray();
    }
    
    public static void main(String[] arg) throws YYException
    {
        FileUtils.getFileFromBytes(getImageByte("http://caipiao.taobao.com/?spm=1.1000386.221827.9.A9jHID&ad_id=&am_id=&cm_id=&pm_id="), "d:/444.bmp");
    }

}
