package com.yinyang.yy.utils;

/**
 * 图片处理
 * 
 * @Title: ImageDeal.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:27:55
 * @version V1.0
 */
public class ImageDeal {

	/** 图片地址类型:错误的地址 */
	private final static String TYPE_ERROR = "0";

	/** 图片地址类型: 如：upload/20087/72/ff80808114121c4001141226d2fd0b03/productimg1216796629778.jpg */
	private final static String TYPE_UPLOAD = "1";

	/** 图片地址类型: 如：albu_508495265_00 */
	private final static String TYPE_ALBU = "2";

	/** 图片地址类型: 如：f2/albu/g1/M00/06/13/rBEbRlN0liUIAAAAAAL-MsMK6rkAABSgABYRQIAAv5K776.jpg */
	private final static String TYPE_FDFS_ALBU = "3";

	private final static String URL_NOPHOTO_IMAGE = "images/no_photo.gif";

	private final static String NEW_URL_SEO_SUFFIX = "s";

	/**
	 * 小图地址(100x100)
	 * @param path
	 * @return
	 */
	public static String getThumbnailPath(String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "100x100/" + trimPath;
		} else if (TYPE_ALBU.equals(type)) {
			return trimPath + "/1.thumb.jpg";
		} else if (TYPE_UPLOAD.equals(type)) {
			int extensionIndex = trimPath.lastIndexOf(".");
			return trimPath.substring(0, extensionIndex).concat(".thumb.gif");
		} else {
			return trimPath;
		}
	}


	/**
	 * 大图(0x0)图片地址
	 * @param path
	 * @return
	 */
	public static String get0x0(String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "0x0/" + trimPath;
		} else if (TYPE_ALBU.equals(type)) {
			return trimPath + "/1.0x0.jpg";
		} else if (TYPE_UPLOAD.equals(type)) {
			return trimPath;
		} else {
			return trimPath;
		}
	}


	/**
	 * 260x260图片地址
	 * @param path
	 * @return
	 */
	public static String get260x260(String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "260x260/" + trimPath;
		} else if (TYPE_ALBU.equals(type)) {
			return trimPath + "/1.260x260.jpg";
		} else if (TYPE_UPLOAD.equals(type)) {
			int extensionIndex = trimPath.lastIndexOf(".");
			return trimPath.substring(0, extensionIndex).concat(".260x260.jpg");
		} else {
			return trimPath;
		}
	}
	/**
	 * 200 x 200 图片地址
	 * @param path
	 * @return
	 */
	public static String get200x200(String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "200x200/" + trimPath;
		} else if (TYPE_ALBU.equals(type)) {
			return trimPath + "/1.200x200.jpg";
		} else if (TYPE_UPLOAD.equals(type)) {
			int extensionIndex = trimPath.lastIndexOf(".");
			return trimPath.substring(0, extensionIndex).concat(".200x200.jpg");
		} else {
			return trimPath;
		}
	}

	/**
	 * 获取小图(100x100 的seo地址, upload开头的没有seo地址)
	 * @param path
	 * @return
	 */
	public static String getSeoThumbnailPath(String productname, String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "100x100" + NEW_URL_SEO_SUFFIX + "/" + getSeoPicUrl(trimPath) + "/" + productname + ".jpg";
		} else if (TYPE_ALBU.equals(type)) {
			return path + "-" + "1.thumb/" + productname + ".jpg";
		} else {
			return trimPath;
		}
	}


	/**
	 * 获取小图(0x0 的seo地址, upload开头的没有seo地址)
	 * @param path
	 * @return
	 */
	public static String getSeo0x0(String productname, String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "0x0" + NEW_URL_SEO_SUFFIX + "/" + getSeoPicUrl(trimPath) + "/" + productname + ".jpg";
		} else if (TYPE_ALBU.equals(type)) {
			return path + "-" + "1.0x0/" + productname + ".jpg";
		} else {
			return trimPath;
		}
	}



	/**
	 * 获取小图(0x0 的seo地址, upload开头的没有seo地址)
	 * @param path
	 * @return
	 */
	public static String getSeo260x260(String productname, String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "260x260" + NEW_URL_SEO_SUFFIX + "/"+ getSeoPicUrl(trimPath) + "/" + productname + ".jpg";
		} else if (TYPE_ALBU.equals(type)) {
			return path + "-" + "1.260x260/" + productname + ".jpg";
		} else {
			return trimPath;
		}
	}


	/**
	 * 获取小图(0x0 的seo地址, upload开头的没有seo地址)
	 * @param path
	 * @return
	 */
	public static String getSeo200x200(String productname, String path) {
		if (path == null || "".equals(path.trim())) {
			return URL_NOPHOTO_IMAGE;
		}
		String trimPath = path.trim();
		String type = judgeType(trimPath);
		if (TYPE_FDFS_ALBU.equals(type)) {
			return "200x200" + NEW_URL_SEO_SUFFIX + "/"+getSeoPicUrl(trimPath) + "/" + productname + ".jpg";
		} else if (TYPE_ALBU.equals(type)) {
			return path + "-" + "1.200x200/" + productname + ".jpg";
		} else {
			return trimPath;
		}
	}

	/**
	 * seo地址
	 * 把前6个"/"替换成 "-"
	 * @param path
	 * @return
	 */
	private static String getSeoPicUrl(String path) {
		if (path == null) {
			return path;
		}
		return path.replaceFirst("/", "-").replaceFirst("/", "-").replaceFirst("/", "-")
		.replaceFirst("/", "-").replaceFirst("/", "-").replaceFirst("/", "-");
	}



	private static String judgeType(String path) {
		if (path == null || "".equals(path.trim())) {
			return TYPE_ERROR;
		}
		path = path.trim();
		if (path.startsWith("f2/albu/")) {
			return TYPE_FDFS_ALBU;
		}
		if (path.startsWith("albu_")) {
			return TYPE_ALBU;
		}
		if (path.startsWith("upload")) {
			return TYPE_UPLOAD;
		}

		return TYPE_ERROR;

	}

	public static void main(String[] args) {
//		String uploadUrl = "upload/20087/72/ff80808114121c4001141226d2fd0b03/productimg1216796629778.jpg";
//		System.out.println("http://www.dhresource.com/" + getThumbnailPath(uploadUrl));
//		System.out.println("http://www.dhresource.com/" + get0x0(uploadUrl));
//		System.out.println("http://www.dhresource.com/" + get260x260(uploadUrl));
//		System.out.println("http://www.dhresource.com/" + get200x200(uploadUrl));
//		System.out.println("http://www.dhresource.com/" + getSeoThumbnailPath("proudctname", uploadUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo0x0("proudctname", uploadUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo200x200("proudctname", uploadUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo260x260("proudctname", uploadUrl));
//
//		String albuUrl = "albu_508495265_00";
//		System.out.println("http://www.dhresource.com/" + getThumbnailPath(albuUrl));
//		System.out.println("http://www.dhresource.com/" + get0x0(albuUrl));
//		System.out.println("http://www.dhresource.com/" + get260x260(albuUrl));
//		System.out.println("http://www.dhresource.com/" + get200x200(albuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeoThumbnailPath("proudctname", albuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo0x0("proudctname", albuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo200x200("proudctname", albuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo260x260("proudctname", albuUrl));
//
//		String f2AlbuUrl = "f2/albu/g1/M00/00/00/wKjfHVQQFBeIOc0fAASd8U8QSpcAAAAAQBGAM4ABJ4J310.jpg";
//		System.out.println("http://www.dhresource.com/" + getThumbnailPath(f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + get0x0(f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + get260x260(f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + get200x200(f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeoThumbnailPath("proudctname", f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo0x0("proudctname", f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo200x200("proudctname", f2AlbuUrl));
//		System.out.println("http://www.dhresource.com/" + getSeo260x260("proudctname", f2AlbuUrl));


		System.out.println(ImageUtil.get200x200ImgPath("/upload/php/ad2/1432617749.jpg"));
	}
}
