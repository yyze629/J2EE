package com.yinyang.yy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * 文件上传/下载工具类
 * @author 作者:尹洋 E-mail:vteiud@163.com
 * @date 创建时间：2015年8月28日 下午5:36:06
 * @version 1.0
 */
public class FileUpOrDownLoadUtils {
	
	/** 设置文件处理编码方式 */
	private static final String CODE = "UTF-8";
	/** 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是10MB */
	private static final long SINGLE_FILE_MAXSIZE = 1024 * 1024 *100;
	/** 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为50MB */
	private static final long ALL_FILE_MAXSIZE = 1024 * 1024 * 500;
	/** 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB */
	private static final int CACHE_SIZE = 1024 * 100;
	
	private static String userDir = System.getProperty("user.dir");
	private static Logger logger = Logger.getLogger(FileUpOrDownLoadUtils.class);
	
	/****
	 * 上传的时候 写入文件  用conifg资源文件配置的路径/usr/local/upload/是没有问题的
	 * 但是此处 获取文件的时候 会  报错   '系统找不到指定的文件。'  因为程序没有在window系统 加上   D 或 E 的这种盘符
	 * ****/
	static{
		logger.debug("盘符截取之前:"+userDir);
		/** 根据linux系统或window系统自动判断 */
		userDir = userDir.substring(0, userDir.indexOf(File.separator));
		logger.debug("盘符截取之后:"+userDir);
	}
	
