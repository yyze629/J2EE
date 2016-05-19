package com.yinyang.sellerpay.dao.util;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlParse {
	
	public static void main(String[] args){
		StringBuffer text = new StringBuffer();
		text.append("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
		text.append("<ordermessage>");
		text.append("    <messagehead>");
		text.append("        <status>0</status>");
		text.append("        <statusdesc>Success</statusdesc>");
		text.append("        <mid>1708</mid>");
		text.append("        <oid>20150916-1708-20002000060085</oid>");
		text.append("    </messagehead>");
		text.append("    <messagebody>");
		text.append("        <order>");
		text.append("            <orderindex>1</orderindex>");
		text.append("            <oid>20150916-1708-20002000060085</oid>");
		text.append("			 <pmode>ss</pmode>");
		text.append("			 <pstatus>1</pstatus>");
		text.append("			 <pstring>aa</pstring>");
		text.append("			 <amount>100.00</amount>");
		text.append("			 <moneytype>0</moneytype>");
		text.append("			 <isvirement>0</isvirement>");
		text.append("            <sign>5773b7c2cc705e5748df353455f61f5ff812739f99f105740b6c379c2577f39a749aa52dd8e2f6200b36809207f0b55653c7c35ff2244655a164ed398689e06f6c2d95721f67bf97c827ed43aec035da0530b97556c0dea2048cfe5e02acb6f57ab7c1d6d4fd77f3e77d2303855c05b0548721221ccef1150f7a504b1fabc329</sign>");
		text.append("            <rmbamount>100</rmbamount>");
		text.append("            <cardholder></cardholder>");
		text.append("            <cardno></cardno>");
		text.append("            <cardbank></cardbank>");
		text.append("             <cardcountry></cardcountry>");
		text.append("            <dmstatus></dmstatus>");
		text.append("            <mpistatus></mpistatus>");
		text.append("         </order>");
	    text.append("     </messagebody>");
	    text.append(" </ordermessage>");
		text.append("");
		try {
			Document document = DocumentHelper.parseText(text.toString());
			Element root = document.getRootElement();  
			Element head=root.element("messagehead");  
			Element body=root.element("messagebody");  
			Element order=body.element("order");  
			for(Iterator<Element> it=head.elementIterator();it.hasNext();){
				Element element = it.next();
				System.out.println(element.getName()+":"+element.getText());
			}
			for(Iterator<Element> it=order.elementIterator();it.hasNext();){
				Element element = it.next();
				if(element != null)
					System.out.println(element.getName()+":"+element.getText());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
