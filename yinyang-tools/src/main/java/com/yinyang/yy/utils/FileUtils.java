package com.yinyang.yy.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinyang.yy.common.YYException;

/**
 * 有更新 文件处理工具类
 * 
 * @author 作者：yinyang E-mail:
 * @date 创建时间：2015年4月3日 下午5:20:13
 * @version 1.0
 */
public class FileUtils {
	
	private static Log logger = LogFactory.getLog(FileUtils.class);
	
	/**
	 * 判断是linux还是windows
	 */
	/*
	 * static{ //修改linux读取路径问题 if("/".equals(File.separator)){
	 * TIMESTAMP_FILE_PATH="/"+TIMESTAMP_FILE_PATH;
	 * System.getProperty("catalina.home");//D:\apache-tomcat-7.0.35 } }
	 */

	/**
	 * 字节数组转化为文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 * @throws YYException
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) throws YYException {
		AssertUtils.notNull(b, "【FileUtils-getFileFromBytes】传入参数b为NULL");
		AssertUtils.notEmpty(outputFile, "【FileUtils-getFileFromBytes】传入参数outputFile为空");
		mkDir(outputFile, false);
		File ret = null;
		BufferedOutputStream bos = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret);
			bos = new BufferedOutputStream(fstream);
			bos.write(b);
			return ret;
		} catch (IOException e) {
			throw new YYException("【FileUtils-getFileFromBytes】IO异常", e, "b.length=" + b.length + ",outputFile=" + outputFile);
		} finally {
			IOCloseUtils.closeOutputStream(bos);
		}
	}

	/**
	 * 文件转化为字节数组
	 * 
	 * @param file
	 * @return
	 * @throws YYException
	 */
	public static byte[] getBytesFromFile(File file) throws YYException {
		AssertUtils.notNull(file, "【FileUtils-getBytesFromFile】传入参数file为NULL");
		byte[] ret = null;
		FileInputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream(4096);
			byte[] b = new byte[4096];
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
			ret = out.toByteArray();
			return ret;
		} catch (IOException e) {
			throw new YYException("【FileUtils-getBytesFromFile】IO异常", e);
		} finally {
			IOCloseUtils.closeInputStream(in);
			IOCloseUtils.closeOutputStream(out);
		}
	}

	/**
	 * 根据扩展名获取文件名
	 * 
	 * @param exp
	 * @return
	 */
	public static String getFileName(String exp) {
		return UUIDUtils.getUUID() + "." + exp;
	}

	/**
	 * 创建目录
	 * 
	 * @param writerPath
	 *            创建路径
	 * @param isDir
	 *            true-目录 false-文件路径
	 * @throws YYException
	 */
	public static void mkDir(String path, boolean isDir) throws YYException {
		AssertUtils.notEmpty(path, "【FileUtils-mkDir】传入参数path为空");
		path = path.replace("\\", "/");
		File file = new File(path);
		if (isDir) {
			if (!file.exists()) {
				file.mkdirs();
			}
		} else {
			File parent = new File(file.getParent());
			if (!parent.exists()) {
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
	public static void writeTxt(String content, String filePath, String code) throws YYException {
		AssertUtils.notEmpty(content, "【FileUtils-writeTxt】传入参数content为空");
		AssertUtils.notEmpty(filePath, "【FileUtils-writeTxt】传入参数filePath为空");
		AssertUtils.notEmpty(code, "【FileUtils-writeTxt】传入参数code为空");
		mkDir(filePath, false);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), code)));
			writer.write(content);
		} catch (Exception e) {
			throw new YYException("【FileUtils-writeTxt】写入TXT文件异常", e, "content=" + content + ",filePath=" + filePath + ",code=" + code);
		} finally {
			IOCloseUtils.closeWriter(writer);
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
	public static String readTxt(String filePath, String code) throws YYException {
		AssertUtils.notEmpty(filePath, "【FileUtils-readTxt】传入参数filePath为空");
		AssertUtils.notEmpty(code, "【FileUtils-readTxt】传入参数code为空");
		StringBuffer result = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), code));
			String str = "";
			while ((str = bufferedReader.readLine()) != null) {
				result.append(str);
			}
		} catch (Exception e) {
			throw new YYException("【FileUtils-readTxt】读取TXT文件异常", e, "filePath=" + filePath + ",code=" + code);
		} finally {
			IOCloseUtils.closeReader(bufferedReader);
		}
		return result.toString();
	}

	/**
	 * 写文本文件
	 * 
	 * @param content
	 * @param filePath
	 * @param code
	 * @throws YYException
	 */
	public static void writeFile(String content, String filePath, String code) throws YYException {
		AssertUtils.notEmpty(content, "【FileUtils-writeFile】传入参数content为空");
		AssertUtils.notEmpty(filePath, "【FileUtils-writeFile】传入参数filePath为空");
		AssertUtils.notEmpty(code, "【FileUtils-writeFile】传入参数code为空");
		mkDir(filePath, false);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), code)));
			writer.write(content);
		} catch (Exception e) {
			throw new YYException("【FileUtils-writeFile】写入文件异常", e, "content=" + content + ",filePath=" + filePath + ",code=" + code + ",异常信息:[" + e.getMessage() + "]");
		} finally {
			IOCloseUtils.closeWriter(writer);
		}
	}

	/**
	 * 删除 目录
	 * 
	 * @param file
	 *            删除的目录文件
	 */
	public static void deletDir(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deletDir(files[i]);
				} else {
					files[i].delete();
				}
			}
		}

		file.delete();
	}

	/**
	 * 删除目录
	 * 
	 * @param path
	 *            删除的目录
	 * @throws YYException 
	 * @throws Exception
	 */
	public static void deletDir(String path) throws YYException {
		AssertUtils.notEmpty(path, "【FileUtils-deletDir】传入参数path为空");
		
		File file = null;
		try {
			file = new File(path);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deletDir(files[i]);
					} else {
						files[i].delete();
					}
				}
			}

			file.delete();
		} catch (Exception e) {
			// throw new Exception("【FileUtils-deletDir】删除目录异常" + " file=" +
			// file, e);
			logger.debug(String.format("【FileUtils-deletDir】删除[%s]文件目录异常 [%s]", file, e));
		}
	}

	/** 新增方法 */

	/**
	 * 快速读取文件最后一行
	 * 
	 * @param file
	 *            带文件名的全路径
	 * @param charset
	 *            读取文件的编码方式
	 * @return 返回最后一行数据
	 * @throws Exception
	 */
	public static String readLastLine(File file, String charset) throws Exception {
		if (!file.exists() || file.isDirectory() || !file.canRead()) {
			return null;
		}
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			long len = raf.length();
			if (len == 0L) {
				return "";
			} else {
				long pos = len - 1;
				while (pos > 0) {
					pos--;
					raf.seek(pos);
					if (raf.readByte() == '\n') {
						break;
					}
				}
				if (pos == 0) {
					raf.seek(0);
				}
				byte[] bytes = new byte[(int) (len - pos)];
				raf.read(bytes);
				if (charset == null) {
					return new String(bytes);
				} else {
					return new String(bytes, charset);
				}
			}
		} catch (FileNotFoundException e) {
			throw new YYException("【FileUtils-readTxt】读取TXT文件异常", e, "filePath=" + file.getAbsolutePath() + ",charset=" + charset);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (Exception e) {
					throw new YYException("【FileUtils-writeFile】读取文件异常", e, "filePath=" + file.getAbsolutePath() + ",charset=" + charset + ",异常信息:[" + e.getMessage() + "]");
				}
			}
		}// finally
	}

	/**
	 * 文件转化为字符串
	 * 
	 * @param file
	 * @return
	 * @throws YYException 
	 * @throws STException
	 */
	public static List<String> getStringFromFile(File file) throws IOException, YYException {
		AssertUtils.notNull(file, "【FileUtils-getBytesFromFile】传入参数file为NULL");

		List<String> list = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "GBK");
			br = new BufferedReader(isr);

			// 读取一行
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}

			return list;
		} catch (IOException e) {
			throw new IOException("【FileUtils-getBytesFromFile】IO异常", e);
		} finally {
			IOCloseUtils.closeBufferedReader(br);
			IOCloseUtils.closeInputStreamReader(isr);
			IOCloseUtils.closeFileInputStream(fis);
		}
	}

	/**
	 * 读取源文件内容
	 * @param filename String 文件路径
	 * @throws IOException
	 * @return byte[] 文件内容
	 */
	public static byte[] readFile(String filename) throws IOException {

		File file = new File(filename);
		if (filename == null || filename.equals("")) {
			logger.error("文件工具类读取文件出错,文件路径不正确...", new NullPointerException("无效的文件路径"));
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len) {
			logger.error("文件工具类[读取文件异常]...", new IOException("读取文件不正确"));
		}
		bufferedInputStream.close();

		return bytes;
	}

	/**
	 * 将数据写入文件
	 * @param data byte[]
	 * @param filename 文件路径+名称
	 * @throws IOException
	 */
	public static void writeFile(byte[] data, String filename) throws IOException {
		try {
			File file = new File(filename);
			file.getParentFile().mkdirs();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(data);
			bufferedOutputStream.close();
		} catch (Exception e) {
			logger.error("文件工具类[将数据写入文件]异常...", e);
		}
	}

	/**
	 * 从jar文件里读取class
	 * @param filename String
	 * @throws IOException
	 * @return byte[]
	 */
	public byte[] readFileJar(String filename) throws IOException {
		byte[] bytes = null;
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(getClass().getResource(filename).openStream());
			int len = bufferedInputStream.available();
			bytes = new byte[len];
			int r = bufferedInputStream.read(bytes);
			if (len != r) {
				bytes = null;
				throw new IOException("读取文件不正确");
			}
			bufferedInputStream.close();
		} catch (Exception e) {
			logger.error("文件工具类[从jar文件里读取class]异常...", e);
		}
		return bytes;
	}

	/**
	 * 读取网络流
	 * 为了防止中文的问题，在读取过程中没有进行编码转换，而且采取了动态的byte[]的方式获得所有的byte返回
	 * @param bufferedInputStream BufferedInputStream
	 * @throws IOException
	 * @return byte[]
	 */
	public byte[] readUrlStream(BufferedInputStream bufferedInputStream) throws IOException {
		byte[] bytes = new byte[100];
		byte[] bytecount = null;
		int n = 0;
		int ilength = 0;
		try {
			while ((n = bufferedInputStream.read(bytes)) >= 0) {
				if (bytecount != null)
					ilength = bytecount.length;
				byte[] tempbyte = new byte[ilength + n];
				if (bytecount != null) {
					System.arraycopy(bytecount, 0, tempbyte, 0, ilength);
				}

				System.arraycopy(bytes, 0, tempbyte, ilength, n);
				bytecount = tempbyte;

				if (n < bytes.length)
					break;
			}
		} catch (Exception e) {
			logger.error("文件工具类[读取网络流]异常...", e);
		}
		return bytecount;
	}
}
