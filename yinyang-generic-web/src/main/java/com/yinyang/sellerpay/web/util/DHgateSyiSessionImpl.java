package com.yinyang.sellerpay.web.util;

import java.io.Serializable;
import java.util.HashMap;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

/**
 * syi-session
 */
public class DHgateSyiSessionImpl implements DHgateSession {

	private String sessionID;
	private String categoryKey;
	private String objectTypeKey;
	private long liveTime = 24 * 60L;
	private DHgateSessionBean sessionBean;

	private String syiSessionID;
	private static String syiPrefix = "syi_";
	public static final String DHgateMinimalSession="DHgateMinimalSession";
	private String minimalSessionID;
	private String minimalCategoryKey = "dhgate";
	private String minimalObjectTypeKey = "common";
	private static String minimalPrefix = "minimal_";
	static Log log = LogFactory.getLogger(DHgateSyiSessionImpl.class);

	public String getId() {
		return this.sessionID;
	}

	public void set(String key, Object obj) {
		this.setAttribute(key, obj);
	}

	public Object get(String key) {
		return this.getAttribute(key);
	}

	public DHgateSyiSessionImpl(String sessionID, String categoryKey, String objectTypeKey) {
		this.sessionID = sessionID;
		this.syiSessionID = syiPrefix + this.sessionID;
		this.minimalSessionID = minimalPrefix + this.sessionID;
		this.categoryKey = categoryKey;
		this.objectTypeKey = objectTypeKey;
		sessionBean = (DHgateSessionBean) this.getCacheData();
		if (sessionBean == null) {
			sessionBean = new DHgateSessionBean();
		}
	}

	public DHgateSyiSessionImpl(String sessionID, String categoryKey, String objectTypeKey, long liveTime) {
		this.liveTime = liveTime;
		this.categoryKey = categoryKey;
		this.objectTypeKey = objectTypeKey;
		this.sessionID = sessionID;
		this.syiSessionID = syiPrefix + this.sessionID;
		this.minimalSessionID = minimalPrefix + this.sessionID;
		sessionBean = (DHgateSessionBean) this.getCacheData();
		if (sessionBean == null) {
			sessionBean = new DHgateSessionBean();
		}
	}

	public void setAttribute(String key, Object obj) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}

		if (!(obj instanceof Serializable)) {
			throw new RuntimeException("can't set a object of non serializable");
		}

		sessionBean.getSessionBean().put(key, obj);
		this.setCacheData();
	}

	public Object getAttribute(String key) {
		return sessionBean.getSessionBean().get(key);
	}

	public void removeAttribute(String key) {
		this.removeAttribute(key, true);
	}

	public void removeAttribute(String key, boolean save) {
		sessionBean.getSessionBean().remove(key);
		if (save) {
			this.setCacheData();
		}
	}

	public void expire() {
		CacheSellerManager.deleteData(categoryKey, objectTypeKey, syiSessionID);
	}

	private void setCacheData() {
		CacheSellerManager.setData(categoryKey, objectTypeKey, syiSessionID, sessionBean, liveTime);
	}

	private Object getCacheData() {
		String minimalSessionString = null;
		HashMap minimalSession = (HashMap) CacheSellerManager.getData(minimalCategoryKey, minimalObjectTypeKey, minimalSessionID);
		if (minimalSession == null) {
			return null;
		} else {
			minimalSessionString = (String) minimalSession.get(DHgateMinimalSession);
			DHgateSessionBean userSessionBean = (DHgateSessionBean) CacheSellerManager.getData(categoryKey, objectTypeKey, syiSessionID);
			return userSessionBean;
		}
	}

	private String getMinimalSessionField(String field, String minimalSessionString) {
		String fieldVal = null;
		if (field != null && minimalSessionString != null) {
			String[] keyVals = minimalSessionString.split(String.valueOf((char) 28));
			if (keyVals != null && keyVals.length > 0) {
				for (String keyVal : keyVals) {
					if (keyVal != null && keyVal.trim().length() > 0) {
						String[] keyValArry = keyVal.split(String.valueOf((char) 29));
						if (keyValArry != null && keyValArry.length == 2) {
							if (field.equalsIgnoreCase(keyValArry[0])) {
								fieldVal = keyValArry[1];
								break;
							}
						}
					}
				}
			}
		}
		return fieldVal;
	}

}
