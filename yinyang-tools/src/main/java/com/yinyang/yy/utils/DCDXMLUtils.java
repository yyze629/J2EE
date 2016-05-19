package com.yinyang.yy.utils;

import java.io.ByteArrayInputStream;
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
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年4月7日 下午1:19:14 
 * @version 1.0  
 */
public class DCDXMLUtils {
	
	private static Log log = LogFactory.getLog(DCDXMLUtils.class);
	
	private Map<String,Object> billMap = null;
	
	/**
	 * 解析xml文件
	 * @param xmlPath XML文件地址
	 * @return
	 */
	public Map<String,Object> parseXMLFile(String xmlPath){
		billMap = new HashMap<String,Object>();
		try {
			InputStream is = new FileInputStream(new File(xmlPath));
			List<Map<String, String>> billItemList = null;
			Map<String, String> map = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element element = document.getDocumentElement();
			//处理单个节点node
			this.getSingleNodeValue(element);
			
			//循环节点NodeList
			NodeList newDataSet = element.getElementsByTagName("NewDataSet");
			Element newDataSetElement = (Element)newDataSet.item(0);
			System.out.println("NewDataSet的值："+newDataSetElement.getTagName());
			
			
			
			NodeList bookNodes = element.getElementsByTagName("Table");
			//System.out.println("bookNodes.getLength():" + bookNodes.getLength());
			billItemList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < bookNodes.getLength(); i++) {
				Element bookElement = (Element) bookNodes.item(i);
				NodeList childNodes = bookElement.getChildNodes();
				map = new HashMap<String, String>();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if ("Bill_No".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_No", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Bill_Time".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_Time", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Charge_Type".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Type", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Supplier_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("Supplier_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_User".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_User", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Category_Code".equals(childNodes.item(j).getNodeName())) {
							map.put("Category_Code", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Code".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Code", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Num".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Num", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Par_Price".equals(childNodes.item(j).getNodeName())) {
							map.put("Par_Price", childNodes.item(j).getFirstChild().getNodeValue());
						} 
						/*else if ("Game_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Game_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Game_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Game_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Region_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Region_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Region_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Region_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Server_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Server_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Server_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Server_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} */
						else if ("Charge_Account".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Account", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("From_IP".equals(childNodes.item(j).getNodeName())) {
							map.put("From_IP", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("From_Province_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("From_Province_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} 
						/*else if ("Charge_Note".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Note", childNodes.item(j).getFirstChild().getNodeValue());
						} */
						else if ("Bill_Status".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_Status", childNodes.item(j).getFirstChild().getNodeValue());
						} 
					}
				}//map
				billItemList.add(map);
			}//list
			billMap.put("NewDataSet", billItemList);
		} catch (Exception e) {
			log.error("代充单-下载单据解析XML文件出错，文件路径为：["+xmlPath+"]",e);
		}
		return billMap;
	}
	
