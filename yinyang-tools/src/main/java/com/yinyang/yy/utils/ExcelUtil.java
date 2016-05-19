package com.yinyang.yy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Excel工具类
 * 
 * @Title: ExcelUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午2:52:06
 * @version V1.0
 */
@SuppressWarnings("resource")
public class ExcelUtil {

	private static final Log log = LogFactory.getLog(ExcelUtil.class);

	/**
     * 使用方式
     * 
     * action
     * @Action(value = "downloadHotSearch", results = { @Result(name = "success", type = "stream", params = { "contentType",
	            "application/vnd.ms-excel", "inputName", "inputStream", "contentDisposition", "attachment;filename=${filename}" }) })
	    .....此处省略一万行
	    log.info("Download time consume :" + (endTime - startTime));
		this.inputStream = ExcelUtil.export(heads, allBodyList);
		return SUCCESS;
     */
	
	/**
	 * 不分页的导出功能
	 * 
	 * @param heads
	 * @param bodyList
	 * @return
	 */
	public static InputStream export(Object[] heads, List<Object[]> bodyList) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row0 = sheet.createRow(0);
		if (heads != null) {
			for (int i = 0; i < heads.length; i++) {
				HSSFCell cellT1 = row0.createCell(i);
				cellT1.setCellValue(heads[i].toString());
			}
		}

		if (bodyList != null) {
			for (int j = 0; j < bodyList.size(); j++) {
				Object[] bodys = bodyList.get(j);
				if (bodys != null) {
					HSSFRow row = sheet.createRow(j + 1);
					for (int i = 0; i < bodys.length; i++) {
						HSSFCell cell = row.createCell(i);
						setCellValue(cell, bodys[i]);
					}
				}
			}
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());

	}

	/**
	 * 创建一个workbook
	 * 
	 * @return
	 */
	public static HSSFWorkbook creatWorkbook() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		return workbook;
	}

	/**
	 * 创建一页sheet
	 * 
	 * @param workbook
	 * @return
	 */
	public static HSSFSheet creatSheet(HSSFWorkbook workbook) {
		// sheet 名字统一设置
		HSSFSheet sheet = workbook.createSheet("sheet1");
		return sheet;
	}

	/**
	 * 创建每一页
	 * 
	 * @param sheet
	 * @param bodyList
	 * @param startIndx
	 *            第一页第一行的开始row
	 * @param pageIndex
	 * @param pageSize
	 */
	public static void createPageRows(HSSFSheet sheet, List<Object[]> bodyList, int startIndx, int pageIndex, int pageSize) {
		int rowindex = (pageIndex - 1) * pageSize;
		if (bodyList != null) {

			for (int j = 0; j < bodyList.size(); j++) {
				Object[] bodys = bodyList.get(j);

				if (bodys != null) {
					int currentRow = j + startIndx + rowindex;
					HSSFRow row = sheet.createRow(currentRow);

					for (int i = 0; i < bodys.length; i++) {
						HSSFCell cell = row.createCell(i);
						setCellValue(cell, bodys[i]);
					}

				}

			}
		}
	}

	/**
	 * 创建标题
	 * 
	 * @param sheet
	 * @param heads
	 */
	public static void createHead(HSSFSheet sheet, Object[] heads) {
		HSSFRow row0 = sheet.createRow(0);
		if (heads != null) {
			for (int i = 0; i < heads.length; i++) {
				HSSFCell cell = row0.createCell(i);
				cell.setCellValue(heads[i].toString());
			}
		}
	}

	/**
	 * workbook转换成inputstream
	 * 
	 * @param workbook
	 * @return
	 */
	public static InputStream getInputStream(HSSFWorkbook workbook) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			log.error("", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	/**
	 * 设置cell值
	 * 
	 * @param cell
	 * @param obj
	 */
	private static void setCellValue(HSSFCell cell, Object obj) {
		if (obj == null) {
			cell.setCellValue("");
		} else {
			try {
				cell.setCellValue(Double.valueOf(String.valueOf(obj)));
			} catch (NumberFormatException e) {
				cell.setCellValue((String) obj);
			}
		}
	}
}
