package com.yinyang.sellerpay.dao.util;

import java.net.URLDecoder;

import com.dhgate.common.util.CapinfoMD5;
import com.dhgate.sellerpay.dto.PayFlowDTO;


public class CapitalReturnUtil {
    private static CapinfoMD5 myRSA = new CapinfoMD5();

    public static String PublicKeyPath() {
        return "/Public1024.key";
    }   

    /**
     * 校验首信易返回的数字签名
     */
    public static boolean checkPayReturn(String VOid,String VPstatus,String VAmountActual,String VMoneytypeActual,String vsign) {
       try {
           String source = VOid + VPstatus + VAmountActual + VMoneytypeActual;
           int verifyStatus = myRSA.PublicVerifyMD5(PublicKeyPath() , vsign ,source);
           if(0 == verifyStatus)
               return true;
           return false;
       }catch(Exception e) {
           e.printStackTrace();
           return false;
       }
    }
    public static void main(String arg[]) throws Exception {
    	
		String v_oid = "20151102-1708-SP10000-1000000013";
	    String v_pstatus = "20";
	    String v_pstring = "%D6%A7%B8%B6%CD%EA%B3%C9";
	    String v_pmode = "%D5%D0%C9%CC%D2%F8%D0%D0%D2%BB%CD%F8%CD%A8";
	    String v_md5info = "86b4a6fec89d5ae2f944cbb36181170b";
	    String v_amount = "0.10";
	    String v_moneytype ="0";
	    String v_md5money = "5f795adf27b3621d089d9a1e594eb996";
	    String v_sign = "24e619d3741437c604e3c2b83a7acccd5f59c255669f2a94a4905ac2cfaa94b14ce92d852005c952b2a206672f6d47c9c04afcefa83ce49baec360fc049fe3d6a1e7c6948644caae1e520e06ae79ad383e5af8fede7c024849ea0ca618bf8cfc4095c102d53882a8c3f2b9961b1476f0be0653987801fac9cf88e08a7c77927d";
	    String source = v_oid + v_pstatus + v_amount + v_moneytype;
	    PayFlowDTO dto = new PayFlowDTO();
	    System.out.println("source = v_oid + v_pstatus + v_amount + v_md5money ------------"+source);
	    dto.setVOid(v_oid);
	    dto.setVAmountActualStr(v_amount);
	    dto.setVPstatus(v_pstatus);
	    dto.setVPstring(URLDecoder.decode(v_pstring, "gbk"));
	    dto.setVPmodeDesc(URLDecoder.decode(v_pmode, "gbk"));
        dto.setVMd5info(v_md5info);
        dto.setVMd5money(v_md5money);
        //dto.setVAmountActual(v_amount);
        dto.setVMoneytypeActual(v_moneytype);
        dto.setVSign(v_sign);
        
        boolean bool =CapitalReturnUtil.checkPayReturn(v_oid,v_pstatus,v_amount,v_moneytype,v_sign);
        System.out.println(bool);
        
        /*String text_sign = "20151102-1708-SP10000-1000000013200.100";
    	String v_sign = "24e619d3741437c604e3c2b83a7acccd5f59c255669f2a94a4905ac2cfaa94b14ce92d852005c952b2a206672f6d47c9c04afcefa83ce49baec360fc049fe3d6a1e7c6948644caae1e520e06ae79ad383e5af8fede7c024849ea0ca618bf8cfc4095c102d53882a8c3f2b9961b1476f0be0653987801fac9cf88e08a7c77927d";
        int key = myRSA.PublicVerifyMD5(PublicKeyPath(), v_sign, text_sign);
        System.out.println("key:"+key);*/
     }
    
}
