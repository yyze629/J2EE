package com.yinyang.sellerpay.web.util;

import com.dhgate.memcache.IMemCached;
import com.dhgate.memcache.MemCacheFactory;

/**
 * memecache的操作类，往memcached里存、取数据
 * @author sunbaoming@dhgate.com
 * ＠version 2.0
 */
public class CacheSellerManager {
    
    protected static IMemCached cache = MemCacheFactory.getCacheCluster("cluster1");

    /**
	 *
	 * @param CategoryKey
	 * @param ObjectTypeKey
	 * @param IDkey
	 * @param obj
	 * @param liveTime 缓存时间 单位为分钟
	 * @return
	 */
	public static boolean setData(String CategoryKey, String ObjectTypeKey, String IDkey, Object obj, long liveTime)
	{
		if (CategoryKey == null || ObjectTypeKey == null || IDkey == null) {
			return false;
		}
		String key = CategoryKey + "_" + ObjectTypeKey + "_" + IDkey;
		//getMemcachedManager().setMemcached(key, obj, liveTime*60*1000);
		boolean b = cache.set(key, obj, liveTime);
		System.out.println(b);
		return true;
	}

	public static Object getData(String CategoryKey, String ObjectTypeKey, String IDkey)
	{
		if (CategoryKey == null || ObjectTypeKey == null || IDkey == null) {
			return false;
		}
		String key = CategoryKey + "_" + ObjectTypeKey + "_" + IDkey;
		return cache.get(key);
	}

	public static boolean deleteData(String CategoryKey, String ObjectTypeKey, String IDkey)
	{
		if (CategoryKey == null || ObjectTypeKey == null || IDkey == null) {
			return false;
		}
		String key = CategoryKey + "_" + ObjectTypeKey + "_" + IDkey;
		cache.delete(key);
		return true;
	}


}
