package com.yinyang.yy.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EhcacheManager {

	private static final String CACHE_NAME = "freightCache";

	@Autowired
	private CacheManager cacheManager;

	public void addCache(String key, Object value) {
		Cache cache = cacheManager.getCache(CACHE_NAME);
		if (cache != null) {
			Element element = new Element(key, value);
			cache.put(element);
		}
	}

	public Object getCache(String key) {
		Cache cache = cacheManager.getCache(CACHE_NAME);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}
}