	/**
	 * 解析XML字符串
	 * @param xmlString
	 * @return
	 */
	public Map<String,Object> parseXMLString(String xmlString){
		billMap = new HashMap<String,Object>();
		
		List<Map<String, String>> billItemList = null;
		Map<String, String> map = null;
		try {
			//document = (Document) DocumentHelper.parseText(xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			ByteArrayInputStream is = new ByteArrayInputStream(xmlString.getBytes("GB2312"));
			Document document = builder.parse(is);
			
			Element element = document.getDocumentElement();
			//先判断状态
			if(!this.getXMLState(element)){
				log.warn("代充单-下载单据解析XML字符串失败，xml字符串为：["+xmlString+"]");
				//RetCode
				NodeList retCode = element.getElementsByTagName("RetCode");
				Element retCodeElement = (Element)retCode.item(0);
				billMap.put(retCodeElement.getTagName(), retCodeElement.getFirstChild().getNodeValue());
				
				//RetMsg
				NodeList retMsg = element.getElementsByTagName("RetMsg");
				Element retMsgElement = (Element)retMsg.item(0);
				billMap.put(retMsgElement.getTagName(), retMsgElement.getFirstChild().getNodeValue());
				
				return billMap;
			}
			//处理单个节点node
			this.getSingleNodeValue(element);
			
			//循环节点NodeList
			NodeList newDataSet = element.getElementsByTagName("NewDataSet");
			Element newDataSetElement = (Element)newDataSet.item(0);
			System.out.println("NewDataSet的值："+newDataSetElement.getTagName());
			
			
			
			NodeList bookNodes = element.getElementsByTagName("Table");
			//System.out.println("bookNodes.getLength():" + bookNodes.getLength());
			billItemList = new ArrayList<Map<String, String>>();
			for (int i = 0; i < bookNodes.getLength(); i++) {
				Element bookElement = (Element) bookNodes.item(i);
				NodeList childNodes = bookElement.getChildNodes();
				map = new HashMap<String, String>();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if ("Bill_No".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_No", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Bill_Time".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_Time", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Charge_Type".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Type", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Supplier_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("Supplier_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Client_User".equals(childNodes.item(j).getNodeName())) {
							map.put("Client_User", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Category_Code".equals(childNodes.item(j).getNodeName())) {
							map.put("Category_Code", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Code".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Code", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Product_Num".equals(childNodes.item(j).getNodeName())) {
							map.put("Product_Num", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Par_Price".equals(childNodes.item(j).getNodeName())) {
							map.put("Par_Price", childNodes.item(j).getFirstChild().getNodeValue());
						} 
						/*else if ("Game_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Game_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Game_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Game_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Region_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Region_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Region_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Region_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Server_Name".equals(childNodes.item(j).getNodeName())) {
							map.put("Server_Name", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("Server_Value".equals(childNodes.item(j).getNodeName())) {
							map.put("Server_Value", childNodes.item(j).getFirstChild().getNodeValue());
						} */
						else if ("Charge_Account".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Account", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("From_IP".equals(childNodes.item(j).getNodeName())) {
							map.put("From_IP", childNodes.item(j).getFirstChild().getNodeValue());
						} else if ("From_Province_ID".equals(childNodes.item(j).getNodeName())) {
							map.put("From_Province_ID", childNodes.item(j).getFirstChild().getNodeValue());
						} 
						/*else if ("Charge_Note".equals(childNodes.item(j).getNodeName())) {
							map.put("Charge_Note", childNodes.item(j).getFirstChild().getNodeValue());
						} */
						else if ("Bill_Status".equals(childNodes.item(j).getNodeName())) {
							map.put("Bill_Status", childNodes.item(j).getFirstChild().getNodeValue());
						} 
					}
				}//map
				billItemList.add(map);
			}//list
			billMap.put("NewDataSet", billItemList);
		} catch (Exception e) {
			log.error("代充单-下载单据解析XML字符串出错，xml字符串为：["+xmlString+"]",e);
		}
		return billMap;
	}

	/**
	 * 处理单个节点node业务逻辑
	 * @param element
	 * @return
	 */
	private Map<String,Object> getSingleNodeValue(Element element){
		//RetCode
		NodeList retCode = element.getElementsByTagName("RetCode");
		Element retCodeElement = (Element)retCode.item(0);
		//System.out.println(retCodeElement.getTagName());
		//System.out.println(retCodeElement.getFirstChild().getNodeValue());
		billMap.put(retCodeElement.getTagName(), retCodeElement.getFirstChild().getNodeValue());
		
		//RetMsg
		NodeList retMsg = element.getElementsByTagName("RetMsg");
		Element retMsgElement = (Element)retMsg.item(0);
		//System.out.println(retMsgElement.getTagName());
		//System.out.println(retMsgElement.getFirstChild().getNodeValue());
		billMap.put(retMsgElement.getTagName(), retMsgElement.getFirstChild().getNodeValue());
		
		//AgentID
		NodeList agentID = element.getElementsByTagName("AgentID");
		Element agentIDElement = (Element)agentID.item(0);
		//System.out.println(agentIDElement.getTagName());
		//System.out.println(agentIDElement.getFirstChild().getNodeValue());
		billMap.put(agentIDElement.getTagName(), agentIDElement.getFirstChild().getNodeValue());
		
		//DownLoadTime
		NodeList downLoadTime = element.getElementsByTagName("DownLoadTime");
		Element downLoadTimeElement = (Element)downLoadTime.item(0);
		//System.out.println(downLoadTimeElement.getTagName());
		//System.out.println(downLoadTimeElement.getFirstChild().getNodeValue());
		billMap.put(downLoadTimeElement.getTagName(), downLoadTimeElement.getFirstChild().getNodeValue());
		
		return billMap;
	}
	
	/**
	 * 判断XML文件状态
	 * @param element
	 * @return
	 */
	private boolean getXMLState(Element element){
		boolean flag = false;
		//RetCode  0为操作成功
		NodeList retCode = element.getElementsByTagName("RetCode");
		Element retCodeElement = (Element)retCode.item(0);
		if("0".equals(retCodeElement.getFirstChild().getNodeValue())){
			flag = true;
		}
		
		return flag;
	}
	
	//解析XML举例-并打印
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		DCDXMLUtils aa = new DCDXMLUtils();
		//InputStream input = aa.getClass().getClassLoader().getResourceAsStream("E:/尹洋/test.xml"); 
		String path = "E:/尹洋/test0.xml";
		aa.parseXMLFile(path);
		
		System.out.println(aa.billMap.get("RetCode"));
		System.out.println(aa.billMap.get("RetMsg"));
		System.out.println(aa.billMap.get("AgentID"));
		System.out.println(aa.billMap.get("DownLoadTime"));

		List<Map<String, String>> billItemList = null;
		billItemList = new ArrayList<Map<String, String>>();
		billItemList = (ArrayList<Map<String, String>>)aa.billMap.get("NewDataSet");
		
		System.out.println("listMap:" + billItemList.size());
		for (int k = 0; k < billItemList.size(); k++) {
			Map<String, String> aabb = billItemList.get(k);
			System.out.println(aabb.get("Product_Name"));
			System.out.println(aabb.get("From_IP"));
		}
		
		/*System.out.println("------------------------------------------------");
		String xmlString = "<?xml version=\"1.0\" encoding=\"GB2312\" ?> <Return><RetCode>-1</RetCode><RetMsg>数据为空</RetMsg><AgentID>1956473</AgentID></Return>";
		aa.parseXMLString(xmlString);
		System.out.println("RetCode:"+aa.billMap.get("RetCode"));
		System.out.println("RetMsg:"+aa.billMap.get("RetMsg"));*/
	}
}

