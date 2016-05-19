package com.yinyang.yy.superutils.thirdparty;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//import org.apache.tools.zip.ZipEntry; 一直没找到 这个包  gooagoo下也是
//import org.apache.tools.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 * @author <a href="http://www.xdemo.org">xdemo.org</a>
 * 2015年2月28日
 */
/**
 * 压缩文件
 * @author <a href="http://www.xdemo.org">xdemo.org</a>
 */
public class ApacheZip {

	/**
	 * dir路径请使用/,不要使用\\
	 * @param comments zip注释
	 * @param targetZip zip路径
	 * @param dir 要压缩的目录
	 */
	/*public static void zipDir(String comments, String targetZip, String dir) {

		List<File> files = getFiles(dir, new ArrayList<File>());

		byte buffer[] = new byte[40960];

		try {

			BufferedInputStream bis = null;

			FileOutputStream fos = new FileOutputStream(targetZip);

			FileInputStream fis = null;

			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));

			ZipEntry entry = null;

			zos.setEncoding("GBK");
			zos.setComment(comments);
			
			for (File file : files) {
				
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				
				String entryName = file.getAbsolutePath().replace(dir, "");
				entry = new ZipEntry(entryName);
				zos.putNextEntry(entry);
				int count;
				while ((count = bis.read(buffer)) != -1) {
					zos.write(buffer, 0, count);
				}
				bis.close();

			}
			zos.close();
			if(fis!=null)
				fis.close();
			if(fos!=null)
				fos.close();
			if(bis!=null)
				bis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	/**
	 * 
	 * 获取目录下所有文件(包括子文件夹) 递归调用
	 * 
	 * @param realpath
	 * @param files
	 * @return
	 */
	private static List<File> getFiles(String realpath, List<File> files) {

		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				}else{
					files.add(file);
				}
			}
		}
		return files;
	}

	/**
	 * 支持单个或者多个文件压缩到一个压缩包，不支持目录压缩，没有层级关系
	 * 
	 * @param comments
	 *            压缩包右侧的注释
	 * @param targetZip
	 *            压缩包文件地址
	 * @param files
	 *            一个File或者File[]数组
	 */
	/*public static void zip(String comments, String targetZip, File... files) {

		byte buffer[] = new byte[40960];

		try {

			BufferedInputStream bis = null;

			FileOutputStream fos = new FileOutputStream(targetZip);

			FileInputStream fis = null;

			ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));

			ZipEntry entry = null;

			zos.setEncoding("GBK");
			zos.setComment(comments);

			for (File file : files) {
				if (file.isDirectory())
					continue;
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				entry = new ZipEntry(file.getName());
				zos.putNextEntry(entry);

				int count;
				while ((count = bis.read(buffer)) != -1) {
					zos.write(buffer, 0, count);
				}
				bis.close();
			}
			zos.close();
			if(fis!=null)
				fis.close();
			if(fos!=null)
				fos.close();
			if(bis!=null)
				bis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/*public static void main(String[] args) {
		// zip("测测进风口拉萨进风口了","E:\\xxxxxx.zip",new File[]{new
		// File("E:\\x\\image\\"),new
		// File("E:\\新建 Microsoft Office Excel 2007 工作表.xlsx"),new
		// File("D:\\jdbc.properties")});
		zipDir("测试", "E:\\zd.zip", "E:\\zd1\\");
		
	}*/

}
