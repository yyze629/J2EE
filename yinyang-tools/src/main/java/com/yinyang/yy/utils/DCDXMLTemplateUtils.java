package com.yinyang.yy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 * XML解析工具类[代充单-下载单据]
 * 
 * @author 作者：yinyang E-mail:
 * @date 创建时间：2015年4月7日 下午1:19:14
 * @version 1.0
 */
public class DCDXMLTemplateUtils {

	private static Log log = LogFactory.getLog(DCDXMLTemplateUtils.class);

	private Map<String, Object> billMap = new HashMap<String, Object>();

	public Map<String, Object> getBooks(String xmlPath) {

		try {
			InputStream is = new FileInputStream(new File(xmlPath));
			List<Map<String, String>> billItemList = null;
			Map<String, String> map = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			// 处理单个节点node
			this.getSingleNodeValue(element);
			// 循环节点NodeList
			NodeList bookNodes = element.getElementsByTagName("BillItem");
			// System.out.println("bookNodes.getLength():" +
			// bookNodes.getLength());
			billItemList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < bookNodes.getLength(); i++) {
				Element bookElement = (Element) bookNodes.item(i);
				NodeList childNodes = bookElement.getChildNodes();
				map = new HashMap<String, String>();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if ("BillNo".equals(childNodes.item(j).getNodeName())) {
							map.put("BillNo", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("BillTime".equals(childNodes.item(j).getNodeName())) {
							map.put("BillTime", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ProductCode".equals(childNodes.item(j).getNodeName())) {
							map.put("ProductCode", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ProductName".equals(childNodes.item(j).getNodeName())) {
							map.put("ProductName", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ParPrice".equals(childNodes.item(j).getNodeName())) {
							map.put("ParPrice", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ProductNum".equals(childNodes.item(j).getNodeName())) {
							map.put("ProductNum", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ReceiptPrice".equals(childNodes.item(j).getNodeName())) {
							map.put("ReceiptPrice", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ReceiptAmt".equals(childNodes.item(j).getNodeName())) {
							map.put("ReceiptAmt", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("SalePrice".equals(childNodes.item(j).getNodeName())) {
							map.put("SalePrice", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("SaleAmt".equals(childNodes.item(j).getNodeName())) {
							map.put("SaleAmt", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("RegionName".equals(childNodes.item(j).getNodeName())) {
							map.put("RegionName", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("RegionValue".equals(childNodes.item(j).getNodeName())) {
							map.put("RegionValue", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ServerName".equals(childNodes.item(j).getNodeName())) {
							map.put("ServerName", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ServerValue".equals(childNodes.item(j).getNodeName())) {
							map.put("ServerValue", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("ChargeAccount".equals(childNodes.item(j).getNodeName())) {
							map.put("ChargeAccount", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("FromIP".equals(childNodes.item(j).getNodeName())) {
							map.put("FromIP", childNodes.item(j).getFirstChild().getNodeValue());
						}
					}
				}// map
				billItemList.add(map);
			}// list
			billMap.put("BillItem", billItemList);
		} catch (Exception e) {
			log.error("代充单-下载单据解析XML文件出错，文件路径为：[" + xmlPath + "]", e);
		}
		return billMap;
	}

	/**
	 * 处理单个节点node业务逻辑
	 * 
	 * @param element
	 * @return
	 */
	private Map<String, Object> getSingleNodeValue(Element element) {
		// RetCode
		NodeList retCode = element.getElementsByTagName("RetCode");
		Element retCodeElement = (Element) retCode.item(0);
		// System.out.println(retCodeElement.getTagName());
		// System.out.println(retCodeElement.getFirstChild().getNodeValue());
		billMap.put(retCodeElement.getTagName(), retCodeElement.getFirstChild().getNodeValue());

		// RetMsg
		NodeList retMsg = element.getElementsByTagName("RetMsg");
		Element retMsgElement = (Element) retMsg.item(0);
		// System.out.println(retMsgElement.getTagName());
		// System.out.println(retMsgElement.getFirstChild().getNodeValue());
		billMap.put(retMsgElement.getTagName(), retMsgElement.getFirstChild().getNodeValue());

		// AgentID
		NodeList agentID = element.getElementsByTagName("AgentID");
		Element agentIDElement = (Element) agentID.item(0);
		// System.out.println(agentIDElement.getTagName());
		// System.out.println(agentIDElement.getFirstChild().getNodeValue());
		billMap.put(agentIDElement.getTagName(), agentIDElement.getFirstChild().getNodeValue());

		// DownLoadTime
		NodeList downLoadTime = element.getElementsByTagName("DownLoadTime");
		Element downLoadTimeElement = (Element) downLoadTime.item(0);
		// System.out.println(downLoadTimeElement.getTagName());
		// System.out.println(downLoadTimeElement.getFirstChild().getNodeValue());
		billMap.put(downLoadTimeElement.getTagName(), downLoadTimeElement.getFirstChild().getNodeValue());

		return billMap;
	}

	// 解析XML举例-并打印
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DCDXMLTemplateUtils aa = new DCDXMLTemplateUtils();
		// InputStream input =
		// aa.getClass().getClassLoader().getResourceAsStream("E:/尹洋/test.xml");
		String path = "E:/尹洋/test.xml";
		aa.getBooks(path);

		System.out.println(aa.billMap.get("RetCode"));
		System.out.println(aa.billMap.get("RetMsg"));
		System.out.println(aa.billMap.get("AgentID"));
		System.out.println(aa.billMap.get("DownLoadTime"));

		List<Map<String, String>> billItemList = null;
		billItemList = new ArrayList<Map<String, String>>();
		billItemList = (ArrayList<Map<String, String>>) aa.billMap.get("BillItem");

		System.out.println("listMap:" + billItemList.size());
		for (int k = 0; k < billItemList.size(); k++) {
			Map<String, String> aabb = billItemList.get(k);
			System.out.println(aabb.get("ProductName"));
			System.out.println(aabb.get("FromIP"));
		}
	}
}

// 模板示例如下：
/**
 * <BillList> <RetCode>0</RetCode> <RetMsg>获取成功</RetMsg>
 * <AgentID>88888</AgentID> <DownLoadTime>20120202122030</DownLoadTime>
 * <BillItem> <BillNo>AABBKJJHOL</BillNo> <BillTime>20110804121225</BillTime>
 * <ProductCode>AASHQFSGZB015EV</ProductCode>
 * <ProductName>起凡一卡通(三国争霸/群雄逐鹿)_1500通宝-15元</ProductName>
 * <ParPrice>15</ParPrice> <ProductNum>2</ProductNum>
 * <ReceiptPrice>14.5000</ReceiptPrice> <ReceiptAmt>29.0000</ReceiptAmt>
 * <SalePrice>15.0000</SalePrice> <SaleAmt>30.0000</SaleAmt>
 * <RegionName>一区</RegionName> <RegionValue>1</RegionValue>
 * <ServerName>一服</ServerName> <ServerValue>11</ServerValue>
 * <ChargeAccount>test123456</ChargeAccount> <FromIP>192.168.2.1</FromIP>
 * </BillItem> <BillItem> <BillNo>AA11111L</BillNo>
 * <BillTime>20110804121225</BillTime>
 * <ProductCode>AASHQFSGZB015EV</ProductCode>
 * <ProductName>起凡一卡通(三国争霸/群雄逐鹿)_1500通宝-15元2222</ProductName>
 * <ParPrice>15</ParPrice> <ProductNum>2</ProductNum>
 * <ReceiptPrice>12.5000</ReceiptPrice> <ReceiptAmt>22.0000</ReceiptAmt>
 * <SalePrice>12.0000</SalePrice> <SaleAmt>32.0000</SaleAmt>
 * <RegionName>2区</RegionName> <RegionValue>2</RegionValue>
 * <ServerName>2服</ServerName> <ServerValue>11</ServerValue>
 * <ChargeAccount>test122256</ChargeAccount> <FromIP>192.168.2.2</FromIP>
 * </BillItem> </BillList>
 */