	/**
	 * 上传文件
	 * @param request 请求对象
	 * @param uploadPath 文件保存路径 eg: .../entry/template/
	 * @return message 上传结果
	 * @throws Exception
	 */
	public static Map<String,Object> uploadFilePost(HttpServletRequest request, String uploadPath) throws Exception {
		logger.debug("[文件上传/下载工具类]上传文件uploadFilePost方法~");
		Map<String, Object> map = null;
		/** 上传时生成的临时文件保存目录 */
		File tmpFile = new File(uploadPath+"temp");
		if (!tmpFile.exists()) {
			/** 创建临时目录 */
			tmpFile.mkdir();
		}
		
		/** 消息提示 */
		String message = "";
		try {
			map = new HashMap<String,Object>();
			/** 使用Apache文件上传组件处理文件上传步骤：
			 1、创建一个DiskFileItemFactory工厂 */
			DiskFileItemFactory factory = new DiskFileItemFactory();
			/** 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。*/
			factory.setSizeThreshold(CACHE_SIZE);
			/** 设置上传时生成的临时文件的保存目录 */
			factory.setRepository(tmpFile);
			/** 2、创建一个文件上传解析器 */
			ServletFileUpload upload = new ServletFileUpload(factory);
			/** 监听文件上传进度 */
			upload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long pBytesRead, long pContentLength, int arg2) {
					/**
					 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
					 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
					 */
					//logger.debug("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
				}
			});
			/** 解决上传文件名的中文乱码 */
			upload.setHeaderEncoding(CODE);
			/** 3、判断提交上来的数据是否是上传表单的数据 */
			/**
			 * 混合模式失效 
			 * 解决办法：ajax里面也需要添加参数
			 * url : url,
			 * enctype: 'multipart/form-data',
			 */
			if (!ServletFileUpload.isMultipartContent(request)) {
				/** 按照传统方式获取数据 */
				message = "文件上传失败,请按照传统方式获取数据!";
				map.put("message", message);
			}
			
			upload.setFileSizeMax(SINGLE_FILE_MAXSIZE);
			upload.setSizeMax(ALL_FILE_MAXSIZE);
			/** 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项 */
			List<FileItem> list = upload.parseRequest(request);//一直报错，后面发现是引包异常(import org.apache.tomcat.util.http.fileupload...;)
			for (FileItem item : list) {
				/** 如果fileitem中封装的是普通输入项的数据 */
				if (item.isFormField()) {
					String name = item.getFieldName();
					/** 解决普通输入项的数据的中文乱码问题 */
					String value = item.getString(CODE);
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					map.put(name, value);//保存混合表单的其他非文件信息,如文件名,文件说明
				} else {
					/** 如果fileitem中封装的是上传文件   得到上传的文件名称，*/
					String filename = item.getName();
					logger.debug("上传文件名["+filename+"]!");
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					/** 
					 * 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					 * c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					 * 处理获取到的上传文件的文件名的路径部分，只保留文件名部分 
					 * */
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					/** 得到上传文件的扩展名 */
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					/** 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法 */
					logger.debug("上传的文件的扩展名是：" + fileExtName);
					/** 获取item中的上传文件的输入流 */
					InputStream in = item.getInputStream();
					/** 得到文件保存的名称 */
					String saveFilename = makeFileNameByDate(filename);
					/** 得到文件的保存目录  Hash算法打乱  先不用*/
					//String realSavePath = makePath(saveFilename, UPLOAD_PATH);
					/** 创建一个文件输出流  File.separator*/
					FileOutputStream out = new FileOutputStream(uploadPath + saveFilename);
					logger.info("[文件上传/下载工具类]上传文件,文件保存路径:["+uploadPath + saveFilename+"]");
					
					/** 创建一个缓冲区 */
					byte buffer[] = new byte[1024];
					/**判断输入流中的数据是否已经读完的标识 */
					int len = 0;
					/** 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据 */
					while ((len = in.read(buffer)) > 0) {
						/** 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中 */
						out.write(buffer, 0, len);
					}
					/** 关闭输入流 */
					in.close();
					/** 关闭输出流 */
					out.close();
					/** 删除处理文件上传时生成的临时文件 */
					//item.delete();
					message = "文件上传成功！";
					map.put("message", message);
					map.put("path", uploadPath + saveFilename);
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			message = "文件上传失败,单个文件超出最大值！！！";
			logger.error("[文件上传/下载工具类]上传文件,"+message);
			map.put("message", message);
		} catch (FileUploadBase.SizeLimitExceededException e) {
			message = "文件上传失败,上传文件的总的大小超出限制的最大值！！！";
			logger.error("[文件上传/下载工具类]上传文件,"+message);
			map.put("message", message);
		} catch (FileUploadBase.InvalidContentTypeException e) {
			message = "上传文件流成功,但是表单流异常,不是multipartstream文件流,eader is null！！！";
			logger.error("[文件上传/下载工具类]上传文件,"+message);
			map.put("message", message);
		} catch (Exception e) {
			message = "文件上传失败！";
			logger.error("[文件上传/下载工具类]上传文件异常,"+message, e);
			map.put("message", message);
		}
		logger.info("[文件上传/下载工具类]上传文件返回map数据:"+JsonGsonUtils.toJson(map));
		
		return map;
	}
	
	/**
	 * 列出某路径下的文件来提供下载
	 * @param request 请求对象
	 * @return request  接收对象后直接/或加工返回页面  key:fileNameMap <pre>request.setAttribute("fileNameMap", fileNameMap);</pre><pre>[request.getRequestDispatcher("/listfile.jsp").forward(request, response);]</pre>
	 * @throws Exception
	 */
	public static HttpServletRequest listFilesGet(HttpServletRequest request, String path) throws Exception {
		logger.debug("[文件上传/下载工具类]列出某路径下的文件来提供下载listFilesGet方法~");
		
		/** 从某路径下 获取并存储要下载的文件名 */
		Map<String, String> fileNameMap = new HashMap<String, String>();
		
		/** 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中 */
		listfile(new File(path), fileNameMap);//File既可以代表一个文件也可以代表一个目录
		/** 将Map集合发送到listfile.jsp页面进行显示 */
		request.setAttribute("fileNameMap", fileNameMap);
		logger.debug("[文件上传/下载工具类]列出某路径下的文件来提供下载,map数据为:"+JsonGsonUtils.toJson(fileNameMap));
		return request;
	}
	
	/**
	 * 下载文件
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param fileKey 获取文件的key "filename"
	 * @param path 待下载的文件保存路径  (不带D\E等window系统的盘符)
	 * @throws Exception
	 */
	public static void downloadFileGet(HttpServletRequest request, HttpServletResponse response, String fileKey, String path) throws Exception {
		logger.debug("[文件上传/下载工具类]下载文件downloadFileGet方法~");
		/** 得到要下载的文件名 */
		String fileName = request.getParameter(fileKey); // 23239283-92489-阿凡达.avi
		fileName = new String(fileName.getBytes(CODE), CODE);
		logger.info("[文件上传/下载工具类]下载文件,文件名:"+fileName);
		
		/** 得到要下载的文件 */
		String realPath = userDir + path + fileName;
		File file = new File(realPath);
		logger.info("[文件上传/下载工具类]下载文件,即将下载文件路径:"+ realPath);
		
		/** 如果文件不存在 */
		if (!file.exists()) {
			request.setAttribute("message", "您要下载的资源已被删除！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		/** 处理文件名 */
		String realname = fileName.substring(fileName.indexOf("_") + 1);
		/** 设置响应头，控制浏览器下载该文件 */
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, CODE));
		/** 读取要下载的文件，保存到文件输入流 */
		FileInputStream in = new FileInputStream(realPath);
		/** 创建输出流 */
		/** 第一种  直接下载   浏览器提示保存路径**/
		OutputStream out = response.getOutputStream();
		/** 第二种  设置下载默认保存路径 但是同时浏览器也会提醒保存路径 servlet方法是只进入一次的，但是文件保存了2份 **/
		//FileOutputStream out = new FileOutputStream(DOWNLOAD_PATH+fileName);
		/** 创建缓冲区 */
		byte buffer[] = new byte[1024];
		int len = 0;
		/** 循环将输入流中的内容读取到缓冲区当中 */
		while ((len = in.read(buffer)) > 0) {
			/** 输出缓冲区的内容到浏览器，实现文件下载 */
			out.write(buffer, 0, len);
		}
		/** 关闭文件输入流 */
		in.close();
		/** 关闭输出流 */
		out.close();
	}
	
	/**
	 * 通过日期时间构造唯一文件名
	 * @Method: makeFileNameByDate
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:尹洋
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	public static String makeFileNameByDate(String filename) { 
		/** 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名 */
		String timeName = TimeUtils.getCurrentDateTime("yyyyMMddHHmmss");
		String name = filename.substring(0, filename.lastIndexOf("."));
		String extensionName = filename.substring(filename.lastIndexOf("."),filename.length());//带.了
		
		return name + "_" + timeName + extensionName;
	}
	
	/**
	 * 通过UUID构造唯一文件名
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:尹洋
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	public static String makeFileName(String filename) { 
		/** 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名 */
		String name = filename.substring(0, filename.lastIndexOf("."));
		String extensionName = filename.substring(filename.lastIndexOf("."),filename.length());//带.了
		
		return name + "_" + UUIDUtils.getRandomUUID()+ extensionName;
	}
	
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @Method: makePath
	 * @Description:
	 * @Anthor:尹洋
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */
	public static String makePath(String filename, String savePath) {
		/** 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址 */
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		/** 构造新的保存目录 */
		String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
															// upload\3\5
		/** File既可以代表文件也可以代表目录 */
		File file = new File(dir);
		/** 如果目录不存在 */
		if (!file.exists()) {
			/** 创建目录 */
			file.mkdirs();
		}
		return dir;
	}
	
	/**
	 * 获取文件列表
	 * @Method: listfile
	 * @Description: 递归遍历指定目录下的所有文件
	 * @Anthor:尹洋
	 * @param file 即代表一个文件，也代表一个文件目录
	 * @param map 存储文件名的Map集合
	 */
	public static void listfile(File file, Map<String, String> map) {
		/** 如果file代表的不是一个文件，而是一个目录 */
		if (!file.isFile()) {
			/** 列出该目录下的所有文件和目录 */
			File files[] = file.listFiles();
			/** 遍历files[]数组 */
			for (File f : files) {
				/** 递归 */
				listfile(f, map);
			}
		} else {
			/**
			 * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			 * file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：
			 * 9349249849-88343-8344_阿_凡_达.avi
			 * 那么file.getName().substring(file.getName().indexOf("_")+1)
			 * 处理之后就可以得到阿_凡_达.avi部分
			 */
			String realName = file.getName().substring(file.getName().indexOf("_") + 1);
			/** file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复 */
			map.put(file.getName(), realName);
		}
	}
}
