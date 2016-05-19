package com.yinyang.sellerpay.web.util;

import java.io.Serializable;
import java.util.HashMap;

/**
 * dhgateSessionBean是一个值对象容器，用于保存往memcache里对象
 * @author sunbaoming
 *
 */
public class DHgateSessionBean implements Serializable {
    /**！！！！请不要修改这个值！！！！，与其它如adcenter等保持同步**/
    private static final long serialVersionUID = 7281820360326562059L;
    
	private HashMap<Object,Object> SessionBean = new HashMap<Object, Object>();

	public HashMap<Object, Object> getSessionBean() {
		return SessionBean;
	}

	public void setSessionBean(HashMap<Object, Object> sessionBean) {
		SessionBean = sessionBean;
	}
}
