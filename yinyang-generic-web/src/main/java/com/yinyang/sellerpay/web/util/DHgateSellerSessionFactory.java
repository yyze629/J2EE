package com.yinyang.sellerpay.web.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhgate.common.util.DHgateSession;
import com.dhgate.skeleton.web.util.CookieUtil;


public class DHgateSellerSessionFactory {
	protected static String CategoryKey = "dhgate";
	protected static String ObjectTypeKey = "service";
	public static final String DHC_S = "dhc_s";
	public static DHgateSession getDHgateSyi1Session(String sessionID) {
		return new DHgateSyiSessionImpl(sessionID, CategoryKey, ObjectTypeKey);
	}

	public static DHgateSession getDHgateSyiSession(HttpServletRequest request, HttpServletResponse response) {
		return new DHgateSyiSessionImpl(getSessionID(request, response), CategoryKey, ObjectTypeKey);
	}
	
	public static DHgateSession getDHgateMinimalSession(HttpServletRequest request, HttpServletResponse response) {
		return new DHgateMinimalSessionImpl(getSessionID(request, response));
	}

	public static String getSessionID(HttpServletRequest request, HttpServletResponse response) {
		String sessionID = CookieUtil.getCookie(request, DHC_S);
		if (sessionID == null) {
			UUID uuid = UUID.randomUUID();
			sessionID = uuid.toString();
			// 86 400秒，一天
			String domain = null;
			if (request.getServerName() != null && request.getServerName().indexOf(CookieConstants.DHGATE_ROOT) > 0) {
				domain = CookieConstants.DHGATE_ROOT;
			}
			CookieUtil.addCookie(DHC_S, sessionID, domain, -1, response);
		}
		return sessionID;
	}
	
	public static String getMinimalSessionField(String field, String minimalSessionString) {
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