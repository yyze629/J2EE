package com.yinyang.yy.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yinyang.yy.Column2dDTO;

/**
 * Column2DOfFCFUtil
 * 
 * @Description: FusionCharts的柱状图的工具类 (用于拼装XML格式化的数据)
 * @author zhangliuzhen
 * @version 1.0
 * @create time 2013-7-31 下午2:38:51
 */
public class FusionChartsUtil {

	/**
	 * 
	 * @Description: 构造<graph></graph>
	 *
	 * @param sb
	 * @return StringBuilder
	 * @create time 2013-7-31 下午2:41:10
	 */
	public static StringBuilder graph(StringBuilder sb, Integer flag) {
		if (flag == 1) {// 开始
			sb.append("<graph caption='' xAxisName='' yAxisName='' decimalPrecision='0'            ");
			sb.append("	formatNumberScale='0'");
			sb.append("chartRightMargin='20' ");
			sb.append(" labelDisplay='STAGGER'");
			sb.append(" canvasBorderColor='cccccc'");// 帆布边框颜色
			// 禁止动画效果
			sb.append("defaultAnimation='0' ");
			sb.append("Animation='0' ");
			// sb.append(" divLineIsDashed='0'");
			// sb.append(" plotBorderDashed='1'");
			// sb.append("	defaultAnimation='0' Animation='0'>                                        ");
		} else if (flag == 2) {// 结束
			sb.append("</graph>");
		} else {
			sb.append(" >");
		}
		return sb;
	}

	/**
	 * 
	 * @Description: 设置Graph的属性
	 *
	 * @param sb
	 * @param attributeName
	 * @param attributeValue
	 * @return StringBuilder
	 * @create time 2013-8-1 上午11:32:02
	 */
	public static StringBuilder attributeOfGraph(StringBuilder sb, String attributeName, String attributeValue) {
		sb.append(attributeName).append("='").append(attributeValue).append("'");
		return sb;
	}

	/**
	 * 
	 * @Description: 构造<set></set>
	 *
	 * @param sb
	 * @return StringBuilder
	 * @create time 2013-7-31 下午2:41:29
	 */
	public static StringBuilder set(StringBuilder sb, List<Column2dDTO> column2dDTOList) {
		if (column2dDTOList == null) {
			return sb;
		}

		if (column2dDTOList.size() == 0) {
			return sb;
		}

		Column2dDTO column2dDTO = null;
		String name = "";
		String value = "";
		for (int i = 0; i < column2dDTOList.size(); i++) {
			column2dDTO = column2dDTOList.get(i);
			name = column2dDTO.getName();
			value = column2dDTO.getValue();
			sb.append("<set name='").append(name).append("' value='").append(value).append("' color='").append("008ED6").append("' />    ");
			// 为了保证柱状图的位置[备注:size为1的情况保证柱状图在正中间的位置],进行如下判断
			if (column2dDTOList.size() != 1) {
				sb.append("<set name='").append("").append("' value='").append("").append("' color='").append("008ED6").append("' />    ");// 此行代码是控制柱状图的宽度
			}

		}

		return sb;
	}

	public static StringBuilder setOfExtends(StringBuilder sb, List<Column2dDTO> column2dDTOList, Integer interval) {
		if (column2dDTOList == null) {
			return sb;
		}

		if (column2dDTOList.size() == 0) {
			return sb;
		}

		Column2dDTO column2dDTO = null;
		String name = "";
		String value = "";
		for (int i = 0; i < column2dDTOList.size(); i++) {
			column2dDTO = column2dDTOList.get(i);
			name = column2dDTO.getName();
			value = column2dDTO.getValue();

			sb.append("<set name='").append(name).append("' value='").append(value).append("' color='").append("008ED6").append("' />    ");
			sb.append("<set name='").append("").append("' value='").append("").append("' color='").append("008ED6").append("' />    ");// 此行代码是控制柱状图的宽度

			/*
			 * if ((i) % interval == 0) {//x轴显示能被3整除的x
			 * sb.append("<set name='").append
			 * (name).append("' value='").append(value
			 * ).append("' color='").append("008ED6") .append("' />    ");
			 * sb.append
			 * ("<set name='").append("").append("' value='").append("")
			 * .append("' color='"
			 * ).append("008ED6").append("' />    ");//此行代码是控制柱状图的宽度 } else {
			 * sb.
			 * append("<set name='").append(name).append("' value='").append(value
			 * ).append("' color='").append("008ED6").append("'")
			 * .append(" tooltext='").append("aaa").append("' />    ");
			 * sb.append
			 * ("<set name='").append("").append("' value='").append("")
			 * .append("' color='"
			 * ).append("008ED6").append("' />    ");//此行代码是控制柱状图的宽度 }
			 */

		}

		return sb;
	}

	/**
	 * 
	 * @Description: 计算y轴不同的值的最小个数是否大于等于5,成立返回true,否则返回false
	 *               [备注：防止y轴显示的存在相同的值,目前Y线条共四条]
	 * @param column2dDTOList
	 * @return int
	 * @create time 2013-8-8 下午6:09:03
	 */
	@SuppressWarnings("unchecked")
	public static boolean compute(List<Column2dDTO> column2dDTOList) {
		boolean yes = false;

		if (column2dDTOList != null && column2dDTOList.size() >= 5) {
			@SuppressWarnings("rawtypes")
			Set set = new HashSet();
			Column2dDTO column2dDTO = null;
			for (int i = 0; i < column2dDTOList.size(); i++) {
				column2dDTO = column2dDTOList.get(i);
				set.add(column2dDTO.getValue());

				if (set.size() >= 5) {
					yes = true;
					break;
				}
			}

		}
		return yes;
	}

	public static Long getMax(List<Column2dDTO> column2dDTOList) {
		Long max = 5L;

		if (column2dDTOList != null && column2dDTOList.size() >= 5) {
			Column2dDTO column2dDTO = null;

			for (int i = 0; i < column2dDTOList.size(); i++) {
				column2dDTO = column2dDTOList.get(i);
				if (column2dDTO.getValue() != null && !"".equals(column2dDTO.getValue()) && Long.valueOf(column2dDTO.getValue()) > max) {
					max = Long.valueOf(column2dDTO.getValue());
				}
			}

		}

		return max;
	}

	public static void yAxisMaxValue(StringBuilder sb, List<Column2dDTO> list) {
		if (!compute(list)) {
			Long max = getMax(list);
			max = max + 5;
			FusionChartsUtil.attributeOfGraph(sb, "yAxisMaxValue", String.valueOf(max));// 追加属性
		}
	}
}
