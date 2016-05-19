package com.yinyang.sellerpay.dao.util;

import com.capinfo.crypt.Md5;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

public class CapitalVerifySignUtil {

	private static final Log log = LogFactory.getLogger(CapitalVerifySignUtil.class);

    
    public static String generateDHBVerifySign(String text, String md5pass) throws Exception {
        String md5Str = "";
        Md5 md5 = new Md5("");
        md5.hmac_Md5(text, md5pass);
        byte b[] = md5.getDigest();
        md5Str = Md5.stringify(b);

        return md5Str;
    }
	

    
    public static void main(String[] args) {
    	String[] oidArr={"20111201-2254-10001322705273327"};
    	String url="http://210.73.90.18/merchant/order/order_ack_oid_list.jsp?v_mid=2254&v_oid=%s&v_mac=%s";
    	for(String oid:oidArr){
       	 String ary_mid[] = oid.split("-");
            String input = ary_mid[1] + oid;
           
//           try {
//           	 Md5 md5 = new Md5("");
//                md5.hmac_Md5(input, "dhgate20101028_2254");
//                byte b[] = md5.getDigest();
//                String md5Str = Md5.stringify(b);
//                System.out.println(String.format(url,oid,md5Str));
//                HttpClient client = new HttpClient();
//                GetMethod post = new GetMethod(String.format(url,oid,md5Str));
//                client.executeMethod(post);
//                if(post.getStatusCode() == HttpStatus.SC_OK) {
//                    String respstr = post.getResponseBodyAsString();
//                    String status = respstr.substring(respstr.indexOf("<status>")+8, respstr.indexOf("</status>"));
//                    if("0".equals(status)) {    // 处理正常
//                        String pstatus = respstr.substring(respstr.indexOf("<pstatus>")+9, respstr.indexOf("</pstatus>"));
//                        String amount = respstr.substring(respstr.indexOf("<amount>")+8, respstr.indexOf("</amount>"));
//                        String pstring = respstr.substring(respstr.indexOf("<pstring>")+8, respstr.indexOf("</pstring>"));
//                        System.out.println("pstatus=="+pstatus+"---------pstring=="+pstring);
//                    }
//                    else {
//                       
//                    }
//                }
//           }catch(Exception e) {
//               e.printStackTrace();
//           }	
    	}
    	
    }
}
