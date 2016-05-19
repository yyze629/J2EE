package com.yinyang.yy.superutils;

import java.io.File;
import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;

/**
 * 打印机工具类
 * 
 * @author 作者:尹洋 E-mail:yinyang@shujutang.com
 * @date 创建时间：2015年8月31日 上午10:20:39
 * @version 1.0
 */
public class PrinterUtils {

	public static void printer() {
		// 构造一个文件选择器，默认为当前目录
		JFileChooser fileChooser = new JFileChooser(new File("F:/齐亚云.doc"));//SystemProperties.USER_DIR
		int state = fileChooser.showOpenDialog(null);// 弹出文件选择对话框
		if (state == fileChooser.APPROVE_OPTION) // 如果用户选定了文件
		{
			File file = fileChooser.getSelectedFile();// 获取选择的文件
			// 构建打印请求属性集
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			// 设置打印格式，因为未确定文件类型，这里选择AUTOSENSE
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			// 查找所有的可用打印服务
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			// 定位默认的打印服务
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			// 显示打印对话框
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
			if (service != null) {
				try {
					DocPrintJob job = service.createPrintJob();// 创建打印作业
					FileInputStream fis = new FileInputStream(file);// 构造待打印的文件流
					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);// 建立打印文件格式
					job.print(doc, pras);// 进行文件的打印
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void simplePrinter() {
		JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
		int state = fileChooser.showOpenDialog(null);
		if (state == fileChooser.APPROVE_OPTION) {
			File file = new File("F:/齐亚云.doc"); // 获取选择的文件
			/** 构建打印请求属性集 */
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			/** 设置打印格式，因为未确定类型，所以选择autosense */
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			/** 查找所有的可用的打印服务 */
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			/** 定位默认的打印服务 */
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			/** 显示打印对话框 */
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
			if (service != null) {
				try {
					DocPrintJob job = service.createPrintJob(); // 创建打印作业
					FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);
					job.print(doc, pras);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		//simplePrinter();
		printer();
	}
}
