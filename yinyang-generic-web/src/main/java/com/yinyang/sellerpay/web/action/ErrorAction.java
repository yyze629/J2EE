package com.yinyang.sellerpay.web.action;


import java.io.IOException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

@Results({ @Result(name = "error", location = "/content/error/error.vm", type = "velocity") })
@ParentPackage("default")
@Namespace("/")
public class ErrorAction extends SellerPayBaseAction {

	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	public static Log log = LogFactory.getLogger(ErrorAction.class);

	@Action(value = "errorAction")
	public String errorAction() throws IOException {
		return "error";
	}

}
