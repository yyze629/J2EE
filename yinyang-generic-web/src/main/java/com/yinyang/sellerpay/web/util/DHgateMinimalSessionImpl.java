package com.yinyang.sellerpay.web.util;

import java.util.HashMap;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;

public class DHgateMinimalSessionImpl implements DHgateSession {

	private String sessionID;
	private String minimalSessionID;
	private long liveTime = 24 * 60L;
	private String categoryKey = "dhgate";
	private String objectTypeKey = "common";
	private HashMap<String, String> sessionBean;
	private static String minimalPrefix = "minimal_";

	static Log log = LogFactory.getLogger(DHgateMinimalSessionImpl.class);

	@SuppressWarnings("unchecked")
	public DHgateMinimalSessionImpl(String sessionID) {
		this.sessionID = sessionID;
		this.minimalSessionID = minimalPrefix + this.sessionID;
		sessionBean = (HashMap<String, String>) this.getCacheData();
		if (sessionBean == null) {
			sessionBean = new HashMap<String, String>();
		}
	}

	@SuppressWarnings("unchecked")
	public DHgateMinimalSessionImpl(String sessionID, long liveTime) {
		this.liveTime = liveTime;
		this.sessionID = sessionID;
		this.minimalSessionID = minimalPrefix + this.sessionID;
		sessionBean = (HashMap<String, String>) this.getCacheData();
		if (sessionBean == null) {
			sessionBean = new HashMap<String, String>();
		}
	}

	public String getId() {
		return this.sessionID;
	}

	public String getMinimalSessionId() {
		return this.minimalSessionID;
	}

	public Object get(String key) {
		return this.getAttribute(key);
	}

	public Object getAttribute(String key) {
		return sessionBean.get(key);
	}

	public void set(String key, Object obj) {
		this.setAttribute(key, obj);
	}

	public void setAttribute(String key, Object obj) {
		if (key == null) {
			throw new IllegalArgumentException("key not be null!");
		}

		if (!(obj instanceof String)) {
			throw new RuntimeException("only surpport String ");
		}
		sessionBean.put(key, (String) obj);
		this.setCacheData();
	}

	public void removeAttribute(String key) {
		this.removeAttribute(key, true);
	}

	public void removeAttribute(String key, boolean save) {
		sessionBean.remove(key);
		if (save) {
			this.setCacheData();
		}
	}

	public void expire() {
		CacheSellerManager.deleteData(categoryKey, objectTypeKey, minimalSessionID);
	}

	private Object getCacheData() {
		return CacheSellerManager.getData(categoryKey, objectTypeKey, minimalSessionID);
	}

	private void setCacheData() {
		CacheSellerManager.setData(categoryKey, objectTypeKey, minimalSessionID, sessionBean, liveTime);
	}
	
	public String getMinimalSessionField(String field, String minimalSessionString) {
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