//模板示例如下：
/**
<?xml version="1.0" encoding="GB2312" ?> 
<Return>
	<RetCode>0</RetCode>
	<RetMsg>查询成功总数量：1</RetMsg>
	<AgentID>1662531</AgentID>
	<DownLoadTime>201309023105921</DownLoadTime>
	<NewDataSet>
		<Table>
			<Bill_No>C130922226723917</Bill_No>
			<Bill_Time>2013-09-22T17:34:09.66+08:00</Bill_Time>
			<Charge_Type>3</Charge_Type>
			<Supplier_ID>1662531</Supplier_ID>
			<Client_ID>585294</Client_ID>
			<Client_Name>roc1029</Client_Name>
			<Client_User>roc1029</Client_User>
			<Category_Code>ABBJGYZGYD</Category_Code>
			<Product_Code>ABBJGYZGYDB50EV</Product_Code>
			<Product_Name>全国移动_50元[5-10分钟即时到账gy]</Product_Name>
			<Product_Num>1</Product_Num>
			<Par_Price>50.0000</Par_Price>
			<Game_Name></Game_Name>
			<Game_Value></Game_Value>
			<Region_Name></Region_Name>
			<Region_Value></Region_Value>
			<Server_Name></Server_Name>
			<Server_Value></Server_Value>
			<Charge_Account>15010009068</Charge_Account>
			<From_IP>210.082.109.025</From_IP>
			<From_Province_ID>2</From_Province_ID>
			<Charge_Note></Charge_Note>
			<Bill_Status>0</Bill_Status>
		</Table>
		<Table>
			<Bill_No>C130922222222</Bill_No>
			<Bill_Time>2013-09-22T17:34:09.66+08:00</Bill_Time>
			<Charge_Type>3</Charge_Type>
			<Supplier_ID>16625312</Supplier_ID>
			<Client_ID>5852942</Client_ID>
			<Client_Name>roc10292</Client_Name>
			<Client_User>roc10292</Client_User>
			<Category_Code>ABBJGYZGYD2</Category_Code>
			<Product_Code>ABBJGYZGYDB50EV2</Product_Code>
			<Product_Name>全国移动_50元[5-10分钟即时到账gy]2</Product_Name>
			<Product_Num>12</Product_Num>
			<Par_Price>502.0000</Par_Price>
			<Game_Name></Game_Name>
			<Game_Value></Game_Value>
			<Region_Name></Region_Name>
			<Region_Value></Region_Value>
			<Server_Name></Server_Name>
			<Server_Value></Server_Value>
			<Charge_Account>150100090628</Charge_Account>
			<From_IP>210.082.109.025</From_IP>
			<From_Province_ID>2</From_Province_ID>
			<Charge_Note></Charge_Note>
			<Bill_Status>0</Bill_Status>
		</Table>
		<Table>
			<Bill_No>C130922226723917</Bill_No>
			<Bill_Time>2013-09-22T17:34:09.66+08:00</Bill_Time>
			<Charge_Type>3</Charge_Type>
			<Supplier_ID>1662531</Supplier_ID>
			<Client_ID>585294</Client_ID>
			<Client_Name>roc1029</Client_Name>
			<Client_User>roc1029</Client_User>
			<Category_Code>ABBJGYZGYD</Category_Code>
			<Product_Code>ABBJGYZGYDB50EV</Product_Code>
			<Product_Name>全国移动_50元[5-10分钟即时到账gy]</Product_Name>
			<Product_Num>1</Product_Num>
			<Par_Price>250.0000</Par_Price>
			<Game_Name></Game_Name>
			<Game_Value></Game_Value>
			<Region_Name></Region_Name>
			<Region_Value></Region_Value>
			<Server_Name></Server_Name>
			<Server_Value></Server_Value>
			<Charge_Account>15010009068</Charge_Account>
			<From_IP>210.082.109.025</From_IP>
			<From_Province_ID>2</From_Province_ID>
			<Charge_Note></Charge_Note>
			<Bill_Status>0</Bill_Status>
		</Table>
	</NewDataSet>
</Return>
*/