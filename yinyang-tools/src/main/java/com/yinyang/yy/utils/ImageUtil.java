package com.yinyang.yy.utils;

/**
 * 
 * 图片地址处理类
 * 100*100，金橱窗、详情浮层里的小图；
 * 200*200，产品列表图（list）、黄金展位、最近浏览记录、交叉营销图；
 * 260*260，产品列表图（gallery）、详情浮层里的大图。
 * @Title: ImageUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:28:14
 * @version V1.0
 */
public class ImageUtil {
	/**
	 * gate默认图片域名
	 */
	public static final String IMAGE_DOMAIN_GATE = "www.dhresource.com";
	/**
	 * @param imagePath
	 * @param domain 扩展图片域名
	 * @return
	 */
	public static String get260x260ImgPath(String imagePath, String domain){
   	  imagePath = ImageDeal.get260x260(imagePath);
   	  imagePath =  "http://" + domain + "/" +imagePath;
   	  return imagePath;
	}
	/**
	 * @param imagePath
	 * @param domain 扩展图片域名
	 * @return
	 */
	public static String get200x200ImgPath(String imagePath, String domain){
       String sThumbnailPath = ImageDeal.get200x200( imagePath ); //取得图片对应的小样路径
       sThumbnailPath = "http://" + domain + "/" + sThumbnailPath;
       return sThumbnailPath;
	}
	/**
	 *
	 * @param imagePath
	 * @param domain 扩展图片域名
	 * @return
	 */
	public static String get100x100ImgPath(String imagePath, String domain){
 	  imagePath = ImageDeal.getThumbnailPath(imagePath);
 	  imagePath =  "http://" + domain + "/" +imagePath;
 	  return imagePath;
	}
	/**
	 * gate260*260图
	 * @param imagePath
	 * @return
	 */
	public static String get260x260ImgPath(String imagePath){
		return get260x260ImgPath(imagePath,IMAGE_DOMAIN_GATE);
	}
	/**
	 * gate200*200图
	 * @param imagePath
	 * @return
	 */
	public static String get200x200ImgPath(String imagePath){
		return get200x200ImgPath(imagePath, IMAGE_DOMAIN_GATE);

	}
	/**
	 * gate100*100图
	 * @param imagePath
	 * @return
	 */
	public static String get100x100ImgPath(String imagePath){
		return get100x100ImgPath(imagePath, IMAGE_DOMAIN_GATE);

	}
	/**
	 * gate seo260*260图
	 * @param imagePath
	 * @return
	 */
	public static String getSeo260x260ImgPath(String productname, String imagePath){
		return getSeo260x260ImgPath(productname, imagePath, IMAGE_DOMAIN_GATE);
	}
	/**
	 * gate seo260*260图
	 * @param imagePath
	 * @return
	 */
	private static String getSeo260x260ImgPath(String productname, String imagePath, String domain){
		imagePath = ImageDeal.getSeo260x260(productname, imagePath);
		imagePath =  "http://" + domain + "/" +imagePath;
		return imagePath;
	}
	/**
	 * gate seo260*260图
	 * @param imagePath
	 * @return
	 */
	public static String getSeo200x200ImgPath(String productname, String imagePath){
		return getSeo200x200ImgPath(productname, imagePath, IMAGE_DOMAIN_GATE);
	}
	/**
	 * gate seo260*260图
	 * @param imagePath
	 * @return
	 */
	private static String getSeo200x200ImgPath(String productname, String imagePath, String domain){
		imagePath = ImageDeal.getSeo200x200(productname, imagePath);
		imagePath =  "http://" + domain + "/" +imagePath;
		return imagePath;
	}
}
