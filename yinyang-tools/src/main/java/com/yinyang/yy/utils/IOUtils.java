package com.yinyang.yy.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 
 * @Title: IOUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:36:12
 * @version V1.0
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class IOUtils {
	private static Log logger = LogFactory.getLog(IOUtils.class);
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static byte[] converseFile(String path) throws Exception {
		InputStream is = new FileInputStream(path);
		return getBytes(is);
	}

	public static byte[] converseFile(File file) throws Exception {
		InputStream is = new FileInputStream(file);
		return getBytes(is);
	}

	public static byte[] getBytes(InputStream is) throws Exception {
		byte[] data = null;
		Collection chunks = new ArrayList();
		byte[] buffer = new byte[1024 * 1000];
		int read = -1;
		int size = 0;
		while ((read = is.read(buffer)) != -1) {
			if (read > 0) {
				byte[] chunk = new byte[read];
				System.arraycopy(buffer, 0, chunk, 0, read);
				chunks.add(chunk);
				size += chunk.length;
			}
		}
		if (size > 0) {
			ByteArrayOutputStream bos = null;
			try {
				bos = new ByteArrayOutputStream(size);
				for (Iterator itr = chunks.iterator(); itr.hasNext();) {
					byte[] chunk = (byte[]) itr.next();
					bos.write(chunk);
				}
				data = bos.toByteArray();
			} finally {
				if (bos != null) {
					bos.close();
				}
			}
		}
		return data;
	}

	public static String toString(InputStream input, String encoding) throws IOException {
		StringWriter sw = new StringWriter();
		copy(input, sw, encoding);
		return sw.toString();
	}

	public static void copy(InputStream is, Writer w, String encodeing) {
		InputStreamReader isr = null;
		try {
			if (encodeing == null || encodeing.trim().length() == 0) {
				isr = new InputStreamReader(is);
			} else {
				isr = new InputStreamReader(is, encodeing);
			}
			copy(isr, w);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void copy(Reader reader, Writer w) throws IOException {
		char[] temp = new char[DEFAULT_BUFFER_SIZE];
		int index = -1;
		while ((index = reader.read(temp)) != -1) {
			w.write(temp, 0, index);
		}
	}

	public static void renameFile(String oriFileName, String toFileName) {
		File file = new File(oriFileName);
		if (file.exists() && file.canRead()) {
			File toFile = new File(toFileName);
			if (toFile.exists()) {
				toFile.delete();
			}
			file.renameTo(new File(toFileName));
		}
	}
}
