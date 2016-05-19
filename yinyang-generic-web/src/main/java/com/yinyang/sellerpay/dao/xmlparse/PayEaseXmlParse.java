package com.yinyang.sellerpay.dao.xmlparse;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.sellerpay.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.util.SellerPayConstant;

public class PayEaseXmlParse {
	
	private static final Log log = LogFactory.getLogger(PayEaseXmlParse.class);
	
	public static PayReconciliationDTO reconciliationParse(String xmlContent){
		PayReconciliationDTO dto = null;
		if(StringUtils.isBlank(xmlContent)){
			return dto;
		}
		try {
			Document document = DocumentHelper.parseText(xmlContent);
			Element root = document.getRootElement();  
			Element head = root.element("messagehead");
			log.info("ReconciliationParse-Head:"+head.getText());
			Element status = head.element("status");
			Element statusdesc = head.element("statusdesc");
			Element mid = head.element("mid");
			Element body = root.element("messagebody"); 
			log.info("ReconciliationParse-Body:"+body.getText());
			Element order = body.element("order");  
			Element oid = order.element("oid");
			Element pmode = order.element("pmode");
			Element pstatus = order.element("pstatus");
			Element pstring = order.element("pstring");
			Element amount = order.element("amount");
			Element moneytype = order.element("moneytype");
			Element isvirement = order.element("isvirement");
			Element sign = order.element("sign");
			
			dto = new PayReconciliationDTO();
			dto.setVStatus(status.getText());
			dto.setVDesc(statusdesc.getText());
			dto.setVMid(mid.getText());
			dto.setVOid(oid.getText());
			dto.setVPmodeDesc(pmode.getText());
			dto.setVPstatus(pstatus.getText());
			dto.setVPstring(pstring.getText());
			dto.setVAmountActualStr(amount.getText());
			dto.setVAmountActual(Double.parseDouble(dto.getVAmountActualStr()));
			dto.setVMoneytypeActual(moneytype.getText());
			dto.setVIsvirement(isvirement.getText());
			dto.setVSign(sign.getText());
			dto.setOperator(SellerPayConstant.PayEaseJob_Operator);
			dto.setCreateDate(new Date(System.currentTimeMillis()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Parse the reconciliation return from payease failed !XmlContent:"+xmlContent);
			log.error(e);
		}  
		return dto;
	}
	
	public static String getTestPayEaseXmlContent(){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		sb.append("<ordermessage>");
		sb.append("    <messagehead>");
		sb.append("        <status>0</status>");
		sb.append("        <statusdesc>Success</statusdesc>");
		sb.append("        <mid>2254</mid>");
		sb.append("        <oid>20151103-2254-SP10000-1000000015</oid>");
		sb.append("    </messagehead>");
		sb.append("    <messagebody>");
		sb.append("        <order>");
		sb.append("            <orderindex>1</orderindex>");
		sb.append("            <oid>20151103-2254-SP10000-1000000015</oid>");
		sb.append("            <pmode></pmode>");
		sb.append("            <pstatus>1</pstatus>");
		sb.append("            <pstring></pstring>");
		sb.append("            <amount>0.10</amount>");
		sb.append("            <moneytype>0</moneytype>");
		sb.append("            <isvirement>1</isvirement>");
		sb.append("            <sign>87c77a428876d31b2077e3731524e56f71ca4fe7753994b6951f20138a708f6df43429d73a5d53a244a6b7b214945a5589a9ed38782b4130cf9458960c2ef5cc2ff45c47801907a56827e70dddb94abf23d5d8cb62e39d421a90732bf9ca50cc3c932c42feeac383b181c9daf90486f53eb281b86432a9f1a6672fdf8f1d345b</sign>");
		sb.append("            <rmbamount>0.1</rmbamount>");
		sb.append("            <cardholder></cardholder>");
		sb.append("            <cardno></cardno>");
		sb.append("            <cardbank></cardbank>");
		sb.append("            <cardcountry></cardcountry>");
		sb.append("            <dmstatus></dmstatus>");
		sb.append("            <mpistatus></mpistatus>");
		sb.append("        </order>");
		sb.append("    </messagebody>");
		sb.append("</ordermessage>");
		return sb.toString();
	}

}
