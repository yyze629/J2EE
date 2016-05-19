package com.yinyang.yy.utils;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@SuppressWarnings("unchecked")
public class Dom4jUtil {

	static String[] response = new String[] { "", "", "" }; // 不考虑并发的

	public static Element getRootElement(String input) {

		Document document;
		Element node = null;
		try {
			document = DocumentHelper.parseText(input);
			node = document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取根节点元素对象
		return node;
	}

	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * 
	 * @param node
	 */
	public static void listNodes(Element node) {

		if ("RetCode".equals(node.getName())) {
			// System.out.println("订购结果："+(node.getTextTrim().equals("1")?"失败":"成功"))
			response[0] = node.getTextTrim();
		}

		if ("RetDesc".equals(node.getName())) {
			// System.out.println("订购描述："+node.getTextTrim());

			response[1] = node.getTextTrim();
		}

		if ("TransactionID".equals(node.getName())) {
			response[2] = node.getTextTrim();
		}
		// 当前节点下面子节点迭代器
		Iterator<Element> it = node.elementIterator();
		// 遍历
		while (it.hasNext()) {
			// 获取某个子节点对象
			Element e = it.next();
			// 对子节点进行遍历
			listNodes(e);
		}
	}

	/**
	 * @param node
	 *            获取资源 检查资源数，如果小于50个资源报警
	 */
	public static boolean resCheck(Element node, int check) {
		boolean flag = true;
		Element reslists = node.element("WebBody").element("RetInfo");

		Iterator<Element> it = reslists.elementIterator();

		while (it.hasNext()) {
			Element e1 = it.next();

			if ("ResList".equals(e1.getName())) {
				Element e2 = e1.element("AvailableAmount");
				Element e3 = e1.element("ResName");
				int count = Integer.parseInt(e2.getTextTrim());
				if (count < check && !"20元150M移动数据流量加油包会员版".equals(e3.getTextTrim())) {
					flag = false;
					break;
				}
			}
		}

		return flag;

	}

	/**
	 * @param node
	 * @return 获取资源
	 */
	public static String resGet(Element node) {
		StringBuffer sb = new StringBuffer();
		Element reslists = node.element("WebBody").element("RetInfo");

		Iterator<Element> it = reslists.elementIterator();

		while (it.hasNext()) {
			Element e1 = it.next();

			if ("ResList".equals(e1.getName())) {
				Element e2 = e1.element("ResName");
				Element e3 = e1.element("AvailableAmount");

				sb.append("\r\n\r\n" + e2.getTextTrim() + "   " + e3.getTextTrim() + "个" + "\r\n");

			}
		}

		return sb.toString();

	}

	/**
	 * @param node
	 * @return 解析卓望的数据看是成功还是失败
	 */
	public static String[] getZhaoWang(Element node) {

		String[] result = { "", "", "" };
		try {
			Element res = node.element("RspCode");
			result[0] = res.getTextTrim();

			Element msg = node.element("ResultMSG");
			result[1] = msg.getTextTrim();

			if ("0000".equals(result[0])) { // success
				Element orderId = node.element("IMPPOrderId");
				result[2] = orderId.getTextTrim();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;

	}

	/**
	 * @return 把运营商的结果返回
	 */
	public static String[] getRespones() {
		return response;
	}

}
