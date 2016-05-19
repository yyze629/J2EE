package com.yinyang.yy;

import java.io.Serializable;

/**
 * 
* @Description: 柱状图的Set对象
*
* @author zhangliuzhen
* @version 1.0
* @create time 2013-7-31 下午7:39:13
 */
public class Column2dDTO implements Serializable {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 2851859828153054581L;

	private String name;

	private String value;

	private String color;
	
	private String Scale;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

	public String getColor() {

		return color;
	}

	public void setColor(String color) {

		this.color = color;
	}

	public String getScale() {
	
		return Scale;
	}

	public void setScale(String scale) {
	
		Scale = scale;
	}

	
}
