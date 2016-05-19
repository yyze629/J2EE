package com.yinyang.sellerpay.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

/**
 * 
* @Description: Lucky
*
* @author baixingang
* @version 1.0
 */
//@ParentPackage("default")
@ParentPackage("struts-default")
@Namespace("/lucky")
public class LuckyAction extends SellerPayBaseAction {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLogger(LuckyAction.class);


	@Action(value = "hello", results = { @Result(name = "success", type = "velocity", location = "/content/lucky/lucky.vm"),
			@Result(name = "error", type = "velocity", location = "/content/lucky/lucky.vm") })
	public String lucky() {
		
		return SUCCESS;
	}
}