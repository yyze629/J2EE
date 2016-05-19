package com.yinyang.sellerpay.web.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dhgate.common.util.StringUtil;

public class ProductUtil {
    private static final Logger log = Logger.getLogger(ProductUtil.class);
    
    private final static String regEx="[^\\w%\\(\\)\\^\\|\\+-]+"; //去非数字字母
    private final static String DELIMITER = "-"; 
    private final static int KEYWORD_LENGTH_IN_URL = 40;  //url总长度控制在80个字符左右
    
    private final static Pattern AVAL_CH = Pattern.compile("\\w");
    public final static String TITLE_FILTER_CONFIG_PATH = "/Product_Title_Filter.xml";
    public static List<String> TITLE_FILTER_REG = null;

    public static String MD52STR(byte[] md) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
        
    public static String Md5InputString(String value) {
        String md5Str = null;
        if (value == null)
            return md5Str;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes());
            byte[] dig = md5.digest();
            md5Str = MD52STR(dig);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } 
        return md5Str;
    }

    /**
     * 	
     * @param productName
     * @return
     */
    public static String genProductNameID(String productName) {
        if (productName == null || productName.length() == 0)
            return "";
        String nameFiltered = dochar(productName);
//        byte[] chs = nameFiltered.getBytes();
//        Arrays.sort(chs);
//        String str = new String(chs);
        return Md5InputString(nameFiltered);
    }
    
    
    /**
     * 	
     * @param productName
     * @return
     */
    public static String genProductNameIDNEW(String productName) {
        if (productName == null || productName.length() == 0)
            return "";
        String nameFiltered = docharnew(productName);
//        byte[] chs = nameFiltered.getBytes();
//        Arrays.sort(chs);
//        String str = new String(chs);
        return Md5InputString(nameFiltered);
    }
    
    /**
     *  1.去掉产品中的特殊字符
		2.对产品名称进行分词(用特殊字符分词)
		3.去掉分词中1个字符长度的char
		4.排序（滤重,重复的单词过滤成1个）
		5.还原字串，通过字串+|+字串的方式或者+空格的方式
		6.md5（还原字串） 得到namemd5
     * @param c
     * @return
     */
    private static String  dochar(String c){
    	c = c.toLowerCase();
    	StringBuffer sBuffer = new StringBuffer();
    	TreeMap<String, String> map = new TreeMap<String, String>();
    	String string [] = c.split("[^0-9a-z-]");
    	//String string [] = c.split("[^a-zA-Z\\d\\.\\u4e00-\\u9fa5]+");
    	for (int i = 0; i < string.length; i++) {
			String string2 = string[i];
			if (string2.length()>1) {
		        Object obj = map.get(string2);
		        if (obj == null) {
		        	map.put(string2, string2);
				}
			}
		}
    	int count = 0;
    	Iterator it =  map.entrySet().iterator();
        while (it.hasNext()) { 
        	Map.Entry  m = (Map.Entry)it.next(); 
        	String k = (String)m.getKey();
        	if (count == 0) {
        		sBuffer.append(k);
			}else {
				sBuffer.append("_");
				sBuffer.append(k);
			}
        	count ++;
        }
    	return sBuffer.toString();
    }
    
    public static String filterAdvb(String c){
    	String ret = c.toLowerCase();
    	//获取缓存中的数据
//		String CategoryKey = "Product";
//		String ObjectTypeKey = "Title";
//		String urlhtmlKey = "filterAdvb";
//		String filterxml =  (String)CacheManagerToHtml.getData(CategoryKey, ObjectTypeKey, urlhtmlKey);
    	if(TITLE_FILTER_REG == null){
	    	SAXReader reader = new SAXReader();
	    	Document doc_fliter = null;
	    	List title_Filter_List = null;
			try {
				doc_fliter = reader.read(ProductUtil.class.getResourceAsStream(TITLE_FILTER_CONFIG_PATH));
				title_Filter_List = doc_fliter.selectNodes("//ProductTitleFilter//Item");	 
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				title_Filter_List = null;
			}
			
			if(title_Filter_List != null && title_Filter_List.size() >0){
	    		int nLength = title_Filter_List.size();
	    		if(TITLE_FILTER_REG == null){
                	TITLE_FILTER_REG = new ArrayList<String>();
                }
	    		for(int i=0; i<nLength; i++) {
	                Element itemNode = (Element)title_Filter_List.get(i);
	                String filters = itemNode.element("FilterList").getText();
	                String reg = itemNode.element("RegEx").getText();
	                String regVal = itemNode.element("RegValue").getText();
	                
	                String[] arrFilter = filters.split(",");
	                if(arrFilter != null && arrFilter.length >0){		                
		                for(int j=0; j<arrFilter.length; j++){
		                	String sFilter = arrFilter[j].toLowerCase().trim();
		                	if( !"".equals(sFilter)){
			                	if(regVal != ""){
			                		sFilter = reg.replaceAll(regVal, sFilter);
			                	}
			                	TITLE_FILTER_REG.add(sFilter);
		                	}
		                }
	                }
	            }
	    	}   	
    	}
    	if(TITLE_FILTER_REG != null && TITLE_FILTER_REG.size() >0){
    		int nLength = TITLE_FILTER_REG.size();
    		for(int i=0; i<nLength; i++) {
                String reg_filter = (String)TITLE_FILTER_REG.get(i);
                Pattern p = Pattern.compile(reg_filter, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(ret);
                ret = m.replaceAll("");
            }
    	}
    	
    	return ret;
    }
    
    private static String  docharnew(String c){
    	c = c.toLowerCase();
    	StringBuffer sBuffer = new StringBuffer();
    	TreeMap<String, String> map = new TreeMap<String, String>();
    	String string [] = c.split("[^0-9a-z-]");
    	
    	ArrayList<String> words10 = new ArrayList<String>();
    	int j=0;
    	for (int i = 0; i < string.length; i++) {
			String string2 = string[i];
			
			if (string2.length()>1) {
				words10.add(string2);
				j = j+1;
				if (j>7) break; //只取前7个词
			}				
		}
    	
    	for (String s : words10){
    		map.put(s, s);
    	}
    	
    	int count = 0;
    	
    	//for (Object o : map.keySet()) { 
    	//	map.get(o); 
    	//}

    	Iterator it =  map.entrySet().iterator();
        while (it.hasNext()) { 
        	Map.Entry  m = (Map.Entry)it.next(); 
        	String k = (String)m.getKey();
        	if (count == 0) {
        		sBuffer.append(k);
			}else {
				sBuffer.append("_");
				sBuffer.append(k);
			}
        	count ++;
        }
    	return sBuffer.toString();
    }
    
    
    public static String filterNameItem(String item) {
        if (item == null)
            return "";
        StringBuffer ret = new StringBuffer();
        byte[] chs = item.getBytes();
        for (int i=0; i<chs.length; i++) {
            byte ch = chs[i];
            if (ch <= (byte)'9' && ch >= (byte)'0') {
                ret.append((char)ch);
            } else if (ch <= (byte)'z' && ch >= (byte)'a') {
                ret.append((char)ch);
            } else if (ch <= (byte)'Z' && ch >= (byte)'A') {
                ret.append((char)ch);
            } 
        }
        return ret.toString();
    }
    

	/**
	 * 获得Collection的个数，当为null返回值为0
	 * @param c (Collection)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static int getCollSize(Collection c) {
		return c == null ? 0 : c.size();
	}
	

	/**
	 * 产品属性个数是否超出类目属性个数。
	 * 
	 * @param proAttrCount
	 *            产品属性个数
	 * @param cateAttrCount
	 *            类目属性个数
	 * @return true 超出, false:未超出
	 */
	public static boolean isOutLimitProAttrCount(int proAttrCount, int cateAttrCount) {

		boolean bOutLimit = false;
		if (cateAttrCount == 0) {
			if (proAttrCount > 0) {
				bOutLimit = true;
			}
		} else {
			if (proAttrCount > cateAttrCount + 1) {
				// 当类目有属性时，产品的属性还可以有自增加属性
				bOutLimit = true;
			}
		}
		
	   return bOutLimit;
	}
    
    public static void main(String arg[]) {
        //String productName1 = "fds \\afa_ 12*12 (343) 222 你好yyy x和xx";
        //String productName2 = "fds \\afa_ (12*12) (343) **  222 你好yyy + x加上xx *";
        //String productName1 = "The exquisite box holds Your Baby Can Read 5DVD 50 hard boxes install the card + the book";
        //String productName2 = "Big and pure silver coin*three years of **** Carry on the back a dragon 1 dollar";
        String productName1 = "test is test";
        String productName2 = "(*)test  %&*^*&(&() is *)(* test  ()*)*&(*";
        String string = "1000 pcs electronic TXT book &quot;manifesto of the communist party&quot;";
        
        
//        String reg = "^\\s*[A-Za-z]+(\\w+|_|-)*\\s*$";
// //       String reg = "^\\s*[A-Za-z]+(\\w+|_)*\\s*$";
//		Pattern pattern = Pattern.compile(reg); 
//		if(!pattern.matcher(loginname).matches()){
//			response.sendRedirect("/erroraction.do?errmessagekey=error.seller.loginname");
//            return null;
//        }
//		
		
//		String string2 [] = string.split("[^a-zA-Z\\d\\.\\u4e00-\\u9fa5]+");
//		for (int i = 0; i < string2.length; i++) {
//			String s = string2[i];
//			System.out.println(s);
//			
//		}
        
        //System.out.println("str="+ProductUtil.dochat(string));
		System.out.println("md5="+ProductUtil.genProductNameID(string));
        
//        System.out.println("str="+ProductUtil.docharnew(string));
		System.out.println("md5="+ProductUtil.genProductNameIDNEW(string));
		//alert(str.split(/[^a-zA-Z\d\.\u4e00-\u9fa5]+/).join('\n'));
		
//		System.out.println(isOutLimitProAttrCount(1, 0)); // true
//		System.out.println(isOutLimitProAttrCount(0, 0)); // false
//		System.out.println(isOutLimitProAttrCount(2, 1)); // false
//		System.out.println(isOutLimitProAttrCount(3, 1)); // true 
		
    }


    public static String getL2supplierid(String supplierid) {
        if (StringUtils.isBlank(supplierid)) return null;
        int len = supplierid.length();
        return supplierid.substring(len - 2, len);
    }
    
//    public static String filterHtmlDesc(String htmlDesc, boolean bFilterUrl){
//    	String ret = null;
//		if(StringUtils.isNotBlank(htmlDesc)){																		
//			RegUtilProduct rup=new RegUtilProduct();
//			String sHtmlContent = rup.setProductFeature(htmlDesc);	//过滤相关HTML标签、js代码 			
//			sHtmlContent=htmlDesc.trim().replaceAll("\\s+", " ");	//两个以上的空格替换成一个空格					
//			Pattern pattern = Pattern.compile("[\\x80-\\xff]");	        			
//			Matcher matcher = pattern.matcher(htmlDesc);	    			
//			htmlDesc = matcher.replaceAll("");
//			htmlDesc = ComUtil.removeSpecialChar(htmlDesc);		    		
//			ret = sHtmlContent;					
//		}
//    	return ret;
//    }
    
	/**
	 * 判断是否是海外直发产品
	 * 
	 * @param dhporttype
	 */
	public static boolean isDhport(String isDhportType) {
		return ProductSys.DHPORTNORMAL.equals(isDhportType) || ProductSys.DHPORTBOOKING.equals(isDhportType) || ProductSys.DHPORTPRESALE.equals(isDhportType)
				|| ProductSys.DHPORTSTOCK.equals(isDhportType);
	}
	

	/**
	 * 根据产品上下架状态获取推送位
	 * 
	 * @param istate
	 *            上下架状态
	 * @return
	 */
	public static String getSrhstateByIstate(String istate){
		if (ProductSys.PRODUCT_ISTATE_ONLINE.equals(istate)) {
			// 上架
			return ProductSys.VALID;
		} else if (ProductSys.PRODUCT_ISTATE_OFFLINE.equals(istate)) {
			// 下架
			return ProductSys.INVALID;
		} else if (ProductSys.PRODUCT_ISTATE_DELETE.equals(istate)) {
			// 删除
			return ProductSys.INVALID;
		} else {
			throw new RuntimeException("产品上下架状态不正确。");
		}
	}
	
	/**
	 * 将备货规格的属性排序后重组
	 * @param specDesc "516800_569944__517700_1156444"
	 * @return
	 */
	public static String getSpecDescBySort(String specDesc) {
		if (StringUtil.isEmpty(specDesc)) {
			return specDesc;
		}
		String vals[] = specDesc.split("__");
		if (vals.length == 1) {
			return specDesc;
		}
		Arrays.sort(vals);
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < vals.length; i++) {
			result.append(vals[i]);
			if (i < vals.length - 1) {
				result.append("__");
			}
		}
		return result.toString();
	}
	
	/**
	 * Long 转Integer
	 * @param numer
	 * @return
	 */
	public static Integer longToInteger(Long numer) {
		if (numer == null) {
			return null;
		} 
		return numer.intValue();
	}
	/**
     * 获取产品最终页标准URL add by chenlei 
     * @param productName
     * @param productid
     * @param istate
     * @param srhstate
     * @return
     */
    public static String setCanonicalUrl(String productName,Long itemcode){
    	String productUrl="";
    	if(StringUtils.isNotEmpty(productName) && itemcode != null){
    		//if ((ProductSys.PRODUCT_ISTATE_ONLINE.equals(istate)) 
    			//	|| (ProductSys.PRODUCT_ISTATE_OFFLINE.equals(istate)&&ProductSys.UN_SRHSTATE.equals(srhstate))) {
    			//istat=1,srhstat=7 或2-6已同步产品使用新URL
    			productUrl = formatKeyword(productName.replaceAll("&quot;", ""));
    			productUrl = getCanonicalUrl(productName,itemcode);
			//}else{
				//未同步产品使用product/productdisplay.do?pid=xxx
				//productUrl = "product/productdisplay.do?from=product_preview&itemcode="+itemcode;
			//}
    	}
    	return productUrl;
    }
    
    public final static String formatKeyword(String keyword){
        if(keyword==null)
            return "new-style";
        try{
            String s = keyword.replaceAll(regEx, " ");
            s = s.toLowerCase().trim();
            String regex = "free[\\s-_]*shipping[^\\w]*";
            s = s.replaceAll(regex, "");
            s = s.replaceAll("[^\\w]+", DELIMITER);
            s = s.replaceAll("-+", DELIMITER);
            s = URLEncoder.encode(s);
 
            if( s.length()>KEYWORD_LENGTH_IN_URL ){
                int i = s.indexOf(DELIMITER,KEYWORD_LENGTH_IN_URL);
                if(i>-1){
                    s = s.substring(0,i);
                }
            }
            return s.toLowerCase();
        }catch(Exception e){
            log.error("正则表达式处理错误: "+keyword);
            return "new-style";
        }
    }
    
    //获取标准URL add by chenlei
    public static String getCanonicalUrl(String productName,Long itemcode){
		String filterProName = formatKeyword(productName.replaceAll("&quot;", ""));
		String  canonicalUrl= "product/" + filterProName + "/" + itemcode + ".html";
		return canonicalUrl;
	}
    
	/**
	 * 页面参数"产品组"处理
	 * 
	 * @param groupid
	 *            选中的产品组
	 * @return
	 */
	public static String getQueryProdGroupId(String groupid) {
		if (groupid == null || "".equals(groupid.trim())) {
			return null;
		}
		String prodGroupId = null;
		if ("nogroup".equals(groupid)) {
			prodGroupId = groupid;
		} else {
			String[] groupds = groupid.split(",");
			if (groupds.length == 2 && StringUtils.isNotBlank(groupds[1])) {
				prodGroupId = groupds[1];
			} else {
				prodGroupId = groupds[0];
			}
		}
		return prodGroupId;
	}


}
